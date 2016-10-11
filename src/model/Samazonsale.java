package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SAMAZONSALE database table.
 * 
 */
@Entity
@NamedQuery(name="Samazonsale.findAll", query="SELECT s FROM Samazonsale s")
public class Samazonsale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long saleid;

	//bi-directional many-to-one association to Samazonproduct
	@ManyToOne
	@JoinColumn(name="PRODUCTID")
	private Samazonproduct samazonproduct;

	//bi-directional many-to-one association to Samazonuser
	@ManyToOne
	@JoinColumn(name="USERID")
	private Samazonuser samazonuser;

	public Samazonsale() {
	}

	public long getSaleid() {
		return this.saleid;
	}

	public void setSaleid(long saleid) {
		this.saleid = saleid;
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

}