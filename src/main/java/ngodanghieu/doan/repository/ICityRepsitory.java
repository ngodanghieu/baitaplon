package ngodanghieu.doan.repository;

import ngodanghieu.doan.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICityRepsitory extends JpaRepository<City,Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM city")
    List<City> getAll();

}
