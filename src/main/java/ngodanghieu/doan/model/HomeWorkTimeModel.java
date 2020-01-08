package ngodanghieu.doan.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class HomeWorkTimeModel {
    private Integer homeWorktimeId;
    private String closeTime;
    private String openTime;
    private String weekday;
}
