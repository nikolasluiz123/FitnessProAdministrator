package br.com.administrator.viewmodel.scheduledtask;

import java.io.Serializable;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import br.com.administrator.mappers.ScheduledTaskMapper;
import br.com.administrator.mappers.labeledenum.chronounit.ChronoUnitLabeledEnumMapper;
import br.com.administrator.service.webclient.ScheduledTaskWebClient;
import br.com.administrator.to.TOScheduledTask;
import br.com.administrator.utils.ScheduledTaskTemporalUtils;
import br.com.fitnesspro.shared.communication.dtos.scheduledtask.ScheduledTaskDTO;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class ScheduledTaskSearchViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final ScheduledTaskWebClient webClient;
	private final ScheduledTaskMapper scheduledTaskMapper;
	private final ChronoUnitLabeledEnumMapper labeledEnumMapper;

	@Inject
	public ScheduledTaskSearchViewModel(ScheduledTaskWebClient webClient, ScheduledTaskMapper mapper, ChronoUnitLabeledEnumMapper labeledEnumMapper) {
		this.webClient = webClient;
		this.scheduledTaskMapper = mapper;
		this.labeledEnumMapper = labeledEnumMapper;
	}

	public List<TOScheduledTask> getListScheduledTask() throws Exception {
		List<ScheduledTaskDTO> result = webClient.getListScheduledTask();
		return result.stream().map(s -> scheduledTaskMapper.getTOScheduledTaskFrom(s)).collect(Collectors.toList());
	}

	public String getFormatedInterval(Long interval) {
		List<ChronoUnit> validUnitsFromScheduledTask = labeledEnumMapper.getValidUnitsFromScheduledTask();
		return ScheduledTaskTemporalUtils.getFormatedInterval(interval, validUnitsFromScheduledTask);
	}

}
