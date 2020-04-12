package ngodanghieu.doan.service;

import ngodanghieu.doan.entities.Comment;
import ngodanghieu.doan.entities.Ratings;
import ngodanghieu.doan.entities.User;
import ngodanghieu.doan.repository.ICommentRepository;
import ngodanghieu.doan.repository.IRatingRepository;
import ngodanghieu.doan.repository.IUserRepository;
import ngodanghieu.doan.response.CommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private ICommentRepository iCommentRepository;

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IRatingRepository iRatingRepository;

    public List<CommentResponse> getComment(Long homeId){
        List<Comment> data = iCommentRepository.findAllByHomeIdOrderByCreatedOnAsc(homeId);

        if (!CollectionUtils.isEmpty(data)){
            return data.stream().map( item -> {
                CommentResponse result = new CommentResponse();
                User user = iUserRepository.findByUserId(item.getUserId());
                if (user != null){
                    if (!StringUtils.isEmpty(user.getUserFullName())){
                        result.setNameUser(user.getUserFullName());
                    }else if (!StringUtils.isEmpty(user.getUserPhone())){
                        result.setNameUser(user.getUserPhone());
                    }else {
                        result.setNameUser("Boot");
                    }
                    List<Ratings> listR = iRatingRepository.findAllByHomeIdAndUserId(homeId,user.getUserId());

                    if (!CollectionUtils.isEmpty(listR)){
                        result.setStart(listR.get(0).getStars());
                    }else {
                        result.setStart(0f);
                    }
                }
                result.setContent(item.getContent());
                result.setCreatedOn(item.getCreatedOn());
                return result;
            }).collect(Collectors.toList());
        }else {
            return new ArrayList<>();
        }
    }

    public Boolean insertComment(String content, Long homeId,Long userId){

        try {
            Comment comment = new Comment();
            comment.setContent(content);
            comment.setCreatedBy(userId.toString());
            comment.setCreatedOn(new Date());
            comment.setHomeId(homeId);
            comment.setModifiedOn(new Date());
            comment.setModifiedBy(userId.toString());
            iCommentRepository.save(comment);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public Boolean rating(float start , Long userId, Long homeId){
        try {
          Ratings ratings = new Ratings();
          ratings.setHomeId(homeId);
          ratings.setStars(start);
          ratings.setUserId(userId);
          iRatingRepository.save(ratings);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
