package ngodanghieu.doan.util;


import ngodanghieu.doan.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ngodanghieu.doan.security.SecurityConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JwtUltis {
    public static Claims verifyToken(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER);
        if (token == null || !token.startsWith(SecurityConstants.PREFIX)) return null;
        // Decode
        return Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(token.replace(SecurityConstants.PREFIX, ""))
                .getBody();
    }

    public static String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUserPhone());
        List<String> roles = new ArrayList<>();
        roles.add("");
        // Thông tin lưu trữ trong JWT dạng json key value
        // Muốn lưu thêm thông tin khác thì cứ put vào
        claims.put("roles", roles);
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + SecurityConstants.EXPIRATION);
        // Encode
        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();
        return SecurityConstants.PREFIX + token;
    }
}
