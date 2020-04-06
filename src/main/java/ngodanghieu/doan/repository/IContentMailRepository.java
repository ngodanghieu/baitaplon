package ngodanghieu.doan.repository;

import ngodanghieu.doan.entities.ContentEmail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContentMailRepository extends JpaRepository<ContentEmail,Long> {

    ContentEmail findByType(Long type);

}
