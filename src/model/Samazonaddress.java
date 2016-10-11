package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SAMAZONADDRESS database table.
 * 
 */
@Entity
@NamedQuery(name="Samazonaddress.findAll", query="SELECT s FROM Samazonaddress s")
public class Samazonaddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long addressid;

	private String address;

	//bi-directional many-to-one association to Samazonuser
	@ManyToOne
	@JoinColumn(name="USERID")
	private Samazonuser samazonuser;

	//bi-directional many-to-one association to Samazonorder
	@OneToMany(mappedBy="samazonaddress")
	private List<Samazonorder> samazonorders;

	public Samazonaddress() {
	}

	public long getAddressid() {
		return this.addressid;
	}

	public void setAddressid(long addressid) {
		this.addressid = addressid;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Samazonuser getSamazonuser() {
		return this.samazonuser;
	}

	public void setSamazonuser(Samazonuser samazonuser) {
		this.samazonuser = samazonuser;
	}

	public List<Samazonorder> getSamazonorders() {
		return this.samazonorders;
	}

	public void setSamazonorders(List<Samazonorder> samazonorders) {
		this.samazonorders = samazonorders;
	}

	public Samazonorder addSamazonorder(Samazonorder samazonorder) {
		getSamazonorders().add(samazonorder);
		samazonorder.setSamazonaddress(this);

		return samazonorder;
	}

	public Samazonorder removeSamazonorder(Samazonorder samazonorder) {
		getSamazonorders().remove(samazonorder);
		samazonorder.setSamazonaddress(null);

		return samazonorder;
	}

}