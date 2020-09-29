/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.exceptions;

public class SpringException extends RuntimeException{
	private String exceptionMsg;
	
	public SpringException(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
	
	public String getExceptionMsg(){
		return this.exceptionMsg;
	}
	
	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
}
