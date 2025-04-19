package br.com.administrator.injection;

import br.com.administrator.mappers.labeledenum.execution.ExecutionLabeledEnumMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class LabeledEnumMapperProducer {
	
	@Produces
	public ExecutionLabeledEnumMapper producesExecutionLabeledEnumMapper() {
		return new ExecutionLabeledEnumMapper();
	}
}
