package ngodanghieu.doan.repository;

import ngodanghieu.doan.entities.Order;
import ngodanghieu.doan.entities.Status;
import ngodanghieu.doan.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Order,Long> {

    Order findByOrderCode(String orderCode);
    @Query(nativeQuery = true,value = "SELECT * FROM order WHERE user_id = :user_id")
    List<Order> getDataByUser(@Param("user_id") Long idUSer);

    List<Order> findAllByUser(User user);

    List<Order> findAllByStatus(Status status);

    @Query(nativeQuery = true, value = "SELECT * FROM `order` o INNER JOIN user_home ON user_home.home_id = o.home_id WHERE user_home.user_id=:id_user ")
    List<Order> getDataHistoryOwnner(@Param("id_user") Long idUser);
}
