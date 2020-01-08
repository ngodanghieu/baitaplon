package ngodanghieu.doan.repository;

import ngodanghieu.doan.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDistrictRepository extends JpaRepository<District,Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM district" )
    List<District> getAllDistrict();
    @Query(nativeQuery = true, value = "SELECT district.* FROM district INNER JOIN city ON district.city_id = city.city_id WHERE city.code = :codecity" )
    List<District> getByCodeCity(@Param("codecity") String codeCity);

}
