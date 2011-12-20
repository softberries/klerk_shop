package com.softberries.klerk.domain;

public enum ProductAttributeType {

	SIMPLE(0),
	COMBO_BOX(1);
	
	private final Integer value;
	
	private ProductAttributeType(Integer value){
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
	
}
