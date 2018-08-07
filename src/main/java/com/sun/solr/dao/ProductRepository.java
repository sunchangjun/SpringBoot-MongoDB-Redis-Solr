package com.sun.solr.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.SolrCrudRepository;

import com.sun.solr.entity.Product;

public interface ProductRepository  extends SolrCrudRepository<Product, String>{
	
	 Page<Product> findByContentMatches(String content, Pageable pageable);

}
