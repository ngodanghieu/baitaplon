package ngodanghieu.doan.model;//package vinid.vinhome.model;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import vinid.vinhome.entities.User;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//
//public class MyUserDetail implements UserDetails {
//    private String phone;
//    private String pass;
//    private boolean active;
//    private List<GrantedAuthority> grantedAuthorityList;
//
//    public MyUserDetail(User user) {
//        this.phone = user.getUserPhone();
//        this.pass =user.getUserHash();
//
////        this.grantedAuthorityList = Arrays.stream(.)
//
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
//    }
//
//    @Override
//    public String getPassword() {
//        return "123";
//    }
//
//    @Override
//    public String getUsername() {
//        return phone;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
