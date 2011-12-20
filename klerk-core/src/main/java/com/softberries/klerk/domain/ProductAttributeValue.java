package com.softberries.klerk.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="product_attribute_value")
public class ProductAttributeValue {

	
	@Id
	@GeneratedValue
	private Long id;
	private String value;
	
	@ManyToOne
	@JoinColumn(name="productAttribute")
	private ProductAttribute productAttribute;

	public ProductAttributeValue(){
		
	}
	public ProductAttributeValue(ProductAttribute attr, String value){
		this.productAttribute = attr;
		this.value = value;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ProductAttribute getProductAttribute() {
		return productAttribute;
	}

	public void setProductAttribute(ProductAttribute productAttribute) {
		this.productAttribute = productAttribute;
	}
	
	
}
