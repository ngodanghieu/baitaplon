package ngodanghieu.doan.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdressResponse {
    private Integer adressId;
    private DistrictResponse district;
    private long homeId;
    private long latitude;
    private long longtitude;
    private String contentDetail;
}
