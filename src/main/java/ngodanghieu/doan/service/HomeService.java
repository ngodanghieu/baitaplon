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
import ngodanghieu.doan.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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

    public Boolean checkHomeExit(Long id){
        Optional<Home> optionalHome = iHomeRepository.findById(id);
        Home home = optionalHome.isPresent() ? optionalHome.get(): null;
        return home != null;
    }

    public List<HomeResponse> getAllHome(){
        List<Home> homeResponseList = iHomeRepository.getAllHome();
        List<HomeResponse> result = new LinkedList<>();
        if (homeResponseList != null && !homeResponseList.isEmpty()){
            homeResponseList.forEach(x ->{
                if (checkHome(x.getHomeId())){
                    result.add(mappingEntitiToResponse(x));
                }

            });
            return result;
        }else {
            return null;
        }
    }

    private Boolean checkHome(Long idHome){
        List<Order> order =  iOrderRepository.getByhome(idHome);
        boolean id = true;
        if (order != null && !order.isEmpty()){
            for (Order order1 : order){
                if (order1.getStatus().getStatusId() != 7 && order1.getStatus().getStatusId() != 6){
                    return true;
                }else {
                    return false;
                }
            }
        }else {
            return true;
        }
        return false;
    }

    public HomeResponse getHomeById(Long id){
        Optional<Home> optionalHome = iHomeRepository.findById(id);
        Home home = optionalHome.isPresent() ? optionalHome.get(): null;
        if (home != null ){
            return mappingEntitiToResponse(home);
        }else {
            return null;
        }
    }

    public List<HomeResponse> getDataSearch(SearchRequset searchRequset){
        List<Home> homeResponseList = (searchRequset.getAdress() == null && searchRequset.getCodeCity() == null && searchRequset.getCodeDistrit() == null) ?
                iHomeRepository.getAllHome() : iHomeRepository.getDataSearch(
                searchRequset.getCodeCity() == null ? "''" : searchRequset.getCodeCity(),
                searchRequset.getCodeDistrit() == null ? "''" : searchRequset.getCodeDistrit(),
                searchRequset.getAdress() == null ? "''" : searchRequset.getAdress());
        List<HomeResponse> result = new LinkedList<>();
        if (homeResponseList != null && !homeResponseList.isEmpty()){
            homeResponseList.forEach(x ->{
                result.add(mappingEntitiToResponse(x));
            });
            return result;
        }else {
            return null;
        }
    }

    @Transactional
    public Boolean createHome(HomeRequest homeRequest, MultipartFile fileData) throws Exception {
        Home home = iHomeRepository.findByHomeId(homeRequest.getId() == null ? 0 : homeRequest.getId());
        if (home != null){
             Home save =  mappingModelToEntitiHome(home,homeRequest,fileData);
             save.setStatus(iStatusRepository.findByStatusCode("active"));
             Home home1 = iHomeRepository.save(save);
             if (homeRequest.getHomeWorkTimeModels() != null && !homeRequest.getHomeWorkTimeModels().isEmpty()){
                 homeRequest.getHomeWorkTimeModels().forEach(x->{
                     HomeWorktime homeWorktime = mapModelToEntitiesHomeWorkTime(x,home1);
                     iHomeWorkTimeRepository.save(homeWorktime);
                 });
             }
            return true;
        }else {
            return false;
        }
    }

    @Transactional
    public Boolean delete(Long idHome) throws Exception {
        Home home = iHomeRepository.findByHomeId(idHome);
        if (home != null){
            home.setStatus(iStatusRepository.findByStatusCode("inactive"));
            iHomeRepository.save(home);
            return true;
        }else {
            return false;
        }
    }



    public  List<DataResultResponse> getHomeByIdUser(Long idUser){
        List<Home> homeResponseList = iHomeRepository.getHomeByIdUser(idUser);
        List<DataResultResponse> result = new LinkedList<>();
        if (homeResponseList != null && !homeResponseList.isEmpty()){
            homeResponseList.forEach(x ->{
                AcreageHome acreageHome = iAcreageHomeRepository.getByIdHome(x.getHomeId());
                List<String> listPrice = null;
                if (acreageHome != null){
                    listPrice = removeDulicates(iHomeRepository.getAllPrice(acreageHome.getAcreage().getAcreageId()));
                }
                result.add(mapEntitiToDataResultResponse(x,listPrice == null ? null : listPrice));
            });
            return result;
        }else {
            return null;
        }

    }

    private List<String> removeDulicates(List<String> list){
        List<String> listWithoutDuplicates = list.stream()
                .distinct()
                .collect(Collectors.toList());
        return listWithoutDuplicates;
    }

    private HomeResponse mappingEntitiToResponse(Home home){
        String title = getTitleHome(home.getHomeId());
        Acreage acreage = iAcreage.getByIdHome(home.getHomeId());
        StringBuilder acreageString = new StringBuilder();
        acreageString.append(acreage.getTotalArea())
                .append("(").append(acreage.getWidth()).append("x").append(acreage.getHeight()).append(")");
        return new HomeResponse(home.getHomeId(),title,home.getContent(),home.getImageUrl(),acreageString.toString(),home.getPrice(),home.getCreatedOn(),home.getCreatedBy(),"");
    }

    private String getTitleHome(Long id){
        AdressHome adressHome = adressHomeService.getiAdressHomeByIdHome(id);
        StringBuilder title = new StringBuilder();
        title.append(adressHome.getNameHome()).append(" - ").append(adressHome.getBuilding().getName());
        return title.toString();
    }

    private DataResultResponse mapEntitiToDataResultResponse(Home home,  List<String> priceList){
        String title = getTitleHome(home.getHomeId());
        return new DataResultResponse(home.getHomeId(),title,priceList);
    }

    private Home mappingModelToEntitiHome(Home home, HomeRequest homeRequest,MultipartFile fileData) throws Exception {
        home.setContent(homeRequest.getContent());
        home.setPrice(homeRequest.getPrice());
        String imageUrl = getImgurContent(fileData);
        home.setImageUrl(imageUrl);
        return home;
    }

    private HomeWorktime mapModelToEntitiesHomeWorkTime(HomeWorkTimeModel homeWorkTimeModel, Home home){
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

    public  String getImgurContent(MultipartFile urlImage) throws Exception {

        File convFile = new File( urlImage.getOriginalFilename() );
        FileOutputStream fos = new FileOutputStream( convFile );
        fos.write( urlImage.getBytes() );
        fos.close();
        // set header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.add("Authorization", "Client-ID ad637b41f54375b");
        // set body
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", new FileSystemResource(convFile));
        // add header & body to request
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        String serverUrl = "https://api.imgur.com/3/upload";
        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> response = restTemplate.postForEntity(serverUrl, requestEntity, String.class);
        String  response2 = restTemplate.postForObject(serverUrl, requestEntity, String.class);
        ResponImage image = new Gson().fromJson(response2,ResponImage.class);
        return image.getData().getLink();
    }
}
