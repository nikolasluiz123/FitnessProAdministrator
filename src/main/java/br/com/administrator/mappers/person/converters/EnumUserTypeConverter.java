package br.com.administrator.mappers.person.converters;

import br.com.administrator.mappers.common.converters.AbstractEnumToLabeledEnumConverter;
import br.com.administrator.mappers.labeledenum.execution.UserLabeledEnumMapper;
import br.com.fitnesspro.shared.communication.enums.general.EnumUserType;

public class EnumUserTypeConverter extends AbstractEnumToLabeledEnumConverter<EnumUserType> {

	private final UserLabeledEnumMapper mapper;

	public EnumUserTypeConverter(UserLabeledEnumMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	protected String getLabelTokenType(EnumUserType enumerator) {
		return mapper.getLabelUserType(enumerator);
	}

}
