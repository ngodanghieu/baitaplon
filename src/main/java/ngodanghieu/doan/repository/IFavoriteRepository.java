package ngodanghieu.doan.repository;

import ngodanghieu.doan.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFavoriteRepository extends JpaRepository<Favorite,Long> {

    List<Favorite> findAllByUserId(Long userId);

    void removeByHomeIdAndUserId(Long homeId,Long userId);

    List<Favorite> findAllByUserIdAndHomeId(Long userId,Long homeId);
}
