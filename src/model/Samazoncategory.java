package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SAMAZONCATEGORY database table.
 * 
 */
@Entity
@NamedQuery(name="Samazoncategory.findAll", query="SELECT s FROM Samazoncategory s")
public class Samazoncategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long categoryid;

	private String name;

	//bi-directional many-to-one association to Samazonproduct
	@OneToMany(mappedBy="samazoncategory")
	private List<Samazonproduct> samazonproducts;

	public Samazoncategory() {
	}

	public long getCategoryid() {
		return this.categoryid;
	}

	public void setCategoryid(long categoryid) {
		this.categoryid = categoryid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Samazonproduct> getSamazonproducts() {
		return this.samazonproducts;
	}

	public void setSamazonproducts(List<Samazonproduct> samazonproducts) {
		this.samazonproducts = samazonproducts;
	}

	public Samazonproduct addSamazonproduct(Samazonproduct samazonproduct) {
		getSamazonproducts().add(samazonproduct);
		samazonproduct.setSamazoncategory(this);

		return samazonproduct;
	}

	public Samazonproduct removeSamazonproduct(Samazonproduct samazonproduct) {
		getSamazonproducts().remove(samazonproduct);
		samazonproduct.setSamazoncategory(null);

		return samazonproduct;
	}

}