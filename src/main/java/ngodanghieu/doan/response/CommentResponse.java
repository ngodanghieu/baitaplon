package ngodanghieu.doan.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private String imageUrl;
    private String nameUser;
    private float start;
    private String content;
    private Date createdOn;
}
