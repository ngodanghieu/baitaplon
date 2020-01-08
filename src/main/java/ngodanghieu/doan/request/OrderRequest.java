package ngodanghieu.doan.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class OrderRequest {
    private Long idUserl;
    private Long idHome;
    private Double totalPrice;
    private Double taxTotal;
    private String requestJson;
    private int paymentWith;
    private String open;
    private String close;
    private String weekday;
}
