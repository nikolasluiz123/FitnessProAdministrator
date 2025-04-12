package br.com.administrator.utils;

import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public class CookiesUtils {
	
	public static void newCookie(String key, String value, long expiration) {
	    Cookie cookie = new Cookie(key, value);
	    cookie.setPath("/");
	    cookie.setMaxAge((int )expiration);
	    cookie.setHttpOnly(true);
	    
	    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	    
	    response.addCookie(cookie);
	}

}
