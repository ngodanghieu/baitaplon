package ngodanghieu.doan.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String userFullName;
    private String userPhone;
    private String userEmail;
    private String nameRole;
    private String token;
}
