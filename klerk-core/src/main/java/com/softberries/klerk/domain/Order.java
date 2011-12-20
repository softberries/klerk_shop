package com.softberries.klerk.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="order")
public class Order {

	@Id
	@GeneratedValue
	private Long id;
	private Date dateStarted;
	private Date dateFinished;
	
	@OneToMany
	private List<Product> products;
}
