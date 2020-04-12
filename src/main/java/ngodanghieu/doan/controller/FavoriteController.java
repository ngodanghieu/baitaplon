package ngodanghieu.doan.controller;

import ngodanghieu.doan.entities.Favorite;
import ngodanghieu.doan.response.AdressResponse;
import ngodanghieu.doan.response.HomeResponse;
import ngodanghieu.doan.response.MyResponse;
import ngodanghieu.doan.response.ResponseData;
import ngodanghieu.doan.service.FavoriteService;
import ngodanghieu.doan.util.Constant;
import ngodanghieu.doan.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/favorite/")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("get-my-favorite-by-user")
    public ResponseEntity<MyResponse> getMyFavoriteByUser(@RequestParam("userId") Long userId){
        MyResponse responseData = new MyResponse();
        try {
            List<HomeResponse> addressResponses = favoriteService.getMyFavorite(userId);
            if (!CollectionUtils.isEmpty(addressResponses)){
                responseData.setData(addressResponses);
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                        Constant.ErrorTypeCommon.OK));
            }else {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData,2,
                        Constant.ErrorTypeCommon.NOT_FOUND_ITEM));
            }
        }catch (Exception e){
            return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData,2,
                    Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
        }
    }

    @DeleteMapping("remove-favorite")
    public ResponseEntity<MyResponse> removeFavorite(@RequestParam("userId") Long userId,@RequestParam("homeId") Long homeId){
        MyResponse responseData = new MyResponse();
        try {
            Boolean result = favoriteService.removeFavorite(userId,homeId);
            if (result){
                responseData.setData(result);
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                        Constant.ErrorTypeCommon.OK));
            }else {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData,2,
                        Constant.ErrorTypeCommon.NOT_FOUND_ITEM));
            }
        }catch (Exception e){
            return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData,2,
                    Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
        }
    }

    @PostMapping("insert-favorite")
    public ResponseEntity<MyResponse> insert(@RequestParam("userId") Long userId,@RequestParam("homeId") Long homeId){
        MyResponse responseData = new MyResponse();
        try {
            Boolean result = favoriteService.insert(userId,homeId);
            if (result){
                responseData.setData(result);
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                        Constant.ErrorTypeCommon.OK));
            }else {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData,2,
                        Constant.ErrorTypeCommon.DUPLICATE));
            }
        }catch (Exception e){
            return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData,2,
                    Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
        }
    }

}
