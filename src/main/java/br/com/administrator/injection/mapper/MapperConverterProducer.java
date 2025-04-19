package br.com.administrator.injection.mapper;

import br.com.administrator.mappers.labeledenum.execution.TokenLabeledEnumMapper;
import br.com.administrator.mappers.labeledenum.execution.UserLabeledEnumMapper;
import br.com.administrator.mappers.person.converters.EnumUserTypeConverter;
import br.com.administrator.mappers.token.converters.EnumTokenTypeConverter;
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
}
