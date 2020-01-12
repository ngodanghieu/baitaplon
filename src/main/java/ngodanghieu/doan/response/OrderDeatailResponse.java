package ngodanghieu.doan.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ngodanghieu.doan.entities.OrderInfo;
import ngodanghieu.doan.model.OrderInfoModel;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class OrderDeatailResponse {
    private String orderCode;
    private String nameUser;
    private String phone;
    private String nameHome;
    private Long orderId;
    private String note;
    private String createDay;
    private List<OrderInfoModel> orderInfoModelList = new LinkedList<>();

    public OrderDeatailResponse(String orderCode, String nameUser, String phone, String nameHome, Long orderId, String note, String createDay) {
        this.orderCode = orderCode;
        this.nameUser = nameUser;
        this.phone = phone;
        this.nameHome = nameHome;
        this.orderId = orderId;
        this.note = note;
        this.createDay = createDay;
    }

}
