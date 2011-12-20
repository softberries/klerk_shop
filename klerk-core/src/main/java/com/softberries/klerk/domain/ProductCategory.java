package com.softberries.klerk.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="product_category")
public class ProductCategory{

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private boolean main;

	@OneToMany(mappedBy="category")
	private List<CategoryPhoto> photos;
	
	@ManyToOne
	@JoinColumn(name = "parentCategory")
	public ProductCategory parentCategory;

	@OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
	public List<ProductCategory> subCategories;

	public ProductCategory(ProductCategory parentCategory, String name, boolean main) {
		this.name = name;
		this.parentCategory = parentCategory;
		this.subCategories = new ArrayList<ProductCategory>();
		this.main = main;
	}

    public ProductCategory() {
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<CategoryPhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<CategoryPhoto> photos) {
		this.photos = photos;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductCategory getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(ProductCategory parentCategory) {
		this.parentCategory = parentCategory;
	}

	public List<ProductCategory> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<ProductCategory> subCategories) {
		this.subCategories = subCategories;
	}

	public boolean isMain() {
		return main;
	}

	public void setMain(boolean main) {
		this.main = main;
	}
	
	
}