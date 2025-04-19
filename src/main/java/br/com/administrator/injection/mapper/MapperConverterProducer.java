package br.com.administrator.injection.mapper;

import br.com.administrator.mappers.labeledenum.execution.ExecutionLabeledEnumMapper;
import br.com.administrator.mappers.labeledenum.execution.TokenLabeledEnumMapper;
import br.com.administrator.mappers.labeledenum.execution.UserLabeledEnumMapper;
import br.com.administrator.mappers.log.converters.EnumExecutionStateConverter;
import br.com.administrator.mappers.log.converters.EnumExecutionTypeConverter;
import br.com.administrator.mappers.log.converters.LabeledExecutionStateEnumToEnumConverter;
import br.com.administrator.mappers.log.converters.LabeledExecutionTypeEnumToEnumConverter;
import br.com.administrator.mappers.person.converters.EnumUserTypeConverter;
import br.com.administrator.mappers.person.converters.LabeledUserTypeEnumToEnumConverter;
import br.com.administrator.mappers.token.converters.EnumTokenTypeConverter;
import br.com.administrator.mappers.token.converters.LabeledTokenTypeEnumToEnumConverter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class MapperConverterProducer {
	
	@Produces
	public EnumTokenTypeConverter producesEnumTokenTypeConverter(TokenLabeledEnumMapper labeledMapper) {
		return new EnumTokenTypeConverter(labeledMapper);
	}
	
	@Produces
	public EnumUserTypeConverter producesEnumUserTypeConverter(UserLabeledEnumMapper labeledMapper) {
		return new EnumUserTypeConverter(labeledMapper);
	}
	
	@Produces
	public EnumExecutionStateConverter producesEnumExecutionStateConverter(ExecutionLabeledEnumMapper labeledMapper) {
		return new EnumExecutionStateConverter(labeledMapper);
	}
	
	@Produces
	public EnumExecutionTypeConverter producesEnumExecutionTypeConverter(ExecutionLabeledEnumMapper labeledMapper) {
		return new EnumExecutionTypeConverter(labeledMapper);
	}
	
	@Produces
	public LabeledExecutionStateEnumToEnumConverter producesLabeledExecutionStateEnumToEnumConverter() {
		return new LabeledExecutionStateEnumToEnumConverter();
	}
	
	@Produces
	public LabeledExecutionTypeEnumToEnumConverter producesLabeledExecutionTypeEnumToEnumConverter() {
		return new LabeledExecutionTypeEnumToEnumConverter();
	}
	
	@Produces
	public LabeledUserTypeEnumToEnumConverter produceLabeledUserTypeEnumToEnumConverter() {
		return new LabeledUserTypeEnumToEnumConverter();
	}
	
	@Produces
	public LabeledTokenTypeEnumToEnumConverter produceLabeledTokenTypeEnumToEnumConverter() {
		return new LabeledTokenTypeEnumToEnumConverter();
	}
}
