package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SAMAZONUSER database table.
 * 
 */
@Entity
@NamedQuery(name="Samazonuser.findAll", query="SELECT s FROM Samazonuser s")
public class Samazonuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long userid;

	private String email;

	private String name;

	private String password;

	private String phone;

	@Column(name="\"ROLE\"")
	private long role;

	private String username;

	//bi-directional many-to-one association to Samazonaddress
	@OneToMany(mappedBy="samazonuser")
	private List<Samazonaddress> samazonaddresses;

	//bi-directional many-to-one association to Samazoncart
	@OneToMany(mappedBy="samazonuser")
	private List<Samazoncart> samazoncarts;

	//bi-directional many-to-one association to Samazonrating
	@OneToMany(mappedBy="samazonuser")
	private List<Samazonrating> samazonratings;

	//bi-directional many-to-one association to Samazonreview
	@OneToMany(mappedBy="samazonuser")
	private List<Samazonreview> samazonreviews;

	//bi-directional many-to-one association to Samazonsale
	@OneToMany(mappedBy="samazonuser")
	private List<Samazonsale> samazonsales;

	public Samazonuser() {
	}

	public long getUserid() {
		return this.userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getRole() {
		return this.role;
	}

	public void setRole(long role) {
		this.role = role;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Samazonaddress> getSamazonaddresses() {
		return this.samazonaddresses;
	}

	public void setSamazonaddresses(List<Samazonaddress> samazonaddresses) {
		this.samazonaddresses = samazonaddresses;
	}

	public Samazonaddress addSamazonaddress(Samazonaddress samazonaddress) {
		getSamazonaddresses().add(samazonaddress);
		samazonaddress.setSamazonuser(this);

		return samazonaddress;
	}

	public Samazonaddress removeSamazonaddress(Samazonaddress samazonaddress) {
		getSamazonaddresses().remove(samazonaddress);
		samazonaddress.setSamazonuser(null);

		return samazonaddress;
	}

	public List<Samazoncart> getSamazoncarts() {
		return this.samazoncarts;
	}

	public void setSamazoncarts(List<Samazoncart> samazoncarts) {
		this.samazoncarts = samazoncarts;
	}

	public Samazoncart addSamazoncart(Samazoncart samazoncart) {
		getSamazoncarts().add(samazoncart);
		samazoncart.setSamazonuser(this);

		return samazoncart;
	}

	public Samazoncart removeSamazoncart(Samazoncart samazoncart) {
		getSamazoncarts().remove(samazoncart);
		samazoncart.setSamazonuser(null);

		return samazoncart;
	}

	public List<Samazonrating> getSamazonratings() {
		return this.samazonratings;
	}

	public void setSamazonratings(List<Samazonrating> samazonratings) {
		this.samazonratings = samazonratings;
	}

	public Samazonrating addSamazonrating(Samazonrating samazonrating) {
		getSamazonratings().add(samazonrating);
		samazonrating.setSamazonuser(this);

		return samazonrating;
	}

	public Samazonrating removeSamazonrating(Samazonrating samazonrating) {
		getSamazonratings().remove(samazonrating);
		samazonrating.setSamazonuser(null);

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
		samazonreview.setSamazonuser(this);

		return samazonreview;
	}

	public Samazonreview removeSamazonreview(Samazonreview samazonreview) {
		getSamazonreviews().remove(samazonreview);
		samazonreview.setSamazonuser(null);

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
		samazonsale.setSamazonuser(this);

		return samazonsale;
	}

	public Samazonsale removeSamazonsale(Samazonsale samazonsale) {
		getSamazonsales().remove(samazonsale);
		samazonsale.setSamazonuser(null);

		return samazonsale;
	}

}