package ngodanghieu.doan.entities;
// Generated Dec 29, 2019 10:33:01 PM by Hibernate Tools 4.3.5.Final

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * User generated by hbm2java
 */
@Entity
//@Table(name = "user", catalog = "ngodanghieu")
@Table(name = "user")
public class User implements java.io.Serializable {

	private Long userId;
	private Status status;
	private String userName;
	private String userFullName;
	private String userPhone;
	private String userEmail;
	private String userHash;
	private String userOptCode;
	private Date userExpiredOtp;
	private int userProvider;
	private String userFacebookId;
	private String userAuthToken;
	private Date userCreatedOn;
	private String userCreatedBy;
	private Date userModifiedOn;
	private String userModifiedBy;
	private Set<UserHome> userHomes = new HashSet<UserHome>(0);
	private Set<OrderHistory> orderHistories = new HashSet<OrderHistory>(0);
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);
	private Set<Order> orders = new HashSet<Order>(0);
	private Set<VinPoint> vinPoints = new HashSet<VinPoint>(0);
	private Set<UserFcm> userFcms = new HashSet<UserFcm>(0);

	public User() {
	}

	public User(Status status, String userName, String userFullName, String userPhone, String userEmail,
			String userHash, String userOptCode, Date userExpiredOtp, int userProvider, String userFacebookId,
			String userAuthToken, Date userCreatedOn, String userCreatedBy, Date userModifiedOn,
			String userModifiedBy) {
		this.status = status;
		this.userName = userName;
		this.userFullName = userFullName;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.userHash = userHash;
		this.userOptCode = userOptCode;
		this.userExpiredOtp = userExpiredOtp;
		this.userProvider = userProvider;
		this.userFacebookId = userFacebookId;
		this.userAuthToken = userAuthToken;
		this.userCreatedOn = userCreatedOn;
		this.userCreatedBy = userCreatedBy;
		this.userModifiedOn = userModifiedOn;
		this.userModifiedBy = userModifiedBy;
	}

	public User(Status status, String userName, String userFullName, String userPhone, String userEmail,
			String userHash, String userOptCode, Date userExpiredOtp, int userProvider, String userFacebookId,
			String userAuthToken, Date userCreatedOn, String userCreatedBy, Date userModifiedOn, String userModifiedBy,
			Set<UserHome> userHomes, Set<OrderHistory> orderHistories, Set<UserRole> userRoles, Set<Order> orders,
			Set<VinPoint> vinPoints , Set<UserFcm> userFcms) {
		this.status = status;
		this.userName = userName;
		this.userFullName = userFullName;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.userHash = userHash;
		this.userOptCode = userOptCode;
		this.userExpiredOtp = userExpiredOtp;
		this.userProvider = userProvider;
		this.userFacebookId = userFacebookId;
		this.userAuthToken = userAuthToken;
		this.userCreatedOn = userCreatedOn;
		this.userCreatedBy = userCreatedBy;
		this.userModifiedOn = userModifiedOn;
		this.userModifiedBy = userModifiedBy;
		this.userHomes = userHomes;
		this.orderHistories = orderHistories;
		this.userRoles = userRoles;
		this.orders = orders;
		this.vinPoints = vinPoints;
		this.userFcms = userFcms;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "user_id", unique = true, nullable = true)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "user_status", nullable = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_status", nullable = false)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Column(name = "user_name", nullable = true, length = 200)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "user_full_name", nullable = true)
	public String getUserFullName() {
		return this.userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	@Column(name = "user_phone", nullable = true, length = 20)
	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	@Column(name = "user_email", nullable = true)
	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Column(name = "user_hash", nullable = true)
	public String getUserHash() {
		return this.userHash;
	}

	public void setUserHash(String userHash) {
		this.userHash = userHash;
	}

	@Column(name = "user_opt_code", nullable = true)
	public String getUserOptCode() {
		return this.userOptCode;
	}

	public void setUserOptCode(String userOptCode) {
		this.userOptCode = userOptCode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "user_expired_otp", nullable = true, length = 10)
	public Date getUserExpiredOtp() {
		return this.userExpiredOtp;
	}

	public void setUserExpiredOtp(Date userExpiredOtp) {
		this.userExpiredOtp = userExpiredOtp;
	}

	@Column(name = "user_provider", nullable = true)
	public int getUserProvider() {
		return this.userProvider;
	}

	public void setUserProvider(int userProvider) {
		this.userProvider = userProvider;
	}

	@Column(name = "user_facebook_id", nullable = true)
	public String getUserFacebookId() {
		return this.userFacebookId;
	}

	public void setUserFacebookId(String userFacebookId) {
		this.userFacebookId = userFacebookId;
	}

	@Column(name = "user_auth_token", nullable = true)
	public String getUserAuthToken() {
		return this.userAuthToken;
	}

	public void setUserAuthToken(String userAuthToken) {
		this.userAuthToken = userAuthToken;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "user_created_on", nullable = true, length = 10)
	public Date getUserCreatedOn() {
		return this.userCreatedOn;
	}

	public void setUserCreatedOn(Date userCreatedOn) {
		this.userCreatedOn = userCreatedOn;
	}

	@Column(name = "user_created_by", nullable = true, length = 100)
	public String getUserCreatedBy() {
		return this.userCreatedBy;
	}

	public void setUserCreatedBy(String userCreatedBy) {
		this.userCreatedBy = userCreatedBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "user_modified_on", nullable = true, length = 10)
	public Date getUserModifiedOn() {
		return this.userModifiedOn;
	}

	public void setUserModifiedOn(Date userModifiedOn) {
		this.userModifiedOn = userModifiedOn;
	}

	@Column(name = "user_modified_by", nullable = true, length = 100)
	public String getUserModifiedBy() {
		return this.userModifiedBy;
	}

	public void setUserModifiedBy(String userModifiedBy) {
		this.userModifiedBy = userModifiedBy;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserHome> getUserHomes() {
		return this.userHomes;
	}

	public void setUserHomes(Set<UserHome> userHomes) {
		this.userHomes = userHomes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<OrderHistory> getOrderHistories() {
		return this.orderHistories;
	}

	public void setOrderHistories(Set<OrderHistory> orderHistories) {
		this.orderHistories = orderHistories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<VinPoint> getVinPoints() {
		return this.vinPoints;
	}

	public void setVinPoints(Set<VinPoint> vinPoints) {
		this.vinPoints = vinPoints;
	}


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserFcm> getUserFcms() {
		return this.userFcms;
	}

	public void setUserFcms(Set<UserFcm> userFcms) {
		this.userFcms = userFcms;
	}


}
