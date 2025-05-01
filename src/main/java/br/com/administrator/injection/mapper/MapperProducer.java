package br.com.administrator.injection.mapper;

import br.com.administrator.mappers.AcademyMapper;
import br.com.administrator.mappers.ApplicationMapper;
import br.com.administrator.mappers.CacheMapper;
import br.com.administrator.mappers.DeviceMapper;
import br.com.administrator.mappers.ScheduledTaskMapper;
import br.com.administrator.mappers.log.LogMapper;
import br.com.administrator.mappers.log.converters.EnumExecutionStateConverter;
import br.com.administrator.mappers.log.converters.EnumExecutionTypeConverter;
import br.com.administrator.mappers.log.converters.LabeledExecutionStateEnumToEnumConverter;
import br.com.administrator.mappers.log.converters.LabeledExecutionTypeEnumToEnumConverter;
import br.com.administrator.mappers.person.PersonMapper;
import br.com.administrator.mappers.person.converters.EnumUserTypeConverter;
import br.com.administrator.mappers.person.converters.LabeledUserTypeEnumToEnumConverter;
import br.com.administrator.mappers.token.TokenMapper;
import br.com.administrator.mappers.token.converters.EnumTokenTypeConverter;
import br.com.administrator.mappers.token.converters.LabeledTokenTypeEnumToEnumConverter;
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
	public PersonMapper producesPersonMapper(EnumUserTypeConverter userTypeConverter, LabeledUserTypeEnumToEnumConverter enumTypeConverter) {
		return new PersonMapper(userTypeConverter, enumTypeConverter);
	}
	
	@Produces
	public TokenMapper producesTokenMapper(EnumTokenTypeConverter tokenTypeConverter, LabeledTokenTypeEnumToEnumConverter enumTypeConverter) {
		return new TokenMapper(tokenTypeConverter, enumTypeConverter);
	}
	
	@Produces
	public LogMapper producesLogMapper(EnumExecutionStateConverter enumStateConverter, 
									   EnumExecutionTypeConverter enumTypeConverter, 
									   LabeledExecutionTypeEnumToEnumConverter executionTypeEnumToEnumConverter, 
									   LabeledExecutionStateEnumToEnumConverter executionStateEnumToEnumConverter) {
		return new LogMapper(enumStateConverter, enumTypeConverter, executionTypeEnumToEnumConverter, executionStateEnumToEnumConverter);
	}
	
	@Produces
	public ScheduledTaskMapper producesScheduledTaskMapper() {
		return new ScheduledTaskMapper();
	}
}
