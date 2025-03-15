package br.com.administrator.enums;

public enum EnumDateTimePatterns {
	DATE_TIME("dd/MM/yyyy HH:mm:ss"),
	DATE("dd/MM/yyyy");
	
	private String pattern;
	
	EnumDateTimePatterns(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}
	
}
