package ngodanghieu.doan.entities;
// Generated Dec 29, 2019 10:33:01 PM by Hibernate Tools 4.3.5.Final

import ngodanghieu.doan.response.OrderDeatailResponse;
import ngodanghieu.doan.response.OrderResponse;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Order generated by hbm2java
 */
@SqlResultSetMappings(value = {
		@SqlResultSetMapping(
				name = "OrderDetail",
				classes = @ConstructorResult(
						targetClass = OrderDeatailResponse.class,
						columns = {
								@ColumnResult(name = "order_code", type = String.class),
								@ColumnResult(name = "user_full_name", type = String.class),
								@ColumnResult(name = "user_phone", type = String.class),
								@ColumnResult(name = "namehome", type = String.class),
								@ColumnResult(name = "order_id", type = Long.class),
								@ColumnResult(name = "note", type = String.class),
								@ColumnResult(name = "datecreate", type = String.class)
						}
				)
		)
})
@NamedNativeQuery(
		name = "getOrderdetal",
		resultSetMapping = "OrderDetail",
		query = "SELECT `order`.order_code,user.user_full_name,user.user_phone, CONCAT(adress_home.name_home, ' - ',building.name) as namehome,`order`.note,DATE_FORMAT(`order`.order_date,'%d/%m/%Y') AS datecreate,`order`.order_id \n" +
				"FROM `order`,user,home,adress_home,building \n" +
				"WHERE `order`.home_id = home.home_id \n" +
				"AND `order`.`user_id` = user.user_id \n" +
				"AND home.home_id = adress_home.home_id \n" +
				"AND building.building_id = adress_home.building_id " +
				"AND `order`.order_code = :ordercode")


@Entity
@Table(name = "order", catalog = "ngodanghieu")
public class Order implements java.io.Serializable {

	private Long orderId;
	private Home home;
	private Status status;
	private User user;
	private Date orderDate;
	private Double totalPrice;
	private Double taxTotal;
	private String orderRequest;
	private String orderCode;
	private long paymentWith;
	private String note;
	private Set<OrderInfo> orderInfos = new HashSet<OrderInfo>(0);

	public Order() {
	}

	public Order(Home home, Status status, User user, Date orderDate, Double totalPrice, Double taxTotal,
			String orderRequest, String orderCode, long paymentWith) {
		this.home = home;
		this.status = status;
		this.user = user;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
		this.taxTotal = taxTotal;
		this.orderRequest = orderRequest;
		this.orderCode = orderCode;
		this.paymentWith = paymentWith;
	}

	public Order(Home home, Status status, User user, Date orderDate, Double totalPrice, Double taxTotal,
			String orderRequest, String orderCode, long paymentWith, Set<OrderInfo> orderInfos) {
		this.home = home;
		this.status = status;
		this.user = user;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
		this.taxTotal = taxTotal;
		this.orderRequest = orderRequest;
		this.orderCode = orderCode;
		this.paymentWith = paymentWith;
		this.orderInfos = orderInfos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "order_id", unique = true, nullable = false)
	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@Column(name = "note", unique = true, nullable = true)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
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
	@JoinColumn(name = "status", nullable = false)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "order_date", nullable = false, length = 10)
	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Column(name = "total_price", nullable = false, precision = 10, scale = 0)
	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "tax_total", nullable = false, precision = 10, scale = 0)
	public Double getTaxTotal() {
		return this.taxTotal;
	}

	public void setTaxTotal(Double taxTotal) {
		this.taxTotal = taxTotal;
	}

	@Column(name = "order_request", nullable = false, length = 65535)
	public String getOrderRequest() {
		return this.orderRequest;
	}

	public void setOrderRequest(String orderRequest) {
		this.orderRequest = orderRequest;
	}

	@Column(name = "order_code", nullable = false, length = 100)
	public String getOrderCode() {
		return this.orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	@Column(name = "payment_with", nullable = false, precision = 10, scale = 0)
	public long getPaymentWith() {
		return this.paymentWith;
	}

	public void setPaymentWith(long paymentWith) {
		this.paymentWith = paymentWith;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	public Set<OrderInfo> getOrderInfos() {
		return this.orderInfos;
	}

	public void setOrderInfos(Set<OrderInfo> orderInfos) {
		this.orderInfos = orderInfos;
	}

}
