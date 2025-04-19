package br.com.administrator.mappers.log.converters;

import br.com.administrator.mappers.common.converters.AbstractEnumToLabeledEnumConverter;
import br.com.administrator.mappers.labeledenum.execution.ExecutionLabeledEnumMapper;
import br.com.fitnesspro.shared.communication.enums.execution.EnumExecutionState;

public class EnumExecutionStateConverter extends AbstractEnumToLabeledEnumConverter<EnumExecutionState> {

	private final ExecutionLabeledEnumMapper mapper;

	public EnumExecutionStateConverter(ExecutionLabeledEnumMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	protected String getLabel(EnumExecutionState enumerator) {
		return mapper.getLabelExecutionState(enumerator);
	}

}
