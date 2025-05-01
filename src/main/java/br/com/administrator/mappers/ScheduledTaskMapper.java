package br.com.administrator.mappers;

import org.modelmapper.ModelMapper;

import br.com.administrator.to.TOScheduledTask;
import br.com.fitnesspro.shared.communication.dtos.scheduledtask.ScheduledTaskDTO;

public final class ScheduledTaskMapper {

	private ModelMapper modelMapper = new ModelMapper();
	
	public ScheduledTaskDTO getScheduledTaskDTOFrom(TOScheduledTask toScheduledTask) {
		return modelMapper.map(toScheduledTask, ScheduledTaskDTO.class);
	}
	
	public TOScheduledTask getTOScheduledTaskFrom(ScheduledTaskDTO scheduledTaskDTO) {
		return modelMapper.map(scheduledTaskDTO, TOScheduledTask.class);
	}
}
