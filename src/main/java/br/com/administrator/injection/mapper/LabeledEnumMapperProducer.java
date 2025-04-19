package br.com.administrator.injection.mapper;

import br.com.administrator.mappers.labeledenum.execution.ExecutionLabeledEnumMapper;
import br.com.administrator.mappers.labeledenum.execution.TokenLabeledEnumMapper;
import br.com.administrator.mappers.labeledenum.execution.UserLabeledEnumMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class LabeledEnumMapperProducer {
	
	@Produces
	public ExecutionLabeledEnumMapper producesExecutionLabeledEnumMapper() {
		return new ExecutionLabeledEnumMapper();
	}
	
	@Produces
	public UserLabeledEnumMapper producesUserLabeledEnumMapper() {
		return new UserLabeledEnumMapper();
	}
	
	@Produces
	public TokenLabeledEnumMapper producesTokenLabeledEnumMapper() {
		return new TokenLabeledEnumMapper();
	}
}
