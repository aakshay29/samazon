package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SAMAZONRATING database table.
 * 
 */
@Entity
@NamedQuery(name="Samazonrating.findAll", query="SELECT s FROM Samazonrating s")
public class Samazonrating implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long ratingid;

	private long rating;

	//bi-directional many-to-one association to Samazonproduct
	@ManyToOne
	@JoinColumn(name="PRODUCTID")
	private Samazonproduct samazonproduct;

	//bi-directional many-to-one association to Samazonuser
	@ManyToOne
	@JoinColumn(name="USERID")
	private Samazonuser samazonuser;

	public Samazonrating() {
	}

	public long getRatingid() {
		return this.ratingid;
	}

	public void setRatingid(long ratingid) {
		this.ratingid = ratingid;
	}

	public long getRating() {
		return this.rating;
	}

	public void setRating(long rating) {
		this.rating = rating;
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