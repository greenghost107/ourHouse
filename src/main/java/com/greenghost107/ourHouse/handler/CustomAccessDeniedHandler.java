/*
 * Created by greenghost107 on Oct/2020
 */
package com.greenghost107.ourHouse.handler;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	
	private final Logger LOGGER = LogManager.getLogger(this.getClass());
//	private final Logger log= Logger.getLogger(this.getClass());

//    public static final Logger LOG
//      = Logger.getLogger(CustomAccessDeniedHandler.class);
	
	@Override
	public void handle(
			HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException exc) throws IOException, ServletException {
		
		Authentication auth
				= SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			LOGGER.warn("User: " + auth.getName()
					+ " attempted to access the protected URL: "
					+ request.getRequestURI());
		}
		System.out.println(request.getContextPath() + "/admin/error");
		response.sendRedirect(request.getContextPath() + "/admin/error");
	}
}
