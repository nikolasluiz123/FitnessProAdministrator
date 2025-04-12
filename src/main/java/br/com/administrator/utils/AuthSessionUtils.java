package br.com.administrator.utils;

import java.time.Duration;
import java.util.List;

import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class AuthSessionUtils {

	private static final String TOKEN_SERVICE_KEY = "token_service";
	private static final String EMAIL_KEY = "email";
	private static final String PASSWORD_KEY = "password";

	public static void saveUserInfos(String token, String email, String password) {
		long sessionTime = Duration.ofHours(1).getSeconds();
		
		CookiesUtils.newCookie(TOKEN_SERVICE_KEY, token, sessionTime);
		CookiesUtils.newCookie(EMAIL_KEY, email, sessionTime);
		CookiesUtils.newCookie(PASSWORD_KEY, password, sessionTime);
	}
	
	public static String getCookieValue(String key) {
	    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    List<Cookie> cookies = List.of(request.getCookies());
	    
	    if (cookies != null && !cookies.isEmpty()) {
	    	List<Cookie> result = cookies.stream().filter(c -> c.getName().equals(key)).toList();
	    	
	    	if (result.isEmpty()) {
	    		return null;
	    	} else {
	    		return result.getFirst().getValue();
	    	}
	    } else {
	    	return null;
	    }
	}
	
	public static String getToken() {
	    return getCookieValue(TOKEN_SERVICE_KEY);
	}
	
	public static String getUserEmail() {
	    return getCookieValue(EMAIL_KEY);
	}
	
	public static String getUserPassword() {
	    return getCookieValue(PASSWORD_KEY);
	}
}
