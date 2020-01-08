package ngodanghieu.doan.repository;

import ngodanghieu.doan.entities.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAdress extends JpaRepository<Adress,Long> {

    @Query(nativeQuery = true, value = "SELECT adress.* FROM adress INNER JOIN district ON adress.district_id = district.district_id WHERE district.code = :codedistrict")
    List<Adress> getAdressByCodeAndDistrict(@Param("codedistrict") String codeDistrict);

    @Query(nativeQuery = true, value = "SELECT * FROM adress")
    List<Adress> getAllData();
}
