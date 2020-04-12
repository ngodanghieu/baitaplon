package ngodanghieu.doan.repository;

import ngodanghieu.doan.entities.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRatingRepository extends JpaRepository<Ratings,Long> {
}
