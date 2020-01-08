package ngodanghieu.doan.service;//package vinid.vinhome.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import vinid.vinhome.entities.User;
//import vinid.vinhome.model.MyUserDetail;
//import vinid.vinhome.repository.IUserRepository;
//
//import java.util.Optional;
//
//@Service
//public class MyUserDetailService implements UserDetailsService {
//
//    @Autowired
//    private IUserRepository iUserRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = iUserRepository.findUserByPhone(username);
//        return new MyUserDetail(user.get());
//    }
//}
