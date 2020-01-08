package ngodanghieu.doan.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
//    private Long id;
    private String userFullName;
//    @Length(min = 8 , max = 12)
    private String userPhone;
//    @Email(message = "EMAIL INVALID")
    private String userEmail;
    private String userHash;
    private String token;
}
