package ngodanghieu.doan.entities;
// Generated Dec 29, 2019 10:33:01 PM by Hibernate Tools 4.3.5.Final

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * OrderInfo generated by hbm2java
 */
@Entity
@Table(name = "order_info", catalog = "vinid_home")
public class OrderInfo implements java.io.Serializable {

	private Integer orderInfoId;
	private Home home;
	private Order order;
	private String openTime;
	private String closeTime;
	private String weekday;
	private String adress;

	public OrderInfo() {
	}

	public OrderInfo(Home home, Order order, String openTime, String closeTime, String weekday, String adress) {
		this.home = home;
		this.order = order;
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.weekday = weekday;
		this.adress = adress;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "order_info_id", unique = true, nullable = false)
	public Integer getOrderInfoId() {
		return this.orderInfoId;
	}

	public void setOrderInfoId(Integer orderInfoId) {
		this.orderInfoId = orderInfoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "home_id", nullable = false)
	public Home getHome() {
		return this.home;
	}

	public void setHome(Home home) {
		this.home = home;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Column(name = "open_time", nullable = false, length = 50)
	public String getOpenTime() {
		return this.openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	@Column(name = "close_time", nullable = false, length = 50)
	public String getCloseTime() {
		return this.closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	@Column(name = "weekday", nullable = false, length = 50)
	public String getWeekday() {
		return this.weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	@Column(name = "adress", nullable = false)
	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

}
