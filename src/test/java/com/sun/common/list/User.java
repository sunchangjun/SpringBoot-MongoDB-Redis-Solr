package com.sun.common.list;


import java.util.List;

public class User {

    private  Integer id;
    private  String name;
    private List<Integer> list;
    
    public User(Integer id,String name) {
    	this.id =id;
    	this.name =name;
    }
    public User() {
    
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
    
}
