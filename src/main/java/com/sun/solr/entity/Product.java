package com.sun.solr.entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * @author lcc
 */
@SolrDocument(solrCoreName = "mycode")
//@data
public class Product {

  @Id
  @Field
  private String id;

  @Field(value = "CAPTURE_TIME")
  private long captureTime;


  @Field(value = "DETAILS")
  private String details;


  @Field(value = "CONTENT")
  private String content;

  @Field(value = "INFO_TYP")
  private int infoTyp;

  @Field(value = "_version_")
  private long version;

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public long getCaptureTime() {
	return captureTime;
}

public void setCaptureTime(long captureTime) {
	this.captureTime = captureTime;
}

public String getDetails() {
	return details;
}

public void setDetails(String details) {
	this.details = details;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

public int getInfoTyp() {
	return infoTyp;
}

public void setInfoTyp(int infoTyp) {
	this.infoTyp = infoTyp;
}

public long getVersion() {
	return version;
}

public void setVersion(long version) {
	this.version = version;
}
  
  
}
