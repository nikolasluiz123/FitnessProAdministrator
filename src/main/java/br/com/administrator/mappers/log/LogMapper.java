package br.com.administrator.mappers.log;

import br.com.administrator.mappers.log.converters.EnumExecutionStateConverter;
import br.com.administrator.mappers.log.converters.EnumExecutionTypeConverter;
import br.com.administrator.mappers.log.converters.LabeledExecutionStateEnumToEnumConverter;
import br.com.administrator.mappers.log.converters.LabeledExecutionTypeEnumToEnumConverter;
import br.com.administrator.to.TOExecutionLog;
import br.com.administrator.to.TOExecutionLogPackage;
import br.com.administrator.to.TOExecutionLogSubPackage;
import br.com.fitnesspro.shared.communication.dtos.logs.ExecutionLogDTO;
import br.com.fitnesspro.shared.communication.dtos.logs.ExecutionLogPackageDTO;
import br.com.fitnesspro.shared.communication.dtos.logs.ExecutionLogSubPackageDTO;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.io.Serializable;

@Dependent
public class LogMapper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final ModelMapper modelMapper;

    @Inject
	public LogMapper(EnumExecutionStateConverter enumStateConverter, 
					 EnumExecutionTypeConverter enumTypeConverter, 
					 LabeledExecutionTypeEnumToEnumConverter labeledEnumTypeConverter, 
					 LabeledExecutionStateEnumToEnumConverter labeledEnumStateConverter) {
		this.modelMapper = new ModelMapper();
		
		modelMapper.addConverter(enumStateConverter);
		modelMapper.addConverter(enumTypeConverter);
		modelMapper.addConverter(labeledEnumTypeConverter);
		modelMapper.addConverter(labeledEnumStateConverter);
	}

	public ExecutionLogDTO getExecutionLogDTOFrom(TOExecutionLog toExecutionLog) {
		return modelMapper.map(toExecutionLog, ExecutionLogDTO.class);
	}
	
	public TOExecutionLog getTOExecutionLogFrom(ExecutionLogDTO executionLogDTO) {
		return modelMapper.map(executionLogDTO, TOExecutionLog.class);
	}
	
	public ExecutionLogPackageDTO getExecutionLogPackageDTOFrom(TOExecutionLogPackage toExecutionLogPackage) {
		return modelMapper.map(toExecutionLogPackage, ExecutionLogPackageDTO.class);
	}
	
	public TOExecutionLogPackage getTOExecutionLogPackageFrom(ExecutionLogPackageDTO executionLogPackageDTO) {
		return modelMapper.map(executionLogPackageDTO, TOExecutionLogPackage.class);
	}
	
	public TOExecutionLogSubPackage getTOExecutionLogSubPackageFrom(ExecutionLogSubPackageDTO executionLogSubPackageDTO) {
		return modelMapper.map(executionLogSubPackageDTO, TOExecutionLogSubPackage.class);
	}
}
