/*
 * Created by greenghost107 on Oct/2020
 */
package com.greenghost107.ourHouse.model.security;

import java.io.Serializable;

public class JwtResponse implements Serializable {
	
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	
	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}
	
	public String getToken() {
		return this.jwttoken;
	}
}
