package br.com.administrator.mappers.common.converters;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import br.com.administrator.managedbean.common.labeledenum.LabeledEnum;

public class LabeledEnumToEnumConverter<T extends Enum<T>> implements Converter<LabeledEnum<T>, T> {

	@Override
	public T convert(MappingContext<LabeledEnum<T>, T> context) {
		return context.getSource().getValue();
	}
}