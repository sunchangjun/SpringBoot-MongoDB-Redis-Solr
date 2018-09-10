package com.sun.Mongodb.entity;

import org.springframework.data.annotation.Id;

/**
 * 推荐类型
 * @author zhangsl
 */
public abstract class MongoRecoType {

	@Id
	protected String id;
	protected String name;
	protected int type;
	protected String _class;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String get_class() {
		return _class;
	}
	public void set_class(String _class) {
		this._class = _class;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
