package br.com.administrator.mappers.token.converters;

import br.com.administrator.mappers.common.converters.AbstractEnumToLabeledEnumConverter;
import br.com.administrator.mappers.labeledenum.execution.TokenLabeledEnumMapper;
import br.com.fitnesspro.shared.communication.enums.serviceauth.EnumTokenType;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class EnumTokenTypeConverter extends AbstractEnumToLabeledEnumConverter<EnumTokenType> {

	private final TokenLabeledEnumMapper mapper;

    @Inject
	public EnumTokenTypeConverter(TokenLabeledEnumMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	protected String getLabel(EnumTokenType enumerator) {
		return mapper.getLabelTokenType(enumerator);
	}

}
