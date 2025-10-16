package br.com.administrator.mappers.token.converters;

import br.com.administrator.mappers.common.converters.AbstractLabeledEnumToEnumConverter;
import br.com.fitnesspro.shared.communication.enums.serviceauth.EnumTokenType;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LabeledTokenTypeEnumToEnumConverter extends AbstractLabeledEnumToEnumConverter<EnumTokenType> {
}
