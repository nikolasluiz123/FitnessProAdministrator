package br.com.administrator.utils;

public class StringUtils {

	public static Boolean isNullOrEmpty(String value) {
		return value == null || value.isEmpty();
	}
	
	public static Boolean isNotNull(String value) {
		return value != null && !value.trim().isEmpty();
	}
}
