package ngodanghieu.doan.controller;

import com.amazonaws.util.IOUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ngodanghieu.doan.model.Data;
import ngodanghieu.doan.model.ResponImage;
import ngodanghieu.doan.model.UploadForm;
import ngodanghieu.doan.request.HomeRequest;
import ngodanghieu.doan.request.SearchRequset;
import ngodanghieu.doan.response.DataResultResponse;
import ngodanghieu.doan.response.HomeResponse;
import ngodanghieu.doan.response.MyResponse;
import ngodanghieu.doan.response.ResponseData;
import ngodanghieu.doan.service.HomeService;
import ngodanghieu.doan.service.HomeWorkTimeService;
import ngodanghieu.doan.util.Constant;
import ngodanghieu.doan.util.Helper;
import ngodanghieu.doan.util.ResponseUtils;
import org.apache.commons.codec.binary.Base64;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/home/")
public class HomeController {
    private static String UPLOAD_DIR = System.getProperty("user.home") + "/upload";

    @Autowired
    private HomeWorkTimeService homeWorkTimeService;

    @Autowired
    private HomeService homeService;

    @GetMapping(value = "get-all-home")
    public ResponseEntity<MyResponse> getAllHome() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userPhone = (String) auth.getPrincipal();
        MyResponse responseData = new MyResponse();
        try {
            List<HomeResponse> result = homeService.getAllHome();
            if (!CollectionUtils.isEmpty(result)) {
                responseData.setData(result);
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

    @GetMapping(value = "get-search")
    public ResponseEntity<MyResponse> getDataSearch(SearchRequset searchRequset) {
        MyResponse responseData = new MyResponse();
        try {
            List<HomeResponse> result = homeService.getDataSearch(searchRequset);
            if (result != null) {
                responseData.setData(result);
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                        Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
            } else {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.NOT_FOUND_ITEM));
            }


        } catch (Exception e) {
            return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                    Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
        }
    }

    @GetMapping(value = "get-all-by-user/{idUser}")
    public ResponseEntity<MyResponse> getAllHomeByUser(@RequestParam("idUser") Long idUser) {
        MyResponse responseData = new MyResponse();
        try {
            List<DataResultResponse> result = homeService.getHomeByIdUser(idUser);
            if (result != null) {
                responseData.setData(result);
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                        Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
            } else {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
            }
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                    Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
        }
    }

    @PostMapping(value = "create-home")
    public ResponseEntity<MyResponse> create(@RequestBody HomeRequest homeRequest, MultipartFile[] file) {
        MyResponse responseData = new MyResponse();
        try {
            if (homeRequest == null) {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.INVALID_INPUT));
            }

            boolean b = homeService.createHome(homeRequest, file);

            if (b) {
                responseData.setData(homeRequest);
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                        Constant.ErrorTypeCommon.OK));
            } else {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
            }
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                    Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
        }
    }

    @DeleteMapping(value = "delete-home/{idHome}")
    public ResponseEntity<MyResponse> delete(@RequestParam("idHome") Long idHome) {
        MyResponse responseData = new MyResponse();
        try {

            if (idHome == null) {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.REQUEST_IS_NULL));
            }

            boolean delete = homeService.delete(idHome);

            if (delete) {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                        Constant.ErrorTypeCommon.OK));
            } else {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
            }


        } catch (Exception e) {
            return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                    Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
        }
    }


}
