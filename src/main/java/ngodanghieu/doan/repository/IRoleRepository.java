package ngodanghieu.doan.repository;

import ngodanghieu.doan.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role,Long> {

    Role findByRoleCode(String codeRole);
}
