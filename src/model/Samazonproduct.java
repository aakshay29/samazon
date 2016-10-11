package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SAMAZONPRODUCT database table.
 * 
 */
@Entity
@NamedQuery(name="Samazonproduct.findAll", query="SELECT s FROM Samazonproduct s")
public class Samazonproduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long productid;

	private String description;

	private String image;

	private String name;

	private long price;

	private String seller;

	//bi-directional many-to-one association to Samazoncart
	@OneToMany(mappedBy="samazonproduct")
	private List<Samazoncart> samazoncarts;

	//bi-directional many-to-one association to Samazoncategory
	@ManyToOne
	@JoinColumn(name="CATEGORYID")
	private Samazoncategory samazoncategory;

	//bi-directional many-to-one association to Samazonrating
	@OneToMany(mappedBy="samazonproduct")
	private List<Samazonrating> samazonratings;

	//bi-directional many-to-one association to Samazonreview
	@OneToMany(mappedBy="samazonproduct")
	private List<Samazonreview> samazonreviews;

	//bi-directional many-to-one association to Samazonsale
	@OneToMany(mappedBy="samazonproduct")
	private List<Samazonsale> samazonsales;

	public Samazonproduct() {
	}

	public long getProductid() {
		return this.productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPrice() {
		return this.price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getSeller() {
		return this.seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public List<Samazoncart> getSamazoncarts() {
		return this.samazoncarts;
	}

	public void setSamazoncarts(List<Samazoncart> samazoncarts) {
		this.samazoncarts = samazoncarts;
	}

	public Samazoncart addSamazoncart(Samazoncart samazoncart) {
		getSamazoncarts().add(samazoncart);
		samazoncart.setSamazonproduct(this);

		return samazoncart;
	}

	public Samazoncart removeSamazoncart(Samazoncart samazoncart) {
		getSamazoncarts().remove(samazoncart);
		samazoncart.setSamazonproduct(null);

		return samazoncart;
	}

	public Samazoncategory getSamazoncategory() {
		return this.samazoncategory;
	}

	public void setSamazoncategory(Samazoncategory samazoncategory) {
		this.samazoncategory = samazoncategory;
	}

	public List<Samazonrating> getSamazonratings() {
		return this.samazonratings;
	}

	public void setSamazonratings(List<Samazonrating> samazonratings) {
		this.samazonratings = samazonratings;
	}

	public Samazonrating addSamazonrating(Samazonrating samazonrating) {
		getSamazonratings().add(samazonrating);
		samazonrating.setSamazonproduct(this);

		return samazonrating;
	}

	public Samazonrating removeSamazonrating(Samazonrating samazonrating) {
		getSamazonratings().remove(samazonrating);
		samazonrating.setSamazonproduct(null);

		return samazonrating;
	}

	public List<Samazonreview> getSamazonreviews() {
		return this.samazonreviews;
	}

	public void setSamazonreviews(List<Samazonreview> samazonreviews) {
		this.samazonreviews = samazonreviews;
	}

	public Samazonreview addSamazonreview(Samazonreview samazonreview) {
		getSamazonreviews().add(samazonreview);
		samazonreview.setSamazonproduct(this);

		return samazonreview;
	}

	public Samazonreview removeSamazonreview(Samazonreview samazonreview) {
		getSamazonreviews().remove(samazonreview);
		samazonreview.setSamazonproduct(null);

		return samazonreview;
	}

	public List<Samazonsale> getSamazonsales() {
		return this.samazonsales;
	}

	public void setSamazonsales(List<Samazonsale> samazonsales) {
		this.samazonsales = samazonsales;
	}

	public Samazonsale addSamazonsale(Samazonsale samazonsale) {
		getSamazonsales().add(samazonsale);
		samazonsale.setSamazonproduct(this);

		return samazonsale;
	}

	public Samazonsale removeSamazonsale(Samazonsale samazonsale) {
		getSamazonsales().remove(samazonsale);
		samazonsale.setSamazonproduct(null);

		return samazonsale;
	}

}