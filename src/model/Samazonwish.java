package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SAMAZONWISH database table.
 * 
 */
@Entity
@NamedQuery(name="Samazonwish.findAll", query="SELECT s FROM Samazonwish s")
public class Samazonwish implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long wishid;

	private java.math.BigDecimal productid;

	private java.math.BigDecimal userid;

	public Samazonwish() {
	}

	public long getWishid() {
		return this.wishid;
	}

	public void setWishid(long wishid) {
		this.wishid = wishid;
	}

	public java.math.BigDecimal getProductid() {
		return this.productid;
	}

	public void setProductid(java.math.BigDecimal productid) {
		this.productid = productid;
	}

	public java.math.BigDecimal getUserid() {
		return this.userid;
	}

	public void setUserid(java.math.BigDecimal userid) {
		this.userid = userid;
	}

}