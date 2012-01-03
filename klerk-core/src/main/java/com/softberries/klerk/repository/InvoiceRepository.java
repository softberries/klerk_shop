package com.softberries.klerk.repository;

import java.util.Set;

import com.softberries.klerk.domain.Invoice;

public interface InvoiceRepository {

	void save(Invoice o);

	Invoice getById(Long id);

	Set<Invoice> fetchAll();
	
	void delete(Long id);
}
