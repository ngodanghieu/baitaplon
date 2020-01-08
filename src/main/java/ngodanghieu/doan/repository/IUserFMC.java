package ngodanghieu.doan.repository;

import ngodanghieu.doan.entities.UserFcm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserFMC extends JpaRepository<UserFcm,Long> {
    @Query(nativeQuery = true , value = "SELECT * FROM user_fcm WHERE user_id=:user_id AND device_id =:device_id")
    UserFcm getByIdUserAndDevice(@Param("user_id") Long idUser, @Param("device_id") String deviceId);

    @Query(nativeQuery = true , value = "SELECT * FROM user_fcm WHERE user_id=:user_id")
    List<UserFcm> getByUser(@Param("user_id") Long idUSer);
}
