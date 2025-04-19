package br.com.administrator.injection.mapper;

import br.com.administrator.mappers.AcademyMapper;
import br.com.administrator.mappers.ApplicationMapper;
import br.com.administrator.mappers.CacheMapper;
import br.com.administrator.mappers.DeviceMapper;
import br.com.administrator.mappers.common.converters.LabeledEnumToEnumConverter;
import br.com.administrator.mappers.log.LogMapper;
import br.com.administrator.mappers.log.converters.EnumExecutionStateConverter;
import br.com.administrator.mappers.log.converters.EnumExecutionTypeConverter;
import br.com.administrator.mappers.person.PersonMapper;
import br.com.administrator.mappers.person.converters.EnumUserTypeConverter;
import br.com.administrator.mappers.token.TokenMapper;
import br.com.administrator.mappers.token.converters.EnumTokenTypeConverter;
import br.com.fitnesspro.models.executions.enums.EnumExecutionType;
import br.com.fitnesspro.shared.communication.enums.execution.EnumExecutionState;
import br.com.fitnesspro.shared.communication.enums.general.EnumUserType;
import br.com.fitnesspro.shared.communication.enums.serviceauth.EnumTokenType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class MapperProducer {
	
	@Produces
	public AcademyMapper producesAcademyMapper() {
		return new AcademyMapper();
	}
	
	@Produces
	public ApplicationMapper producesApplicationMapper() {
		return new ApplicationMapper();
	}
	
	@Produces
	public CacheMapper producesCacheMapper() {
		return new CacheMapper();
	}
	
	@Produces
	public DeviceMapper producesDeviceMapper() {
		return new DeviceMapper();
	}
	
	@Produces
	public PersonMapper producesPersonMapper(EnumUserTypeConverter userTypeConverter) {
		return new PersonMapper(userTypeConverter, new LabeledEnumToEnumConverter<EnumUserType>());
	}
	
	@Produces
	public TokenMapper producesTokenMapper(EnumTokenTypeConverter tokenTypeConverter) {
		return new TokenMapper(tokenTypeConverter, new LabeledEnumToEnumConverter<EnumTokenType>());
	}
	
	@Produces
	public LogMapper producesLogMapper(EnumExecutionStateConverter enumStateConverter, EnumExecutionTypeConverter enumTypeConverter) {
		return new LogMapper(enumStateConverter, 
							 enumTypeConverter, 
							 new LabeledEnumToEnumConverter<EnumExecutionType>(), 
							 new LabeledEnumToEnumConverter<EnumExecutionState>());
	}
}
