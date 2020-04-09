package ngodanghieu.doan.controller;

import ngodanghieu.doan.entities.User;
import ngodanghieu.doan.repository.IUserRepository;
import ngodanghieu.doan.request.ResetPasswordResquet;
import ngodanghieu.doan.request.UserRequest;
import ngodanghieu.doan.response.MyResponse;
import ngodanghieu.doan.response.ResponseData;
import ngodanghieu.doan.response.UserResponse;
import ngodanghieu.doan.service.UserService;
import ngodanghieu.doan.util.Constant;
import ngodanghieu.doan.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResponseUtils responseUtils;

    @PostMapping(value = "register")
    public ResponseEntity<MyResponse> register(@RequestBody UserRequest userRequest) {
        MyResponse responseData = new MyResponse();
        try {
            if (userRequest == null) {
                return ResponseEntity.ok(ResponseUtils.
                        responseSuccess(responseData, 7, Constant.ErrorTypeCommon.INVALID_INPUT));
            }
            if (userRequest.getUserPhone().length() >= 12 && userRequest.getUserPhone().length() < 8) {
                return ResponseEntity.ok(ResponseUtils.
                        responseSuccess(responseData, 7, Constant.ErrorTypeCommon.INVALID_PHONE));
            }
            boolean b = userService.checkExistPhoneNumber(userRequest.getUserPhone());
            if (b) {
                return ResponseEntity.ok(ResponseUtils.
                        responseSuccess(responseData, 7, Constant.ErrorTypeCommon.PHONE_EXISTS));
            }
            UserResponse userResponse = userService.save(userRequest);

            if (userResponse != null) {
                return ResponseEntity.ok(ResponseUtils.
                        responseSuccess(responseData, Constant.StatusCode.OK.getValue(), Constant.ErrorTypeCommon.OK));
            } else {
                return ResponseEntity.ok(ResponseUtils.
                        responseSuccess(responseData, 2, Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
            }
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseUtils.
                    responseSuccess(responseData, 2, e.getMessage()));
        }

    }

    @PostMapping(value = "reset-pass")
    public ResponseEntity<MyResponse> resetPass(@RequestBody ResetPasswordResquet resetPasswordResquet) {
        MyResponse responseData = new MyResponse();
        try {
            if (resetPasswordResquet == null) {
                return ResponseEntity.ok(ResponseUtils.
                        responseSuccess(responseData, 7, Constant.ErrorTypeCommon.FORM_DATA_INVALID));
            }
            boolean b = userService.checkExistPhoneNumber(resetPasswordResquet.getPhone());
            if (!b) {
                return ResponseEntity.ok(ResponseUtils.
                        responseSuccess(responseData, 7, Constant.ErrorTypeCommon.INVALID_PHONE));
            }

            String result = userService.resetPassword(resetPasswordResquet.getPhone(), resetPasswordResquet.getOldPassword(), resetPasswordResquet.getNewPassword());
            return ResponseEntity.ok(ResponseUtils.
                    responseSuccess(responseData, Constant.StatusCode.OK.getValue(), result));
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseUtils.
                    responseSuccess(responseData, 7, Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
        }
    }

    @PostMapping(value = "login")
    public ResponseEntity<MyResponse> login(@RequestBody UserRequest userRequest) {
        MyResponse responseData = new MyResponse();
        try {
            if (userRequest == null) {
                return ResponseEntity.ok(ResponseUtils.
                        responseSuccess(responseData, 7, Constant.ErrorTypeCommon.INVALID_INPUT));
            }
            if (userRequest.getUserPhone().length() >= 12 && userRequest.getUserPhone().length() < 8) {
                return ResponseEntity.ok(ResponseUtils.
                        responseSuccess(responseData, 7, Constant.ErrorTypeCommon.INVALID_PHONE));
            }


            boolean checkExistPhoneNumber = userService.checkExistPhoneNumber(userRequest.getUserPhone());
            if (!checkExistPhoneNumber) {
                return ResponseEntity.ok(ResponseUtils.
                        responseSuccess(responseData, 7, Constant.ErrorTypeCommon.PHONE_EXISTS));
            }

            boolean checkValidate = userService.checkUserValidateOTP(userRequest.getUserPhone());

            if (!checkValidate) {
                return ResponseEntity.ok(ResponseUtils.
                        responseSuccess(responseData, 7, Constant.ErrorTypeCommon.PHONE_NOT_VALIDATE_OTP));
            }

            UserResponse userResponse = userService.login(userRequest);

            if (userResponse != null) {
                responseData.setData(userResponse);
                return ResponseEntity.ok(ResponseUtils.
                        responseSuccess(responseData, Constant.StatusCode.OK.getValue(), Constant.ErrorTypeCommon.OK));
            } else {
                return ResponseEntity.ok(ResponseUtils.
                        responseSuccess(responseData, 7, Constant.ErrorTypeCommon.USER_OR_PASS_FALSE_OR_USER_NOT_VALIDATE_OPT));
            }


        } catch (Exception e) {
            return ResponseEntity.ok(ResponseUtils.
                    responseSuccess(responseData, 7, Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
        }
    }

    @GetMapping(value = "validateOTPCode")
    public ResponseEntity<MyResponse> validateOTPCode(@RequestParam(value = "phone", required = true) String phone, @RequestParam(value = "otp", required = true) String otp) {
        MyResponse responseData = new MyResponse();
        try {
            if (phone.length() >= 12 && phone.length() < 8) {
                return ResponseEntity.ok(ResponseUtils.
                        responseSuccess(responseData, 7, Constant.ErrorTypeCommon.INVALID_PHONE));
            }
            boolean b = userService.checkExistPhoneNumber(phone);
            if (!b) {
                return ResponseEntity.ok(ResponseUtils.
                        responseSuccess(responseData, 2, Constant.ErrorTypeCommon.PHONE_EXISTS));
            }
            UserResponse userResponse = userService.validateOTPCode(phone, otp);

            if (userResponse != null) {
                responseData.setData(userResponse);
                return ResponseEntity.ok(ResponseUtils.
                        responseSuccess(responseData, Constant.StatusCode.OK.getValue(), Constant.ErrorTypeCommon.OK));
            } else {
                return ResponseEntity.ok(ResponseUtils.
                        responseSuccess(responseData, 2, Constant.ErrorTypeCommon.CODE_FALSE));
            }
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseUtils.
                    responseSuccess(responseData, 2, Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
        }
    }

    @PostMapping(value = "sentAgainOtp")
    public ResponseEntity<MyResponse> sentAgainOtp(@RequestParam(value = "phone", required = true) String phone) {
        MyResponse responseData = new MyResponse();
        try {
            if (phone.length() >= 12 && phone.length() < 8) {
                return ResponseEntity.ok(ResponseUtils.
                        responseSuccess(responseData, 7, Constant.ErrorTypeCommon.INVALID_PHONE));
            }
            boolean b = userService.checkExistPhoneNumber(phone);
            if (!b) {
                return ResponseEntity.ok(ResponseUtils.
                        responseSuccess(responseData, 7, Constant.ErrorTypeCommon.PHONE_EXISTS));
            }
            boolean isSent = userService.senAgainOtp(phone);

            if (isSent) {
                return ResponseEntity.ok(ResponseUtils.
                        responseSuccess(responseData, Constant.StatusCode.OK.getValue(), Constant.ErrorTypeCommon.OK));
            } else {
                return ResponseEntity.ok(ResponseUtils.
                        responseSuccess(responseData, 2, Constant.ErrorTypeCommon.SENT_FALSE));
            }
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseUtils.
                    responseSuccess(responseData, 2, Constant.ErrorTypeCommon.ERROR_PROCESS_DATA));
        }
    }


    @Autowired
    IUserRepository userRepository;

    @GetMapping(value = "tets")
    public ResponseEntity<?> test(Long id) {
        ResponseData responseData = new ResponseData();
        try {
            User user = userRepository.getUserByPhone("09625416179");

            responseData.setStatus(2);
            responseData.setContent(userRepository.findByUserId(id).getUserRoles());

        } catch (Exception e) {
            responseData.setStatus(2);
            responseData.setMessage(e.toString());
        }
        return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
    }
}
