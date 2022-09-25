package com.github.sanchezih.jersey.model;

import java.io.Serializable;

public class UserEntity implements Serializable {

	public String username;
	public String name;

	public UserEntity() {
	}

	public UserEntity(String username, String name) {
		this.username = username;
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}