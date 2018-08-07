package com.sun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.solr.dao.ProductRepository;

@RestController
@RequestMapping("/solr")
public class SolrController {
	@Autowired
	ProductRepository productRepository;
	
	 @Autowired
	  private SolrTemplate solrTemplate;
	 
	 public void findBiId() {
		
		
	 }

}
