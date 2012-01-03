package com.softberries.klerk.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String description;
	private String priceNet;
	private String tax;
	private String priceGross;
	private Date dateAdded;
	private boolean deleted;
	private User creator;
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy="product")
	private List<ProductPhoto> photos;
	

	@ManyToMany
	private List<ProductCategory> categories;
	
	@OneToMany
	private List<ProductAttribute> attributes;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriceNet() {
		return priceNet;
	}

	public void setPriceNet(String priceNet) {
		this.priceNet = priceNet;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getPriceGross() {
		return priceGross;
	}

	public void setPriceGross(String priceGross) {
		this.priceGross = priceGross;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public List<ProductPhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<ProductPhoto> photos) {
		this.photos = photos;
	}

	public List<ProductAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<ProductAttribute> attributes) {
		this.attributes = attributes;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public List<ProductCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<ProductCategory> categories) {
		this.categories = categories;
	}
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
}
