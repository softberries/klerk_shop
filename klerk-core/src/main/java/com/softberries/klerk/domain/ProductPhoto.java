package com.softberries.klerk.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProductPhoto extends StorePhoto implements Serializable{

	@ManyToOne
	@JoinColumn(name="product_photo")
	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
