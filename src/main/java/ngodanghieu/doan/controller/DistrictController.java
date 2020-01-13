package ngodanghieu.doan.controller;

import ngodanghieu.doan.response.DistrictResponse;
import ngodanghieu.doan.response.ResponseData;
import ngodanghieu.doan.service.DistrictService;
import ngodanghieu.doan.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getAllDistrict(){
        ResponseData responseData = new ResponseData();
        try {
            List<DistrictResponse> districtResponseList = districtService.getAll();
            if (districtResponseList != null && !districtResponseList.isEmpty()){
                responseData.setContent(districtResponseList);
                responseData.setStatus(1);
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }else {
                responseData.setStatus(2);
                responseData.setMessage(Constant.ErrorTypeCommon.NOT_FOUND_ITEM);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }
        }catch (Exception e){
            responseData.setStatus(2);
            responseData.setMessage(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
            responseData.setErrorType(e.toString());
        }
        return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "api/get-all-district-by-city/{code}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllDistrictByCityCode(@RequestParam("code") String code){
        ResponseData responseData = new ResponseData();
        try {
            List<DistrictResponse> districtResponseList = districtService.getAllByCityCode(code);
            if (districtResponseList != null && !districtResponseList.isEmpty()){
                responseData.setContent(districtResponseList);
                responseData.setStatus(1);
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }else {
                responseData.setStatus(2);
                responseData.setMessage(Constant.ErrorTypeCommon.NOT_FOUND_ITEM);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseData.setStatus(2);
            responseData.setMessage(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
            responseData.setErrorType(e.toString());
        }
        return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
    }
}
