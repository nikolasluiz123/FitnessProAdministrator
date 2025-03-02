package br.com.administrator.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.administrator.enums.EnumDateTimePatterns;

public class TimeConverterUtil {

	public static LocalDateTime parseLocalDateTime(String value, EnumDateTimePatterns pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.getPattern());
		return LocalDateTime.parse(value, formatter);
	}
	
	public static LocalDate parseLocalDate(String value, EnumDateTimePatterns pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.getPattern());
		return LocalDate.parse(value, formatter);
	}
	
	public static String formatLocalDateTime(LocalDateTime value, EnumDateTimePatterns pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.getPattern());
		return formatter.format(value);
	}
	
	public static String formatLocalDate(LocalDate value, EnumDateTimePatterns pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.getPattern());
		return formatter.format(value);
	}
}
