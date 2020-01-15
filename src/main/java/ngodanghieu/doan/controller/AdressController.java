package ngodanghieu.doan.controller;

import io.swagger.annotations.Api;
import ngodanghieu.doan.response.AdressResponse;
import ngodanghieu.doan.response.ResponseData;
import ngodanghieu.doan.service.AdressService;
import ngodanghieu.doan.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "get-all-adress-by-district/{code}")
    public ResponseEntity<?> getAllDistrictByCityCode(@RequestParam("code") String code){
        ResponseData responseData = new ResponseData();
        try {
            List<AdressResponse> adressResponses = adressService.getAllByDistrictCode(code);
            if (adressResponses != null && !adressResponses.isEmpty()){
                responseData.setContent(adressResponses);
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

    @GetMapping(value = "get-all-adress")
    public ResponseEntity<?> getAll(){
        ResponseData responseData = new ResponseData();
        try {
            List<AdressResponse> adressResponses = adressService.getAllData();
            if (adressResponses != null && !adressResponses.isEmpty()){
                responseData.setContent(adressResponses);
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
}
