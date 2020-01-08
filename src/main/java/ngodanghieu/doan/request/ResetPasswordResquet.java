package ngodanghieu.doan.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordResquet {
    private String Phone;
    private String oldPassword;
    private String newPassword;
}
