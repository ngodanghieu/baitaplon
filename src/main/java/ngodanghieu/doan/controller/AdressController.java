package ngodanghieu.doan.controller;

import io.swagger.annotations.Api;
import ngodanghieu.doan.response.AdressResponse;
import ngodanghieu.doan.response.MyResponse;
import ngodanghieu.doan.response.ResponseData;
import ngodanghieu.doan.service.AdressService;
import ngodanghieu.doan.util.Constant;
import ngodanghieu.doan.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@RestController
@RequestMapping(value = "/api/adress/")
public class AdressController {

    @Autowired
    private AdressService adressService;

    @GetMapping(value = "get-all-address-by-district/{code}")
    public ResponseEntity<MyResponse> getAllDistrictByCityCode(@RequestParam("code") String code){
        MyResponse responseData = new MyResponse();
        try {
            List<AdressResponse> addressResponses = adressService.getAllByDistrictCode(code);
            if (!CollectionUtils.isEmpty(addressResponses)){
                responseData.setData(addressResponses);
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData,Constant.StatusCode.OK.getValue(),
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

    @GetMapping(value = "get-all-address")
    public ResponseEntity<MyResponse> getAll(){
        MyResponse responseData = new MyResponse();
        try {
            List<AdressResponse> addressResponses = adressService.getAllData();
            if (!CollectionUtils.isEmpty(addressResponses)){
                responseData.setData(addressResponses);
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData,Constant.StatusCode.OK.getValue(),
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
}
