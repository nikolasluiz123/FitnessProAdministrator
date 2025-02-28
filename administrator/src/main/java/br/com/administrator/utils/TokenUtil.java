package br.com.administrator.utils;

import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TokenUtil {

	private static final String TOKEN_SERVICE_KEY = "token_service";

	public static void saveToken(String token) {
	    Cookie cookie = new Cookie(TOKEN_SERVICE_KEY, token);
	    cookie.setPath("/");
	    cookie.setMaxAge(Integer.MAX_VALUE);
	    cookie.setHttpOnly(true);
	    
	    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	    
	    response.addCookie(cookie);
	}
	
	public String getToken() {
	    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    
	    Cookie[] cookies = request.getCookies();
	    
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals(TOKEN_SERVICE_KEY)) {
	                return cookie.getValue();
	            }
	        }
	    }
	    
	    return null;
	}
}
