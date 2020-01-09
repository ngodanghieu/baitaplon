package ngodanghieu.doan.controller;

import ngodanghieu.doan.entities.User;
import ngodanghieu.doan.repository.IUserRepository;
import ngodanghieu.doan.request.ResetPasswordResquet;
import ngodanghieu.doan.request.UserRequest;
import ngodanghieu.doan.response.ResponseData;
import ngodanghieu.doan.response.UserResponse;
import ngodanghieu.doan.service.UserService;
import ngodanghieu.doan.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "register")
    public ResponseEntity<?> register(@RequestBody UserRequest userRequest){
        ResponseData responseData = new ResponseData();
        try{
            if (userRequest == null) {
                responseData.setStatus(7);
                responseData.setMessage(Constant.ErrorTypeCommon.INVALID_INPUT);
                responseData.setErrorType(Constant.ErrorTypeCommon.INVALID_INPUT);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }
            if(userRequest.getUserPhone().length() >=  12 && userRequest.getUserPhone().length() < 8){
                responseData.setStatus(7);
                responseData.setMessage(Constant.ErrorTypeCommon.INVALID_PHONE);
                return  new ResponseEntity<ResponseData>(responseData,HttpStatus.OK);
            }
            boolean b = userService.checkExistPhoneNumber(userRequest.getUserPhone());
            if (b) {
                responseData.setStatus(2);
                responseData.setMessage("Exist an phone.");
                responseData.setErrorType(Constant.ErrorTypeCommon.PHONE_EXISTS);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }
            UserResponse userResponse = userService.save(userRequest);

            if(userResponse != null){
                responseData.setStatus(1);
                responseData.setContent(userResponse);
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

    @PostMapping(value = "resetPass")
    public ResponseEntity<?> resetPass(@RequestBody ResetPasswordResquet resetPasswordResquet){
        ResponseData responseData = new ResponseData();
        try {
            if (resetPasswordResquet == null){
                responseData.setStatus(7);
                responseData.setMessage(Constant.ErrorTypeCommon.FORM_DATA_INVALID);
                return  new ResponseEntity<ResponseData>(responseData,HttpStatus.OK);
            }
            boolean b = userService.checkExistPhoneNumber(resetPasswordResquet.getPhone());
            if (!b) {
                responseData.setStatus(2);
                responseData.setMessage("No phone.");
                responseData.setErrorType(Constant.ErrorTypeCommon.INVALID_PHONE);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }

            String result = userService.resetPassword(resetPasswordResquet.getPhone(),resetPasswordResquet.getOldPassword(),resetPasswordResquet.getNewPassword());
            responseData.setStatus(1);
            responseData.setContent(result);
            responseData.setMessage(Constant.ErrorTypeCommon.OK);
            return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
        }catch (Exception e){
            responseData.setStatus(2);
            responseData.setMessage(e.toString());
        }
        return new ResponseEntity<ResponseData>(responseData,HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "login")
    public ResponseEntity<?> login(@RequestBody UserRequest userRequest){
        ResponseData responseData = new ResponseData();
        try {
            if (userRequest == null) {
                responseData.setStatus(7);
                responseData.setMessage(Constant.ErrorTypeCommon.INVALID_INPUT);
                responseData.setErrorType(Constant.ErrorTypeCommon.INVALID_INPUT);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }
            if(userRequest.getUserPhone().length() >=  12 && userRequest.getUserPhone().length() < 8){
                responseData.setStatus(7);
                responseData.setMessage(Constant.ErrorTypeCommon.INVALID_PHONE);
                return  new ResponseEntity<ResponseData>(responseData,HttpStatus.OK);
            }
            boolean checkValidate = userService.checkUserValidateOTP(userRequest.getUserPhone());

            if (!checkValidate) {

                responseData.setStatus(2);
                responseData.setMessage("PHONE NOT VALIDATE OTP.");
                responseData.setErrorType(Constant.ErrorTypeCommon.PHONE_EXISTS);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }

            boolean checkExistPhoneNumber = userService.checkExistPhoneNumber(userRequest.getUserPhone());
            if (!checkExistPhoneNumber) {
                responseData.setStatus(2);
                responseData.setMessage("Exist an phone.");
                responseData.setErrorType(Constant.ErrorTypeCommon.PHONE_EXISTS);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }

            UserResponse userResponse = userService.login(userRequest);

            if(userResponse != null){
                responseData.setStatus(1);
                responseData.setContent(userResponse);
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                responseData.setErrorType(Constant.ErrorTypeCommon.OK);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }else {
                responseData.setStatus(2);
                responseData.setMessage("User or pass false or user not validate opt!");
                responseData.setErrorType(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }


        }catch (Exception e){
            responseData.setStatus(2);
            responseData.setMessage(e.toString());
        }
        return new ResponseEntity<ResponseData>(responseData,HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "validateOTPCode")
    public ResponseEntity<?> validateOTPCode(@RequestParam(value = "phone", required = true)  String phone ,@RequestParam(value = "otp", required = true) String otp){
        ResponseData responseData = new ResponseData();
        try{
            if(phone.length() >=  12 && phone.length() < 8){
                responseData.setStatus(7);
                responseData.setMessage(Constant.ErrorTypeCommon.INVALID_PHONE);
                return  new ResponseEntity<ResponseData>(responseData,HttpStatus.OK);
            }
            boolean b = userService.checkExistPhoneNumber(phone);
            if (!b) {
                responseData.setStatus(2);
                responseData.setMessage("Exist an phone.");
                responseData.setErrorType(Constant.ErrorTypeCommon.PHONE_EXISTS);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }
            boolean isTrue = userService.validateOTPCode(phone,otp);

            if(isTrue){
                responseData.setStatus(1);
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                responseData.setErrorType(Constant.ErrorTypeCommon.OK);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }else {
                responseData.setStatus(2);
                responseData.setMessage("CODE FLASE");
                responseData.setErrorType(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }
        }catch (Exception e){
            responseData.setStatus(2);
            responseData.setMessage(e.toString());
        }
        return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
    }

    @Autowired
    IUserRepository userRepository;

    @GetMapping(value = "tets")
    public ResponseEntity<?> test(Long id){
        ResponseData responseData = new ResponseData();
        try{
            User user = userRepository.getUserByPhone("09625416179");

                responseData.setStatus(2);
                responseData.setContent(userRepository.findByUserId(id).getUserRoles());

        }catch (Exception e){
            responseData.setStatus(2);
            responseData.setMessage(e.toString());
        }
        return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
    }
}
