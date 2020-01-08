package ngodanghieu.doan.entities;
// Generated Dec 29, 2019 10:33:01 PM by Hibernate Tools 4.3.5.Final

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * HomeWorktime generated by hbm2java
 */
@Entity
@Table(name = "home_worktime", catalog = "vinid_home")
public class HomeWorktime implements java.io.Serializable {

	private Integer homeWorktimeId;
	private Home home;
	private String closeTime;
	private String openTime;
	private String weekday;
	private Date createdOn;
	private String createdBy;
	private Date modifiedOn;
	private String modifiedBy;

	public HomeWorktime() {
	}

	public HomeWorktime(Home home, String closeTime, String openTime, String weekday, Date createdOn, String createdBy,
			Date modifiedOn, String modifiedBy) {
		this.home = home;
		this.closeTime = closeTime;
		this.openTime = openTime;
		this.weekday = weekday;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.modifiedOn = modifiedOn;
		this.modifiedBy = modifiedBy;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "home_worktime_id", unique = true, nullable = false)
	public Integer getHomeWorktimeId() {
		return this.homeWorktimeId;
	}

	public void setHomeWorktimeId(Integer homeWorktimeId) {
		this.homeWorktimeId = homeWorktimeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "home_id", nullable = false)
	public Home getHome() {
		return this.home;
	}

	public void setHome(Home home) {
		this.home = home;
	}

	@Column(name = "close_time", nullable = false, length = 50)
	public String getCloseTime() {
		return this.closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	@Column(name = "open_time", nullable = false, length = 50)
	public String getOpenTime() {
		return this.openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	@Column(name = "weekday", nullable = false, length = 50)
	public String getWeekday() {
		return this.weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "created_on", nullable = false, length = 10)
	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "created_by", nullable = false, length = 100)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "modified_on", nullable = false, length = 10)
	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	@Column(name = "modified_by", nullable = false, length = 100)
	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
