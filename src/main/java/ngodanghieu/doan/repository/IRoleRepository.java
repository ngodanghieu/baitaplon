package ngodanghieu.doan.repository;

import ngodanghieu.doan.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRoleRepository extends JpaRepository<Role,Long> {

    Role findByRoleCode(String codeRole);

    @Query(nativeQuery = true ,
            value = "SELECT role.role_code FROM role \n" +
                    "INNER JOIN user_role ON role.role_id = user_role.role_id \n" +
                    "INNER JOIN user ON user_role.user_id = user.user_id  \n" +
                    "WHERE user.user_phone = :phone")
    List<String> getListByPhone(@Param("phone") String phone);
}
