package ngodanghieu.doan.security;

import ngodanghieu.doan.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        ngodanghieu.doan.entities.User user = iUserRepository.getUserByPhone(phone);
        if (user != null) {
            Set<GrantedAuthority> roles = new HashSet<>();
//            roles.add(new SimpleGrantedAuthority(user.getRole()));
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(roles);
            // Lưu ý: Password ở đây đã được hash sử dụng BCrypt từ lúc tạo user
            // Trả về thông tin để kiểm tra xác thực: username, password, quyền
            return new org.springframework.security.core.userdetails.User(phone, user.getUserHash(), authorities);
        } else {
            throw new UsernameNotFoundException("user get email " + phone + " does not exist.");
        }
    }
}
