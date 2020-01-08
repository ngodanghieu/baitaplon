package ngodanghieu.doan.repository;

import ngodanghieu.doan.entities.Acreage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAcreage extends JpaRepository<Acreage,Long> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM acreage INNER JOIN  acreage_home ON  acreage.acreage_id = acreage_home.acreage_id  WHERE acreage_home.home_id = :idhome "
    )
    Acreage getByIdHome(@Param("idhome") Long idHome);
}
