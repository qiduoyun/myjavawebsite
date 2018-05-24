package com.xiuxiuyu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class XssFilter implements Filter{
    FilterConfig filterConfig;
    private String filterChar;
    private String replaceChar;
    private String splitChar;
    
	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 HttpServletRequest httpServletRequest=null;
		 if(request instanceof HttpServletRequest){
			 httpServletRequest = (HttpServletRequest)request;
			 String uri = httpServletRequest.getRequestURI();
			 if(!uri.contains(".do")){
				 chain.doFilter(new XssHttpServletRequestWrapper(httpServletRequest, filterChar, replaceChar, splitChar), response);
			 }else{
				 chain.doFilter(request, response); 
			 }
		 }else{
			 chain.doFilter(request, response); 
		 } 
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterChar = filterConfig.getInitParameter("filterChar");
		this.replaceChar = filterConfig.getInitParameter("replaceChar");
		this.splitChar = filterConfig.getInitParameter("splitChar");
		this.filterConfig = filterConfig;
	}

}
