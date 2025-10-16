package br.com.administrator.mappers.log.converters;

import br.com.administrator.mappers.common.converters.AbstractLabeledEnumToEnumConverter;
import br.com.fitnesspro.shared.communication.enums.execution.EnumExecutionState;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LabeledExecutionStateEnumToEnumConverter extends AbstractLabeledEnumToEnumConverter<EnumExecutionState> {
}
