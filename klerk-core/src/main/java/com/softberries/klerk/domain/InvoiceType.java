package com.softberries.klerk.domain;

public enum InvoiceType {

	PRO_FORMA(0), 
	VAT(1);
	
	
	private final Integer value;
	
	private InvoiceType(Integer value){
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
}
