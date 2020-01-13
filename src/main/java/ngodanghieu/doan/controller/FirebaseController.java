package ngodanghieu.doan.controller;

import ngodanghieu.doan.model.MessageFCMModel;
import ngodanghieu.doan.model.UserFCMModel;
import ngodanghieu.doan.response.ResponseData;
import ngodanghieu.doan.service.fcm.FCMService;
import ngodanghieu.doan.service.fcm.UserFCMService;
import ngodanghieu.doan.util.Constant;
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
    public ResponseEntity<?> register(@RequestBody  UserFCMModel userFCMModel){
        ResponseData responseData = new ResponseData();
        try{
            if (userFCMModel == null) {
                responseData.setStatus(7);
                responseData.setMessage(Constant.ErrorTypeCommon.INVALID_INPUT);
                responseData.setErrorType(Constant.ErrorTypeCommon.INVALID_INPUT);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
            }

            boolean b = userFCMService.updateToken(userFCMModel);
            if(b){
                responseData.setStatus(1);
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


    @PostMapping(value = "test")
    public ResponseEntity<?> test(Long idUSer){
        ResponseData responseData = new ResponseData();
        try{
            MessageFCMModel messageFCMModel = new MessageFCMModel();
            messageFCMModel.setTitle("NEW ORDER");
            messageFCMModel.setMessage("mot order moi can acsess");
            List<UserFCMModel> userFCMModels = userFCMService.getListTokenByUserId(idUSer);
            userFCMService.pushNotificationToUsersWithoutTopic("NGo dang hiuw,",messageFCMModel,userFCMModels);
        }catch (Exception e){
            responseData.setStatus(2);
            responseData.setMessage(e.toString());
        }
        return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
    }
}
