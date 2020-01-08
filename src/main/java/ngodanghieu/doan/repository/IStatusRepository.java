package ngodanghieu.doan.repository;

import ngodanghieu.doan.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IStatusRepository extends JpaRepository<Status,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM status WHERE status_code =:status_code")
    Status findByStatusCode(@Param("status_code") String code);
}
