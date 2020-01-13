package ngodanghieu.doan.service.fcm;


import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import ngodanghieu.doan.entities.User;
import ngodanghieu.doan.entities.UserFcm;
import ngodanghieu.doan.model.MessageFCMModel;
import ngodanghieu.doan.model.UserFCMModel;
import ngodanghieu.doan.repository.IUserFMC;
import ngodanghieu.doan.repository.IUserRepository;
import ngodanghieu.doan.service.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserFCMService {

    @Autowired
    private IUserFMC userFCMDAO;

    @Autowired
    private FCMService fcmService;

    @Autowired
    private UserService userService;

    @Autowired
    private IUserRepository iUserRepository;

    @Transactional
    public Boolean updateToken(UserFCMModel userFCMModel) {
        if(userFCMModel != null) {
            UserFcm userFCM = userFCMDAO.getByIdUserAndDevice(userFCMModel.getUserId(), userFCMModel.getDeviceId());
            if(userFCM != null) {
                userFCM.setToken(userFCMModel.getToken());
                userFCM.setModifiedBy(new Date());
                userFCMDAO.save(userFCM);
            } else {
                UserFcm user = new UserFcm();
                User u = iUserRepository.findByUserId(userFCMModel.getUserId());
                user.setDeviceId(userFCMModel.getDeviceId());
                user.setUser(u);
                user.setCreatedDate(new Date());
                user.setModifiedBy(new Date());
                user.setToken(userFCMModel.getToken());
                userFCMDAO.save(user);
            }
            return true;
        }else {
            return false;
        }
    }

    public List<UserFCMModel> getListTokenByListUserIds(List<Long> userIds) {
        List<UserFCMModel> list = new LinkedList<>();
        if(userIds != null && !userIds.isEmpty()) {
            for(Long id : userIds) {
                convertEntityToModel(list, id);
            }
        }
        return list;
    }

    public List<UserFCMModel> getListTokenByUserId(Long userIds) {
        List<UserFCMModel> list = new LinkedList<>();
        if (userIds != null) {
            convertEntityToModel(list, userIds);
        }
        return list;
    }

    public void pushNotificationToUsersWithoutTopic(String orderCode, MessageFCMModel messageFCMModel, List<UserFCMModel> userFCMS) {
        if (!userFCMS.isEmpty()) {
            try {
                List<String> listToken = new ArrayList<>();
                for (UserFCMModel userFCM : userFCMS) {
                    listToken.add(userFCM.getToken());
                }
                JSONObject content = new JSONObject();

                content.put("orderCode", orderCode);

                Map<String, String> data = new HashMap<>();
                data.put("type", "1");
                data.put("message", messageFCMModel.getMessage());
                data.put("title", messageFCMModel.getTitle());
                data.put("content", content.toString());
                fcmService.sendMulticast(data, listToken);
            } catch (FirebaseMessagingException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void pushNotificationToOwnersWithoutTopic(MessageFCMModel messageFCMModel, List<UserFCMModel> userFCMS) {
        if (!userFCMS.isEmpty()) {
            try {
                List<String> listToken = new ArrayList<>();
                for (UserFCMModel userFCM : userFCMS) {
                    listToken.add(userFCM.getToken());
                }

                Map<String, String> data = new HashMap<>();
                data.put("type", "2");
                data.put("message", messageFCMModel.getMessage());
                data.put("title", messageFCMModel.getTitle());
                fcmService.sendMulticast(data, listToken);
            } catch (FirebaseMessagingException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void convertEntityToModel(List<UserFCMModel> list, Long id) {
        List<UserFcm> userFCMList = userFCMDAO.getByUser(id);
        if(userFCMList != null && !userFCMList.isEmpty()) {
            for (UserFcm userFCM : userFCMList) {
                UserFCMModel model = new UserFCMModel();
                model.setUserId(userFCM.getUser().getUserId());
                model.setDeviceId(userFCM.getDeviceId());
                model.setToken(userFCM.getToken());
                list.add(model);
            }
        }
    }
}
