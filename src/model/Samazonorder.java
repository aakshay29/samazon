package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SAMAZONORDER database table.
 * 
 */
@Entity
@NamedQuery(name="Samazonorder.findAll", query="SELECT s FROM Samazonorder s")
public class Samazonorder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long orderid;

	@Temporal(TemporalType.DATE)
	private Date dateplaced;

	//bi-directional many-to-one association to Samazonaddress
	@ManyToOne
	@JoinColumn(name="ADDRESSID")
	private Samazonaddress samazonaddress;

	//bi-directional many-to-one association to Samazoncart
	@ManyToOne
	@JoinColumn(name="CARTID")
	private Samazoncart samazoncart;

	public Samazonorder() {
	}

	public long getOrderid() {
		return this.orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	public Date getDateplaced() {
		return this.dateplaced;
	}

	public void setDateplaced(Date dateplaced) {
		this.dateplaced = dateplaced;
	}

	public Samazonaddress getSamazonaddress() {
		return this.samazonaddress;
	}

	public void setSamazonaddress(Samazonaddress samazonaddress) {
		this.samazonaddress = samazonaddress;
	}

	public Samazoncart getSamazoncart() {
		return this.samazoncart;
	}

	public void setSamazoncart(Samazoncart samazoncart) {
		this.samazoncart = samazoncart;
	}

}