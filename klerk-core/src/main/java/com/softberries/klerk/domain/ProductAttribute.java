package com.softberries.klerk.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="product_attribute")
public class ProductAttribute {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private boolean main;
	
	@OneToMany(mappedBy="productAttribute")
	private List<ProductAttributeValue> values;
	
	@NotNull
	@Enumerated
	private ProductAttributeType type;

	public ProductAttribute(){
		
	}
	public ProductAttribute(String name, ProductAttributeType type, boolean main){
		this.name = name;
		this.type = type;
		this.main = main;
	}
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

	public List<ProductAttributeValue> getValues() {
		return values;
	}

	public void setValues(List<ProductAttributeValue> values) {
		this.values = values;
	}

	public boolean isMain() {
		return main;
	}

	public void setMain(boolean main) {
		this.main = main;
	}
	public ProductAttributeType getType() {
		return type;
	}
	public void setType(ProductAttributeType type) {
		this.type = type;
	}
	
}
