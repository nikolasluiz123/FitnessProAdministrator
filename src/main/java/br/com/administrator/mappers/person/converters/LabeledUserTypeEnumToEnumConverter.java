package br.com.administrator.mappers.person.converters;

import br.com.administrator.mappers.common.converters.AbstractLabeledEnumToEnumConverter;
import br.com.fitnesspro.shared.communication.enums.general.EnumUserType;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LabeledUserTypeEnumToEnumConverter extends AbstractLabeledEnumToEnumConverter<EnumUserType> {
}
