/*
 * Created by greenghost107 on Oct/2020
 */
package com.greenghost107.ourHouse.model.security;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private final String jwttoken;

	private final String jwtRefreshtoken;
	
	public JwtResponse(String jwttoken, String jwtRefreshtoken) {
		this.jwttoken = jwttoken;
		this.jwtRefreshtoken = jwtRefreshtoken;
	}
	
	public String getToken() {
		return this.jwttoken;
	}

	public String getJwtRefreshtoken() {
		return jwtRefreshtoken;
	}
}
