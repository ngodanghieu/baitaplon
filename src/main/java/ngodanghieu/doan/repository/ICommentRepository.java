package ngodanghieu.doan.repository;

import ngodanghieu.doan.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllByHomeIdOrderByCreatedOnAsc(Long idHome);

}
