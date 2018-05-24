package com.xiuxiuyu.filter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.xiuxiuyu.util.StringUtils;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper{
    private String[] filterChars;
    private String[] replaceChars;
	public XssHttpServletRequestWrapper(HttpServletRequest request,String filterChar,String replaceChar,String splitChar) {
		super(request);
		if(!StringUtils.isEmpty(filterChar)){
			filterChars = filterChar.split(splitChar);
		}
		if(!StringUtils.isEmpty(replaceChar)){
			replaceChars = replaceChar.split(splitChar);
		}
	}
	
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(xssEncode(name));
		if(!StringUtils.isEmpty(value)){
			value = xssEncode(value);
		}
		return value;
	}
	
	@Override
	public String getQueryString() {
		String value = super.getQueryString();
		if(!StringUtils.isEmpty(value)){
			value = xssEncode(value);
		}
		return value;
	}
	
	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if(values == null || values.length==0){
			return values;
		}
		for(int i=0;i<values.length;i++){
			values[i] = xssEncode(values[i]);
		}
		return values;
	}
	
	@Override
	public String getHeader(String name) {
		String value = super.getHeader(xssEncode(name));
		if(!StringUtils.isEmpty(value)){
			value = xssEncode(value);
		}
		return value;
	}
	/**
	 * 进行xss处理
	 * @param s
	 * @return
	 */
	private String xssEncode(String s){
		if(StringUtils.isEmpty(s)){
			return s;
		}
		try {
			s = URLDecoder.decode(s, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(filterChars!=null&&filterChars.length>0){
			for(int i=0;i<filterChars.length;i++){
			  s = s.replace(filterChars[i], replaceChars[i]);
			}
		}
		return s;
	}
}
