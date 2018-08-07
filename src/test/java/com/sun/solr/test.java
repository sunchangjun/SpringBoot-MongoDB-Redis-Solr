package com.sun.solr;

import java.io.IOException;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import com.sun.base.BaseJunitTest;
import com.sun.solr.dao.ProductRepository;

public class test extends BaseJunitTest {
	@Autowired
	ProductRepository productRepository;

	@Autowired
	private SolrClient solrClient;
	
//	@Autowired
//	private SolrTemplate solrTemplate;

	@Test
	public void test1() throws SolrServerException, IOException {
		System.out.println(productRepository.count());;


	}

}
