package br.com.administrator.mappers.token.converters;

import br.com.administrator.mappers.common.converters.AbstractEnumToLabeledEnumConverter;
import br.com.administrator.mappers.labeledenum.execution.TokenLabeledEnumMapper;
import br.com.fitnesspro.shared.communication.enums.serviceauth.EnumTokenType;

public class EnumTokenTypeConverter extends AbstractEnumToLabeledEnumConverter<EnumTokenType> {

	private final TokenLabeledEnumMapper mapper;

	public EnumTokenTypeConverter(TokenLabeledEnumMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	protected String getLabelTokenType(EnumTokenType enumerator) {
		return mapper.getLabelTokenType(enumerator);
	}

}
