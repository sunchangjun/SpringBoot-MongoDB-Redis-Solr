package com.sun.common.list;

import org.hibernate.validator.constraints.EAN;

import java.util.List;

public class User {

    private  Integer id;
    private  String name;
    private List<Integer> list;

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
}
