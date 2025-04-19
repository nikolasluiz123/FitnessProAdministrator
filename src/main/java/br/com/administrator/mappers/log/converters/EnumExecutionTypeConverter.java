package br.com.administrator.mappers.log.converters;

import br.com.administrator.mappers.common.converters.AbstractEnumToLabeledEnumConverter;
import br.com.administrator.mappers.labeledenum.execution.ExecutionLabeledEnumMapper;
import br.com.fitnesspro.models.executions.enums.EnumExecutionType;

public class EnumExecutionTypeConverter extends AbstractEnumToLabeledEnumConverter<EnumExecutionType> {

	private final ExecutionLabeledEnumMapper mapper;

	public EnumExecutionTypeConverter(ExecutionLabeledEnumMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	protected String getLabel(EnumExecutionType enumerator) {
		return mapper.getLabelExecutionType(enumerator);
	}

}
