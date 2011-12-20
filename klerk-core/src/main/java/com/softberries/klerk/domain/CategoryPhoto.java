package com.softberries.klerk.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="category_photo")
public class CategoryPhoto extends StorePhoto {

	@ManyToOne
	@JoinColumn(name="category_photo")
	private ProductCategory category;

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	
	
	
}
