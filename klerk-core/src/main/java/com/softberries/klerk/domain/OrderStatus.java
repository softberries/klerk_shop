package com.softberries.klerk.domain;

public enum OrderStatus {

	NEW(0),
	PENDING(1), //offline payment selected during checkout
	VERIFYING(2), //online payment selected during checkout but order is awaiting approval
	AWAITING_PAYMENT(3),
	READY_TO_DISPATCH_WHEN_STOCK_ARRIVES(4),
	READY_TO_DISPATCH(5),
	READY_TO_DISPATCH_PICKED(6),
	READY_TO_DISPATCH_PACKED(7),
	DISPATCHED(8),
	RETURNED_NOT_REFUNDED(9),
	RETURNED_REFUNDED(10),
	CANCELLED(11),
	CLOSED(12);
	
	
	private final Integer value;
	
	private OrderStatus(Integer value){
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
}
