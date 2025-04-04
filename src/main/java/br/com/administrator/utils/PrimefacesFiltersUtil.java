package br.com.administrator.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;

import kotlin.Pair;

public class PrimefacesFiltersUtil {
	
	private Map<String, FilterMeta> filterBy;
	
	public PrimefacesFiltersUtil(Map<String, FilterMeta> filterBy) {
		super();
		this.filterBy = filterBy;
	}

	@SuppressWarnings("unchecked")
	public Pair<LocalDateTime, LocalDateTime> getDateTimeRangeFilter(String key) {
		List<LocalDate> filterValue = (List<LocalDate>) filterBy.get(key).getFilterValue();
		
		LocalDateTime start = LocalDateTime.of(filterValue.get(0), LocalTime.of(0, 0));
		LocalDateTime end = LocalDateTime.of(filterValue.get(1), LocalTime.of(23, 59));
		
		return new Pair<LocalDateTime, LocalDateTime>(start, end);
	}
	
	public <T extends Enum<T>> T getEnumFilterValue(String key, Class<T> enumType) {
		String value = filterBy.get(key).getFilterValue().toString();
        return Enum.valueOf(enumType, value);
	}
	
	public String getStringFilterValue(String key) {
		return filterBy.get(key).getFilterValue().toString();
	}
}
