package ngodanghieu.doan.repository;

import ngodanghieu.doan.entities.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderHistoryRepository extends JpaRepository<OrderHistory,Long> {
}
