package ngodanghieu.doan.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DataImage {
    Integer status;
    Boolean success;
    Object data;
}
