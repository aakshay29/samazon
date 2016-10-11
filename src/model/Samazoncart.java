package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SAMAZONCART database table.
 * 
 */
@Entity
@NamedQuery(name="Samazoncart.findAll", query="SELECT s FROM Samazoncart s")
public class Samazoncart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long cartid;

	private long productordered;

	//bi-directional many-to-one association to Samazonproduct
	@ManyToOne
	@JoinColumn(name="PRODUCTID")
	private Samazonproduct samazonproduct;

	//bi-directional many-to-one association to Samazonuser
	@ManyToOne
	@JoinColumn(name="USERID")
	private Samazonuser samazonuser;

	//bi-directional many-to-one association to Samazonorder
	@OneToMany(mappedBy="samazoncart")
	private List<Samazonorder> samazonorders;

	public Samazoncart() {
	}

	public long getCartid() {
		return this.cartid;
	}

	public void setCartid(long cartid) {
		this.cartid = cartid;
	}

	public long getProductordered() {
		return this.productordered;
	}

	public void setProductordered(long productordered) {
		this.productordered = productordered;
	}

	public Samazonproduct getSamazonproduct() {
		return this.samazonproduct;
	}

	public void setSamazonproduct(Samazonproduct samazonproduct) {
		this.samazonproduct = samazonproduct;
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
		samazonorder.setSamazoncart(this);

		return samazonorder;
	}

	public Samazonorder removeSamazonorder(Samazonorder samazonorder) {
		getSamazonorders().remove(samazonorder);
		samazonorder.setSamazoncart(null);

		return samazonorder;
	}

}