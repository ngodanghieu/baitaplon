package ngodanghieu.doan.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private String name;
    private String orderCode;
    private String createDay;
    private Integer statusOrder;

    public OrderResponse(String name, String orderCode, String createDay) {
        this.name = name;
        this.orderCode = orderCode;
        this.createDay = createDay;
    }
}
