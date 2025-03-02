package br.com.administrator.managedbean.common.converter;

import java.time.LocalDate;

import br.com.administrator.enums.EnumDateTimePatterns;
import br.com.administrator.utils.TimeConverterUtil;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value = "LocalDateConverter")
public class LocalDateConverter implements Converter<LocalDate> {

	@Override
	public LocalDate getAsObject(FacesContext context, UIComponent component, String value) {
		return TimeConverterUtil.parseLocalDate(value, EnumDateTimePatterns.DATE);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, LocalDate value) {
		return TimeConverterUtil.formatLocalDate(value, EnumDateTimePatterns.DATE);
	}

}
