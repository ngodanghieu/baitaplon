package ngodanghieu.doan.controller;

import ngodanghieu.doan.response.CityResponse;
import ngodanghieu.doan.response.MyResponse;
import ngodanghieu.doan.response.ResponseData;
import ngodanghieu.doan.service.CityService;
import ngodanghieu.doan.util.Constant;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ngodanghieu.doan.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {
    @Autowired
    private CityService cityService;

    @ApiOperation(value = "Get All City", response = CityResponse.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 500, message = "Loi server")
    })
    @RequestMapping(value = "/api/get-all-city", method = RequestMethod.GET)
    public ResponseEntity<MyResponse> getAllCity() {
        MyResponse responseData = new MyResponse();
        try {
            List<CityResponse> cityResponseList = cityService.getAll();
            if (cityResponseList != null && !cityResponseList.isEmpty()) {
                responseData.setData(cityResponseList);
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                        Constant.ErrorTypeCommon.OK));
            } else {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.NOT_FOUND_ITEM));
            }

        } catch (Exception e) {
            return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                    Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
        }
    }
}
