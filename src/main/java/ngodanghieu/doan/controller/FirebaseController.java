package ngodanghieu.doan.controller;

import ngodanghieu.doan.model.MessageFCMModel;
import ngodanghieu.doan.model.UserFCMModel;
import ngodanghieu.doan.response.MyResponse;
import ngodanghieu.doan.response.ResponseData;
import ngodanghieu.doan.service.fcm.FCMService;
import ngodanghieu.doan.service.fcm.UserFCMService;
import ngodanghieu.doan.util.Constant;
import ngodanghieu.doan.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/firebase/")
public class FirebaseController {
    @Autowired
    private UserFCMService userFCMService;

    @PostMapping(value = "create-userFcm")
    public ResponseEntity<MyResponse> register(@RequestBody UserFCMModel userFCMModel) {
        MyResponse responseData = new MyResponse();
        try {
            if (userFCMModel == null) {
                return ResponseEntity.ok(ResponseUtils.responseSuccess(responseData, 2,
                        Constant.ErrorTypeCommon.INVALID_INPUT));
            }

            boolean b = userFCMService.updateToken(userFCMModel);
            if (b) {
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


    @PostMapping(value = "test")
    public ResponseEntity<?> test(Long idUSer) {
        ResponseData responseData = new ResponseData();
        try {
            MessageFCMModel messageFCMModel = new MessageFCMModel();
            messageFCMModel.setTitle("NEW ORDER");
            messageFCMModel.setMessage("mot order moi can acsess");
            List<UserFCMModel> userFCMModels = userFCMService.getListTokenByUserId(idUSer);
            userFCMService.pushNotificationToUsersWithoutTopic("NGo dang hiuw,", messageFCMModel, userFCMModels);
        } catch (Exception e) {
            responseData.setStatus(2);
            responseData.setMessage(e.toString());
        }
        return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
    }
}
