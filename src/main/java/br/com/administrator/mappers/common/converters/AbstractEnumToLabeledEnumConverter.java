package br.com.administrator.mappers.common.converters;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import br.com.administrator.managedbean.common.labeledenum.LabeledEnum;

public abstract class AbstractEnumToLabeledEnumConverter<T extends Enum<T>> implements Converter<T, LabeledEnum<T>> {
	
	protected abstract String getLabelTokenType(T enumerator);
	
	@Override
	public LabeledEnum<T> convert(MappingContext<T, LabeledEnum<T>> context) {
		return new LabeledEnum<T>(context.getSource(), getLabelTokenType(context.getSource()));
	}
}