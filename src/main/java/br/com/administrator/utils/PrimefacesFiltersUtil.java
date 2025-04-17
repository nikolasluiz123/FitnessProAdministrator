package br.com.administrator.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import br.com.fitnesspro.shared.communication.query.sort.IEnumFields;
import br.com.fitnesspro.shared.communication.query.sort.Sort;
import kotlin.Pair;
import kotlin.enums.EnumEntries;

public class PrimefacesFiltersUtil {
	
	private Map<String, FilterMeta> filterBy;
	
	public PrimefacesFiltersUtil(Map<String, FilterMeta> filterBy) {
		super();
		this.filterBy = filterBy;
	}

	public <T extends Enum<T> & IEnumFields> List<Sort<T>> getSortFromFieldList(Map<String, SortMeta> sortBy, EnumEntries<T> entries) {
		List<T> fields = getSortFieldList(sortBy, entries);
		return sortBy.values().stream().map(m -> getSortWithMetaAndField(fields, m)).toList();
	}

	public <T extends Enum<T> & IEnumFields> Sort<T> getSortFromField(Map<String, SortMeta> sortBy, EnumEntries<T> entries) {
		T sortField = getSortField(sortBy, entries);
		SortMeta sortMeta = sortBy.values().stream().findFirst().get();
		
		return new Sort<T>(sortField, sortMeta.getOrder() == SortOrder.ASCENDING);
	}
	
	private <T extends Enum<T> & IEnumFields> Sort<T> getSortWithMetaAndField(List<T> fields, SortMeta sortMeta) {
		T field = fields.stream().filter(x -> x.getFieldName().equals(sortMeta.getField())).findFirst().get();
		return new Sort<T>(field, sortMeta.getOrder() == SortOrder.ASCENDING);
	}
	
	private <T extends Enum<T> & IEnumFields> T getSortField(Map<String, SortMeta> sortBy, EnumEntries<T> entries) {
		SortMeta sortMeta = sortBy.values().stream().findFirst().get();
		return entries.stream().filter(x -> x.getFieldName().equals(sortMeta.getField())).findFirst().get();
	}
	
	private <T extends Enum<T> & IEnumFields> List<T> getSortFieldList(Map<String, SortMeta> sortBy, EnumEntries<T> entries) {
		List<String> sortMetaStringFields = sortBy.values().stream().map(s -> s.getField()).toList();
		return entries.stream().filter(x -> sortMetaStringFields.contains(x.getFieldName())).toList();
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
