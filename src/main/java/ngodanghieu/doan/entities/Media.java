package ngodanghieu.doan.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity()
@Table(name = "media", catalog = "ngodanghieu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Media {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "media_id", unique = true, nullable = false)
    private Long mediaId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "type")
    private String type;

    @Column(name = "home_id")
    private Long homeId;

    @Column(name = "url")
    private String url;
}
