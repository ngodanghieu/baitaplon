package ngodanghieu.doan.entities;
// Generated Dec 29, 2019 10:33:01 PM by Hibernate Tools 4.3.5.Final

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Acreage generated by hbm2java
 */
@Entity
@Table(name = "acreage", catalog = "vinid_home")
public class Acreage implements java.io.Serializable {

	private Long acreageId;
	private float height;
	private float width;
	private float totalArea;
	private Set<AcreageHome> acreageHomes = new HashSet<AcreageHome>(0);

	public Acreage() {
	}

	public Acreage(float height, float width, float totalArea) {
		this.height = height;
		this.width = width;
		this.totalArea = totalArea;
	}

	public Acreage(float height, float width, float totalArea, Set<AcreageHome> acreageHomes) {
		this.height = height;
		this.width = width;
		this.totalArea = totalArea;
		this.acreageHomes = acreageHomes;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "acreage_id", unique = true, nullable = false)
	public Long getAcreageId() {
		return this.acreageId;
	}

	public void setAcreageId(Long acreageId) {
		this.acreageId = acreageId;
	}

	@Column(name = "height", nullable = false, precision = 12, scale = 0)
	public float getHeight() {
		return this.height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	@Column(name = "width", nullable = false, precision = 12, scale = 0)
	public float getWidth() {
		return this.width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	@Column(name = "total_area", nullable = false, precision = 12, scale = 0)
	public float getTotalArea() {
		return this.totalArea;
	}

	public void setTotalArea(float totalArea) {
		this.totalArea = totalArea;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "acreage")
	public Set<AcreageHome> getAcreageHomes() {
		return this.acreageHomes;
	}

	public void setAcreageHomes(Set<AcreageHome> acreageHomes) {
		this.acreageHomes = acreageHomes;
	}

}