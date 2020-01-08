package ngodanghieu.doan.service;

import ngodanghieu.doan.entities.User;
import ngodanghieu.doan.entities.UserRole;
import ngodanghieu.doan.repository.IRoleRepository;
import ngodanghieu.doan.repository.IStatusRepository;
import ngodanghieu.doan.repository.IUserRepository;
import ngodanghieu.doan.repository.IUserRoleRepository;
import ngodanghieu.doan.request.UserRequest;
import ngodanghieu.doan.response.UserResponse;
import ngodanghieu.doan.util.Constant;
import ngodanghieu.doan.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IStatusRepository iStatusRepository;

    @Autowired
    private IUserRoleRepository iUserRoleRepository;

    @Autowired
    private IRoleRepository iRoleRepository;

//    @Autowired
//    private SmsService smsService;

    @Transactional
    public UserResponse save(UserRequest userRequest){
        String HasPw = Helper.HasPw(userRequest.getUserHash());
        User newUser = new User();
        newUser.setUserPhone(userRequest.getUserPhone());
        newUser.setUserHash(HasPw);
        newUser.setUserEmail(userRequest.getUserEmail());
        newUser.setUserFullName(userRequest.getUserFullName());
        newUser.setUserCreatedOn(new Date());
        newUser.setStatus(iStatusRepository.findByStatusCode("inactive"));
        sendOTP(newUser,userRequest.getUserPhone());
        User result = iUserRepository.save(newUser);

        if (result == null)
            return null;
        else
        {
            UserRole userRole = new UserRole();
            userRole.setRole(iRoleRepository.findByRoleCode("user"));
            userRole.setUser(result);
            userRole.setCreatedOn(new Date());
            userRole.setCreatedBy("danghieu");
            iUserRoleRepository.save(userRole);
            return MapEntitytoModelResponse(result);
        }
    }

    public String resetPassword(String phone, String passOld,String passNew){
        Optional<User> optionalUser= iUserRepository.findUserByPhone(phone);
        User user = optionalUser.isPresent() ? optionalUser.get(): null;
        if (user != null){
            if (Helper.CheckPw(passOld,user.getUserHash())){
                String hPassw = Helper.HasPw(passNew);
                user.setUserHash(hPassw);
                User result = iUserRepository.save(user);
                if (result == null)
                    return "Loi trong qua trinh xu ly data";
                else
                    return "DONE";
            }else {
                return "PASS NOT TRUE!";
            }
        }else {
            return "HAVE NOT USER !!";
        }
    }

    public UserResponse login(UserRequest userRequest){
        if (checkExistPhoneNumber(userRequest.getUserPhone())){
            Optional<User> optionalUser= iUserRepository.findUserByPhone(userRequest.getUserPhone());
            User user = optionalUser.isPresent() ? optionalUser.get(): null;
            if (user != null && Helper.CheckPw(userRequest.getUserHash(),user.getUserHash())){
                return MapEntitytoModelResponse(user);
            }else {
                return null;
            }
        }else {
            return null;
        }
    }

    public Boolean checkExistUserById(Long idUser){
        return iUserRepository.findByUserId(idUser) != null;
    }

    public Boolean checkExistPhoneNumber(String phone){
        Optional<User> optionalUser= iUserRepository.findUserByPhone(phone);
         User user = optionalUser.isPresent() ? optionalUser.get(): null;
         if (user != null)
             return true;
         else{
             return false;
         }

    }

    private UserResponse MapEntitytoModelResponse(User user){
        return new UserResponse(user.getUserId(),user.getUserFullName(),user.getUserPhone(),user.getUserEmail(),"");
    }

    private void sendOTP(User u, String phone) {
//        String otp = new RandomStringHelper(6, ThreadLocalRandom.current(), RandomStringHelper.digits).nextString();
//        smsService.sendSMSOTPCode(phone, otp);
        String otp = "123456";
        u.setUserOptCode(otp);
        long nowTime = new Date().getTime();
        u.setUserExpiredOtp(new Date(nowTime + Constant.TIME_EXPIRED_OTP));

    }

    public boolean validateOTPCode(String phone, String otpCode) throws Exception {
        Optional<User> optionalUser= iUserRepository.findUserByPhone(phone);
        User user = optionalUser.isPresent() ? optionalUser.get(): null;
        if (user != null){
                if(user.getUserOptCode().equals(otpCode) && user.getUserExpiredOtp().after(new Date())) {
                    user.setStatus(iStatusRepository.findByStatusCode("active"));
                    iUserRepository.save(user);
                    return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }


    public Boolean checkUserValidateOTP(String phone){
        Optional<User> optionalUser= iUserRepository.getUserByPhoneAndValidateOTP(phone);
        User user = optionalUser.isPresent() ? optionalUser.get(): null;
        if (user != null)
            return true;
        else
        {
            Optional<User> optionalUser11 = iUserRepository.findUserByPhone(phone);
            User user1 = optionalUser11.isPresent() ? optionalUser11.get(): null;
            sendOTP(user1,phone);
            iUserRepository.save(user1);
            return false;
        }
    }
//    // Bai tap cua vinid
//    public User findById(Long id){
//        Optional<User>  optionalUser = iUserRepository.findById(id);
//        if (optionalUser.isPresent())
//            return optionalUser.get();
//        else
//            return null;
//    }
//
//    public UserResponse update(UserRequest userRequest){
//        String HasPw = Helper.HasPw(userRequest.getUserHash());
//        User newUser = new User();
//        newUser.setUserId(userRequest.getId());
//        newUser.setUserPhone(userRequest.getUserPhone());
//        newUser.setUserEmail(userRequest.getUserEmail());
//        newUser.setUserHash(HasPw);
//        newUser.setUserFullName(userRequest.getUserFullName());
//        newUser.setUserCreatedOn(new Date());
//        newUser.setStatus(1);
//        sendOTP(newUser,userRequest.getUserPhone());
//        User result = iUserRepository.save(newUser);
//
//        if (result == null)
//            return null;
//        else
//            return MapEntitytoModelResponse(newUser);
//    }
//
//    public Boolean deleteById(Long id){
//         if (findById(id) != null){
//             iUserRepository.deleteById(id);
//             return true;
//         }else{
//             return false;
//         }
//
//    }
//    public List<User> search(String keyWord){
//        String key = "%" + keyWord + "%";
//        return iUserRepository.findUserByName(key,key);
//    }
//
//    public List<User> getAll(){
//       return iUserRepository.getAll();
//    }

}
