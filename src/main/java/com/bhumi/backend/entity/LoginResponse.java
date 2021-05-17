package com.bhumi.backend.entity;

import java.io.Serializable;

public class LoginResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String username;
	private final String jwttoken;

	public LoginResponse(String username, String jwttoken) {
		this.username = username;
		this.jwttoken = jwttoken;
	}

	public String getUsername() {
		return username;
	}

	public String getToken() {
		return this.jwttoken;
	}
}
