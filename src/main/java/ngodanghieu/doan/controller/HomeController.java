package ngodanghieu.doan.controller;

import com.amazonaws.util.IOUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.List;

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
    public ResponseEntity<?> create(@RequestBody HomeRequest homeRequest){
        ResponseData responseData = new ResponseData();
        try{
            if (homeRequest == null) {
                responseData.setStatus(7);
                responseData.setMessage(Constant.ErrorTypeCommon.INVALID_INPUT);
                responseData.setErrorType(Constant.ErrorTypeCommon.INVALID_INPUT);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
            }
//            byte[] encodedBytes = Base64.encodeBase64(fileData.getBytes());

//            String a = getImgurContent(encodedBytes.toString());

            boolean b = homeService.createHome(homeRequest,null);




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
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
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
            }


        }catch (Exception e){
            responseData.setStatus(2);
            responseData.setErrorType(e.toString());
            responseData.setMessage(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
        }
        return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("upload")
    public ResponseEntity<?> uploadFile(@ModelAttribute("uploadForm") UploadForm form) throws IOException {
        // Create folder to save file if not exist
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        MultipartFile[] fileData = form.getFileData();
        String name = fileData[0].getOriginalFilename();
//        Helper.doUpload2(null,)
        byte[] encodedBytes;
        encodedBytes = Base64.encodeBase64(fileData[0].getBytes());
        if (name != null && name.length() > 0) {
            try {
                // Create file
                File serverFile = new File(UPLOAD_DIR + "/" + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(fileData[0].getBytes());
                stream.close();
                return ResponseEntity.ok("Upload success");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error when uploading");
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request");
    }
//    @PostMapping("upload2")
    public  String getImgurContent(String urlImage) throws Exception {
//        URL url;
//        url = new URL("https://api.imgur.com/3/upload");
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
////        String data = URLEncoder.encode("image", "UTF-8") + "="
////                + URLEncoder.encode(IMAGE_URL, "UTF-8");
//
//        conn.setDoOutput(true);
//        conn.setDoInput(true);
//        conn.setRequestMethod("POST");
//        conn.setRequestProperty("Authorization", "Client-ID ad637b41f54375b");
//        conn.setRequestMethod("POST");
//        conn.setRequestProperty("Content-Type",
//                "application/x-www-form-urlencoded");
//
//        conn.connect();
//        StringBuilder stb = new StringBuilder();
//        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
////        wr.write(data);
//        wr.flush();
//
//        // Get the response
//        BufferedReader rd = new BufferedReader(
//                new InputStreamReader(conn.getInputStream()));
//        String line;
//        while ((line = rd.readLine()) != null) {
//            stb.append(line).append("\n");
//        }
//        wr.close();
//        rd.close();
//
//        return stb.toString();


        URL url;

        url = new URL("https://api.imgur.com/3/upload");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        String data = URLEncoder.encode("image", "UTF-8") + "="
                + URLEncoder.encode(urlImage, "UTF-8");

        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Client-ID ad637b41f54375b");
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");

        conn.connect();
        StringBuilder stb = new StringBuilder();
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();

        // Get the response
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            stb.append(line).append("\n");
        }
        wr.close();
        rd.close();

        System.out.println(stb.toString());

        return stb.toString();
    }


    @PostMapping("upload2sdsadasdas")
    public String uploadFile2(@RequestParam("file") MultipartFile file) throws Exception {
        // Create folder to save file if not exist
        String result = Helper.doUpload2(null,file);
        return result;
    }
    @Autowired
    public Environment environment;
    @GetMapping(value = "/api/upload/image/{mediaId}")
    @Async
    public ResponseEntity<byte[]> getImage(String url) throws IOException {
        String urlPath = environment.getProperty("path.url.upload");
        String imgUrl = urlPath + "/" + url;
        File imgPath = new File(imgUrl);
        FileInputStream input = new FileInputStream(imgUrl);
        byte[] image = IOUtils.toByteArray(input);

        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @PostMapping("/upload2222222222222222222")
    public ResponseEntity<?> uploadFile2(@ModelAttribute("uploadForm") UploadForm form) {
        // Create folder to save file if not exist
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        MultipartFile[] fileData = form.getFileData();
        String name = fileData[0].getOriginalFilename();
        if (name != null && name.length() > 0) {
            try {
                // Create file
                File serverFile = new File(UPLOAD_DIR + "/" + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(fileData[0].getBytes());
                stream.close();
                return ResponseEntity.ok(serverFile.getPath());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error when uploading");
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request");
    }

    @ApiOperation(value = "Get file")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request")
    })
    @GetMapping("/file/{filename}")
    public ResponseEntity<?> download(@PathVariable String filename) {
        File file = new File(UPLOAD_DIR + "/" + filename);
        if (!file.exists()) {
//            throw new RecordNotFoundException("File not found");
        }

        UrlResource resource = null;
        try {
            resource = new UrlResource(file.toURI());
        } catch (MalformedURLException e) {
//            throw new RecordNotFoundException("File not found");
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(resource);
    }
}
