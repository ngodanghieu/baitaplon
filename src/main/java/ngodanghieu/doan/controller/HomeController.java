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
import ngodanghieu.doan.response.ResponseData;
import ngodanghieu.doan.service.HomeService;
import ngodanghieu.doan.service.HomeWorkTimeService;
import ngodanghieu.doan.util.Constant;
import ngodanghieu.doan.util.Helper;
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
    public ResponseEntity<?> getAllHome(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userPhone = (String) auth.getPrincipal();
        ResponseData responseData = new ResponseData();
        try {
            List<HomeResponse> result =  homeService.getAllHome();
            if (result != null){
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                responseData.setContent(result);
                responseData.setStatus(1);
                return new ResponseEntity<ResponseData>(responseData,HttpStatus.OK);
            }else
            {
                responseData.setStatus(2);
                responseData.setMessage(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
                return new ResponseEntity<ResponseData>(responseData,HttpStatus.OK);
            }


        }catch (Exception e){
            responseData.setStatus(2);
            responseData.setErrorType(e.toString());
            responseData.setMessage(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
        }
        return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "get-search")
    public ResponseEntity<?> getDataSearch(SearchRequset searchRequset){
        ResponseData responseData = new ResponseData();
        try {
            List<HomeResponse> result =  homeService.getDataSearch(searchRequset);
            if (result != null){
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                responseData.setContent(result);
                responseData.setStatus(1);
                return new ResponseEntity<ResponseData>(responseData,HttpStatus.OK);
            }else {
                responseData.setStatus(2);
                responseData.setMessage(Constant.ErrorTypeCommon.NOT_FOUND_ITEM);
            }


        }catch (Exception e){
            responseData.setStatus(2);
            responseData.setErrorType(e.getMessage());
            responseData.setMessage(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
        }
        return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "get-all-by-user/{idUser}")
    public ResponseEntity<?> getAllHomeByUser(@RequestParam("idUser") Long idUser){
        ResponseData responseData = new ResponseData();
        try {
            List<DataResultResponse> result =  homeService.getHomeByIdUser(idUser);
            if (result != null){
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                responseData.setContent(result);
                responseData.setStatus(1);
                return new ResponseEntity<ResponseData>(responseData,HttpStatus.OK);
            }else
            {
                responseData.setStatus(2);
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
            }


        }catch (Exception e){
            responseData.setStatus(2);
            responseData.setErrorType(e.toString());
            responseData.setMessage(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
        }
        return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "create-home")
    public ResponseEntity<?> create(@RequestBody HomeRequest homeRequest, MultipartFile file){
        ResponseData responseData = new ResponseData();
        try{
            if (homeRequest == null) {
                responseData.setStatus(7);
                responseData.setMessage(Constant.ErrorTypeCommon.INVALID_INPUT);
                responseData.setErrorType(Constant.ErrorTypeCommon.INVALID_INPUT);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }

            boolean b = homeService.createHome(homeRequest,file);

            if(b){
                responseData.setStatus(1);
                responseData.setContent(homeRequest);
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                responseData.setErrorType(Constant.ErrorTypeCommon.OK);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }else {
                responseData.setStatus(2);
                responseData.setMessage("ERROR_PROCESS_DATA");
                responseData.setErrorType(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }
        }catch (Exception e){
            responseData.setStatus(2);
            responseData.setMessage(e.toString());
        }
        return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "delete-home/{idHome}")
    public ResponseEntity<?> delete(@RequestParam("idHome") Long idHome){
        ResponseData responseData = new ResponseData();
        try {

            if (idHome == null){
                responseData.setStatus(2);
                responseData.setMessage(Constant.ErrorTypeCommon.REQUEST_IS_NULL);
                return new ResponseEntity<ResponseData>(responseData,HttpStatus.OK);
            }

            boolean delete = homeService.delete(idHome);

            if (delete){
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                responseData.setStatus(1);
                return new ResponseEntity<ResponseData>(responseData,HttpStatus.OK);
            }else
            {
                responseData.setStatus(2);
                responseData.setMessage(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
                return new ResponseEntity<ResponseData>(responseData,HttpStatus.OK);
            }


        }catch (Exception e){
            responseData.setStatus(2);
            responseData.setErrorType(e.toString());
            responseData.setMessage(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
        }
        return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
    }


}
