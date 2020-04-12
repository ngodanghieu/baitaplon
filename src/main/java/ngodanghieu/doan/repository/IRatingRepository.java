package ngodanghieu.doan.repository;

import ngodanghieu.doan.entities.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRatingRepository extends JpaRepository<Ratings,Long> {

    List<Ratings> findAllByHomeId(Long homeId);

    List<Ratings> findAllByHomeIdAndUserId(Long homeId,Long userId);
}
