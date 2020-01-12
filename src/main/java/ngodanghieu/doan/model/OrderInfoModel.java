package ngodanghieu.doan.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class OrderInfoModel {
    private String closeTime;
    private String openTime;
    private String weekday;
}
