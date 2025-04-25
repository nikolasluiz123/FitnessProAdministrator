package br.com.administrator.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Locale.Category;

import br.com.administrator.enums.EnumDateTimePatterns;

public class TimeConverterUtil {

	public static LocalDateTime parseLocalDateTime(String value, EnumDateTimePatterns pattern) {
	    if (StringUtils.isNullOrEmpty(value)) return null;

	    DateTimeFormatter formatter = getFormatterOfPatternWithLocale(pattern);
	    LocalDateTime localDateTime = LocalDateTime.parse(value, formatter);

	    return localDateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
	}
	
	public static LocalDate parseLocalDate(String value, EnumDateTimePatterns pattern) {
		if (StringUtils.isNullOrEmpty(value)) return null;
		
		DateTimeFormatter formatter = getFormatterOfPatternWithLocale(pattern);
		return LocalDate.parse(value, formatter);
	}

	public static String formatLocalDateTime(LocalDateTime value, EnumDateTimePatterns pattern) {
	    if (value == null) return null;

	    ZonedDateTime zonedDateTime = value.atZone(ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault());
	    DateTimeFormatter formatter = getFormatterOfPatternWithLocale(pattern).withZone(ZoneId.systemDefault());

	    return formatter.format(zonedDateTime);
	}
	
	public static String formatLocalDate(LocalDate value, EnumDateTimePatterns pattern) {
		if (value == null) return null;
		
		DateTimeFormatter formatter = getFormatterOfPatternWithLocale(pattern);
		return formatter.format(value);
	}
	
	private static DateTimeFormatter getFormatterOfPatternWithLocale(EnumDateTimePatterns pattern) {
		return DateTimeFormatter.ofPattern(pattern.getPattern(), Locale.getDefault(Category.FORMAT));
	}
}
