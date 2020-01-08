package ngodanghieu.doan.repository;

import ngodanghieu.doan.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRoleRepository extends JpaRepository<UserRole,Long> {
}
