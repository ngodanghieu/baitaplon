package ngodanghieu.doan.controller;

import ngodanghieu.doan.response.DistrictResponse;
import ngodanghieu.doan.response.MyResponse;
import ngodanghieu.doan.response.ResponseData;
import ngodanghieu.doan.service.DistrictService;
import ngodanghieu.doan.util.Constant;
import ngodanghieu.doan.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @RequestMapping(value = "api/get-all-district", method = RequestMethod.GET)
    public ResponseEntity<MyResponse> getAllDistrict(){
        MyResponse responseData = new MyResponse();
        try {
            List<DistrictResponse> districtResponseList = districtService.getAll();
            if (!CollectionUtils.isEmpty(districtResponseList)){
                responseData.setData(districtResponseList);
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                        Constant.ErrorTypeCommon.OK));
            }else {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.NOT_FOUND_ITEM));
            }
        }catch (Exception e){
            return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                    Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
        }
    }

    @RequestMapping(value = "api/get-all-district-by-city/{code}", method = RequestMethod.GET)
    public ResponseEntity<MyResponse> getAllDistrictByCityCode(@RequestParam("code") String code){
        MyResponse responseData = new MyResponse();
        try {
            List<DistrictResponse> districtResponseList = districtService.getAllByCityCode(code);
            if (!CollectionUtils.isEmpty(districtResponseList)){
                responseData.setData(districtResponseList);
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                        Constant.ErrorTypeCommon.OK));
            }else {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.NOT_FOUND_ITEM));
            }
        }catch (Exception e){
            return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                    Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
        }
    }
}
