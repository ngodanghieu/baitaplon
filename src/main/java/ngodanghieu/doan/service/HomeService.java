package ngodanghieu.doan.service;

import com.google.gson.Gson;
import ngodanghieu.doan.entities.*;
import ngodanghieu.doan.model.HomeWorkTimeModel;
import ngodanghieu.doan.model.ResponImage;
import ngodanghieu.doan.repository.*;
import ngodanghieu.doan.request.HomeRequest;
import ngodanghieu.doan.request.SearchRequset;
import ngodanghieu.doan.response.DataResultResponse;
import ngodanghieu.doan.response.HomeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HomeService {
    @Autowired
    private IHomeRepository iHomeRepository;

    @Autowired
    private AdressHomeService adressHomeService;

    @Autowired
    private IAcreage iAcreage;

    @Autowired
    private IAcreageHomeRepository iAcreageHomeRepository;

    @Autowired
    private IStatusRepository iStatusRepository;

    @Autowired
    private IHomeWorkTimeRepository iHomeWorkTimeRepository;

    @Autowired
    private IOrderRepository iOrderRepository;

    @Autowired
    private IMedia imedia;

    @Autowired
    private IFavoriteRepository iFavoriteRepository;

    @Autowired
    private ICommentRepository iCommentRepository;

    @Autowired
    private IRatingRepository iRatingRepository;

    public Boolean checkHomeExit(Long id) {
        Optional<Home> optionalHome = iHomeRepository.findById(id);
        Home home = optionalHome.isPresent() ? optionalHome.get() : null;
        return home != null;
    }

    public List<HomeResponse> getAllHome(Long limit, Long userId) {

        Page<Home> data = iHomeRepository.getAllHomePage(PageRequest.of(Math.toIntExact(limit), 10));

        List<Home> homeResponseList = data.stream().map(home -> {
            Home result = new Home();
            result.setHomeId(home.getHomeId());
            result.setStatus(home.getStatus());
            result.setContent(home.getContent());
            result.setPrice(home.getPrice());
            result.setImageUrl(home.getImageUrl());
            result.setCreatedBy(home.getCreatedBy());
            result.setCreatedOn(home.getCreatedOn());
            result.setAcreageHomes(home.getAcreageHomes());
            result.setAdressHomes(home.getAdressHomes());
            result.setHomeWorktimes(home.getHomeWorktimes());
            result.setModifiedBy(home.getModifiedBy());
            result.setUserHomes(home.getUserHomes());
            result.setModifiedOn(home.getModifiedOn());
            result.setOrders(home.getOrders());
            result.setOrderInfos(home.getOrderInfos());
            return result;
        }).collect(Collectors.toList());
        List<HomeResponse> result = new LinkedList<>();
        if (homeResponseList != null && !homeResponseList.isEmpty()) {
            homeResponseList.forEach(x -> {
                if (checkHome(x.getHomeId())) {
                    result.add(mappingEntityToResponse(x));
                }

            });
            List<Favorite> favoriteList = iFavoriteRepository.findAllByUserId(userId);
            if (!CollectionUtils.isEmpty(favoriteList)) {
                result.forEach(item -> {
                    favoriteList.forEach(f -> {
                        if (item.getHomeId() == f.getHomeId()) {
                            item.setIsMyFavorite(1);
                        }
                    });
                    float start = totalRating(item.getHomeId());
                    item.setStart(start);
                });
            }
            return result;
        } else {
            return null;
        }
    }

    private float totalRating(Long homeId) {
        List<Ratings> list = iRatingRepository.findAllByHomeId(homeId);
        float result = 0f;
        if (!CollectionUtils.isEmpty(list)) {
            for (Ratings ratings : list){
                result += ratings.getStars();
            }

            result = result / (float)list.size();
        }
        return result;

    }

    private Boolean checkHome(Long idHome) {
        List<Order> order = iOrderRepository.getByhome(idHome);
        boolean id = true;
        if (order != null && !order.isEmpty()) {
            for (Order order1 : order) {
                if (order1.getStatus().getStatusId() != 7 && order1.getStatus().getStatusId() != 6) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return true;
        }
        return false;
    }

    public HomeResponse getHomeById(Long id) {
        Optional<Home> optionalHome = iHomeRepository.findById(id);
        Home home = optionalHome.isPresent() ? optionalHome.get() : null;
        if (home != null) {
            return mappingEntityToResponse(home);
        } else {
            return null;
        }
    }

    public List<HomeResponse> getDataSearch(SearchRequset searchRequset) {
        List<Home> homeResponseList = (searchRequset.getAdress() == null && searchRequset.getCodeCity() == null && searchRequset.getCodeDistrit() == null) ?
                iHomeRepository.getAllHome(100L) : iHomeRepository.getDataSearch(
                searchRequset.getCodeCity() == null ? "''" : searchRequset.getCodeCity(),
                searchRequset.getCodeDistrit() == null ? "''" : searchRequset.getCodeDistrit(),
                searchRequset.getAdress() == null ? "''" : searchRequset.getAdress());
        List<HomeResponse> result = new LinkedList<>();
        if (homeResponseList != null && !homeResponseList.isEmpty()) {
            homeResponseList.forEach(x -> {
                result.add(mappingEntityToResponse(x));
            });
            return result;
        } else {
            return null;
        }
    }

    @Transactional
    public Boolean createHome(HomeRequest homeRequest) throws Exception {
        Home home = iHomeRepository.findByHomeId(homeRequest.getId() == null ? 0 : homeRequest.getId());
        if (home != null) {
            Home save = mappingModelToEntityHome(home, homeRequest);
            save.setStatus(iStatusRepository.findByStatusCode("active"));
            Home home1 = iHomeRepository.save(save);
            if (homeRequest.getHomeWorkTimeModels() != null && !homeRequest.getHomeWorkTimeModels().isEmpty()) {
                homeRequest.getHomeWorkTimeModels().forEach(x -> {
                    HomeWorktime homeWorktime = mapModelToEntitiesHomeWorkTime(x, home1);
                    iHomeWorkTimeRepository.save(homeWorktime);
                });
            }
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Boolean delete(Long idHome) throws Exception {
        Home home = iHomeRepository.findByHomeId(idHome);
        if (home != null) {
            home.setStatus(iStatusRepository.findByStatusCode("inactive"));
            iHomeRepository.save(home);
            return true;
        } else {
            return false;
        }
    }

    public List<DataResultResponse> getHomeByIdUser(Long idUser) {
        List<Home> homeResponseList = iHomeRepository.getHomeByIdUser(idUser);
        List<DataResultResponse> result = new LinkedList<>();
        if (homeResponseList != null && !homeResponseList.isEmpty()) {
            homeResponseList.forEach(x -> {
                AcreageHome acreageHome = iAcreageHomeRepository.getByIdHome(x.getHomeId());
                List<String> listPrice = null;
                if (acreageHome != null) {
                    listPrice = removeDuplicates(iHomeRepository.getAllPrice(acreageHome.getAcreage().getAcreageId()));
                }
                result.add(mapEntityToDataResultResponse(x, listPrice == null ? null : listPrice));
            });
            return result;
        } else {
            return new ArrayList<>();
        }

    }

    private List<String> removeDuplicates(List<String> list) {
        List<String> listWithoutDuplicates = list.stream()
                .distinct()
                .collect(Collectors.toList());
        return listWithoutDuplicates;
    }

    private HomeResponse mappingEntityToResponse(Home home) {
        String title = getTitleHome(home.getHomeId());
        Acreage acreage = iAcreage.getByIdHome(home.getHomeId());
        StringBuilder acreageString = new StringBuilder();
        acreageString.append(acreage.getTotalArea())
                .append("(").append(acreage.getWidth()).append("x").append(acreage.getHeight()).append(")");
        return new HomeResponse(home.getHomeId(), title, home.getContent(), home.getImageUrl(), acreageString.toString(), home.getPrice(), home.getCreatedOn(), home.getCreatedBy(), "", 0, 0f);
    }

    private String getTitleHome(Long id) {
        AdressHome adressHome = adressHomeService.getiAdressHomeByIdHome(id);
        StringBuilder title = new StringBuilder();
        title.append(adressHome.getNameHome()).append(" - ").append(adressHome.getBuilding().getName());
        return title.toString();
    }


    private DataResultResponse mapEntityToDataResultResponse(Home home, List<String> priceList) {
        String title = getTitleHome(home.getHomeId());
        return new DataResultResponse(home.getHomeId(), title, priceList);
    }

    private Home mappingModelToEntityHome(Home home, HomeRequest homeRequest) throws Exception {
        home.setContent(homeRequest.getContent());
        home.setPrice(homeRequest.getPrice());
        home.setImageUrl(homeRequest.getImageUrls().get(0));
        saveMedia(home, homeRequest.getImageUrls());
        return home;
    }

    @Transactional
    void saveMedia(Home home, List<String> list) throws Exception {
        List<Media> result = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            for (String item : list) {
                Media media = new Media();
                media.setHomeId(home.getHomeId());
                media.setType("IMAGE");
//                media.setUserId();
                media.setUrl(item);
                result.add(media);
                imedia.save(media);
            }
        }
//        imedia.saveAll(result);
    }

    private HomeWorktime mapModelToEntitiesHomeWorkTime(HomeWorkTimeModel homeWorkTimeModel, Home home) {
        HomeWorktime homeWorktime = new HomeWorktime();
        homeWorktime.setHome(home);
        homeWorktime.setCloseTime(homeWorkTimeModel.getCloseTime());
        homeWorktime.setOpenTime(homeWorkTimeModel.getOpenTime());
        homeWorktime.setCreatedBy("DANG HIEU");
        homeWorktime.setCreatedOn(new Date());
        homeWorktime.setWeekday(String.valueOf(new Date().getDay()));
        homeWorktime.setModifiedOn(new Date());
        homeWorktime.setModifiedBy("dang hieu");
        return homeWorktime;
    }

    //    @Async
    public String getImgUrlContent(MultipartFile urlImage) throws Exception {

        File convertFile = new File(urlImage.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertFile);
        fos.write(urlImage.getBytes());
        fos.close();
        // set header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.add("Authorization", "Client-ID ad637b41f54375b");
        // set body
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", new FileSystemResource(convertFile));
        // add header & body to request
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        String serverUrl = "https://api.imgur.com/3/upload";
        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> response = restTemplate.postForEntity(serverUrl, requestEntity, String.class);
        String response2 = restTemplate.postForObject(serverUrl, requestEntity, String.class);
        ResponImage image = new Gson().fromJson(response2, ResponImage.class);
        return image.getData().getLink();
    }

    public List<HomeResponse> getListHomeByListHomeId(List<Long> homeIds) {
        List<Home> data = iHomeRepository.findAllByHomeIdIn(homeIds);
        if (!CollectionUtils.isEmpty(data)) {
            List<HomeResponse> result = data.stream().map(this::mappingEntityToResponse).collect(Collectors.toList());
            return result;
        } else {
            return new ArrayList<>();
        }
    }
}
