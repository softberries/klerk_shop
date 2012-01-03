package com.softberries.klerk.domain;

public enum OrderStatus {

	PENDING(0), //offline payment selected during checkout
	VERIFYING(1), //online payment selected during checkout but order is awaiting approval
	AWAITING_PAYMENT(2),
	READY_TO_DISPATCH_WHEN_STOCK_ARRIVES(3),
	READY_TO_DISPATCH(4),
	READY_TO_DISPATCH_PICKED(5),
	READY_TO_DISPATCH_PACKED(6),
	DISPATCHED(7),
	RETURNED_NOT_REFUNDED(8),
	RETURNED_REFUNDED(9),
	CANCELLED(10),
	CLOSED(11);
	
	
	private final Integer value;
	
	private OrderStatus(Integer value){
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
}
