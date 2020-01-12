package ngodanghieu.doan.repository;

import ngodanghieu.doan.entities.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderInfoRepository extends JpaRepository<OrderInfo,Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM order_info WHERE order_id = :order_id ")
    OrderInfo findByOrderId(@Param("order_id") Long idOrder);

    @Query(nativeQuery = true, value = "SELECT * FROM order_info WHERE order_id = :order_id ")
    List<OrderInfo> getList(@Param("order_id") Long idOrder);
}
