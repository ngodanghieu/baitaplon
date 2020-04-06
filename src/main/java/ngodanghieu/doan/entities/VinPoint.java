package ngodanghieu.doan.entities;
// Generated Dec 29, 2019 10:33:01 PM by Hibernate Tools 4.3.5.Final

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * VinPoint generated by hbm2java
 */
@Entity
@Table(name = "point", catalog = "ngodanghieu")
public class VinPoint implements java.io.Serializable {

	private Long vinPointId;
	private User user;
	private int point;

	public VinPoint() {
	}

	public VinPoint(User user, int point) {
		this.user = user;
		this.point = point;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "vin_point_id", unique = true, nullable = false)
	public Long getVinPointId() {
		return this.vinPointId;
	}

	public void setVinPointId(Long vinPointId) {
		this.vinPointId = vinPointId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "point", nullable = false)
	public int getPoint() {
		return this.point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

}
