package br.com.administrator.managedbean.common.converter;

import java.time.LocalDateTime;

import br.com.administrator.enums.EnumDateTimePatterns;
import br.com.administrator.utils.TimeConverterUtil;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value = "LocalDateTimeMilliConverter")
public class LocalDateTimeMilliConverter implements Converter<LocalDateTime> {

	@Override
	public LocalDateTime getAsObject(FacesContext context, UIComponent component, String value) {
		return TimeConverterUtil.parseLocalDateTime(value, EnumDateTimePatterns.DATE_TIME_MILLI, true);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, LocalDateTime value) {
		return TimeConverterUtil.formatLocalDateTime(value, EnumDateTimePatterns.DATE_TIME_MILLI, true);
	}

}
