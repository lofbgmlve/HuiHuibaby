package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandProcess {
		public abstract String requestProcess(
				HttpServletRequest request, HttpServletResponse response) 
						throws ServletException, IOException;
			
			
			
			
		
}
