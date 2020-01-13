package ngodanghieu.doan.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());

        // Cấu hình user mặc định để thực hiện intergration test
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("spring")
                .password(passwordEncoder().encode("secret"))
                .roles("USER");
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*"); // Có thể chỉ định rõ: https://techmaster.vn
        config.addAllowedHeader("*"); // Có thể chỉ định rõ: Arrays.asList("authorization", "content-type", "x-auth-token")
        config.addAllowedMethod("*"); // Có thể chỉ định rõ: Arrays.asList("GET", "POST", "PUT", "DELETE")
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors() // Lọc CORS -> corsFilter()
                .and()
                .csrf() // Trong Spring , chế độ bảo vệ khỏi CSRF mặc định được bật -> không cần thì disable đi
                .disable()
                .authorizeRequests()
                .antMatchers("/api/user/register").permitAll()
                .antMatchers("/api/user/validateOTPCode").permitAll()
                .antMatchers("/api/order/**").permitAll()
                .antMatchers("/api/home/**").permitAll()
                .antMatchers("/api/user/login").permitAll()
                .antMatchers("/api/firebase/**").permitAll()
                .antMatchers("/api/home/get-all-home").permitAll()
//                .antMatchers("/api/home/get-all-by-user","/api/home/create-home","/api/home/delete-home").hasRole("ownener")
                .antMatchers("/api/home/get-all-by-user","/api/home/create-home","/api/home/delete-home").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .addFilter(new ApiJWTAuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**","/api/home/upload");
    }


}
