package ngodanghieu.doan.repository;

import ngodanghieu.doan.entities.AcreageHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAcreageHomeRepository extends JpaRepository<AcreageHome,Long> {
    @Query(nativeQuery = true,
        value = "SELECT * FROM acreage_home WHERE home_id = :idhome "
    )
    AcreageHome getByIdHome(@Param("idhome") Long idHome);
}
