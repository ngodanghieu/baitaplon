package ngodanghieu.doan.service;

import ngodanghieu.doan.entities.Favorite;
import ngodanghieu.doan.repository.IFavoriteRepository;
import ngodanghieu.doan.response.HomeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    @Autowired
    private IFavoriteRepository iFavoriteRepository;

    @Autowired
    private HomeService homeService;


    public List<HomeResponse> getMyFavorite(Long userId){

        List<Favorite> favoriteList = iFavoriteRepository.findAllByUserId(userId);

        if (!CollectionUtils.isEmpty(favoriteList)){
            List<Long> listHomeId = favoriteList.stream().map(Favorite::getHomeId).collect(Collectors.toList());
            return homeService.getListHomeByListHomeId(listHomeId);
        }else {
            return new ArrayList<>();
        }
    }

    @Transactional
    public Boolean removeFavorite(Long userId,Long homeId){
        try {
            iFavoriteRepository.removeByHomeIdAndUserId(homeId,userId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean insert(Long userId,Long homeId){
        try {
            if ( CollectionUtils.isEmpty(iFavoriteRepository.findAllByUserIdAndHomeId(userId,homeId))){
                iFavoriteRepository.save(new Favorite(userId,homeId));
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

}
