package ngodanghieu.doan.repository;

import ngodanghieu.doan.entities.Home;
import ngodanghieu.doan.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IHomeRepository extends JpaRepository<Home,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM home " +
            "INNER JOIN status ON status.status_id =home.status " +
            "INNER JOIN adress_home ON home.home_id = adress_home.home_id " +
            "WHERE status.status_code = 'active'")
    List<Home> getAllHome();

    Home findByHomeId(Long idHome);

    @Query(nativeQuery = true, value = "SELECT * FROM home WHERE home_id = :id")
    Optional<User> findUserById(@Param("id") Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM home " +
            "INNER JOIN user_home ON user_home.home_id = home.home_id " +
            "WHERE user_home.user_id = :id")
    List<Home> getHomeByIdUser(@Param("id") Long id);

    @Query(nativeQuery = true,
            value = "SELECT home.* FROM home " +
                    "INNER JOIN adress_home ON home.home_id = adress_home.home_id " +
                    "INNER JOIN building ON adress_home.building_id = building.building_id " +
                    "INNER JOIN adress ON adress.adress_id = building.adress_id " +
                    "INNER JOIN district ON district.district_id = adress.district_id " +
                    "INNER JOIN city ON city.city_id = district.city_id " +
                    "WHERE city.code = :codecity OR adress.content_detail = :adress " +
                    "OR district.code = :codedistrict")
    List<Home> getDataSearch(@Param("codecity") String codeCity, @Param("codedistrict") String codeDistrict, @Param("adress") String adress);

    @Query(nativeQuery = true, value = "SELECT home.price FROM home \n" +
            "            INNER JOIN acreage_home ON acreage_home.home_id = home.home_id  WHERE acreage_home.acreage_id = :idacreage")
    List<String> getAllPrice(@Param("idacreage") Long idAcreage);


}
