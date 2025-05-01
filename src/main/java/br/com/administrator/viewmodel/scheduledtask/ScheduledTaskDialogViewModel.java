package br.com.administrator.viewmodel.scheduledtask;

import java.io.Serializable;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

import br.com.administrator.mappers.ScheduledTaskMapper;
import br.com.administrator.mappers.labeledenum.chronounit.ChronoUnitLabeledEnumMapper;
import br.com.administrator.service.webclient.ScheduledTaskWebClient;
import br.com.administrator.to.TOScheduledTask;
import br.com.administrator.utils.ScheduledTaskTemporalUtils;
import br.com.fitnesspro.shared.communication.dtos.scheduledtask.ScheduledTaskDTO;
import br.com.fitnesspro.shared.communication.responses.PersistenceServiceResponse;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class ScheduledTaskDialogViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final ScheduledTaskWebClient webClient;
	private final ScheduledTaskMapper scheduledTaskMapper;
	private final ChronoUnitLabeledEnumMapper labeledEnumMapper;

	@Inject
	public ScheduledTaskDialogViewModel(ScheduledTaskWebClient webClient, ScheduledTaskMapper mapper, ChronoUnitLabeledEnumMapper labeledEnumMapper) {
		this.webClient = webClient;
		this.scheduledTaskMapper = mapper;
		this.labeledEnumMapper = labeledEnumMapper;
	}
	
	public void saveScheduledTask(TOScheduledTask to, ChronoUnit selectedTemporalUnit, String interval) throws Exception {
		to.setIntervalMillis(getMillis(interval, selectedTemporalUnit));
		
		ScheduledTaskDTO dto = scheduledTaskMapper.getScheduledTaskDTOFrom(to);
		
		PersistenceServiceResponse<ScheduledTaskDTO> response = webClient.saveScheduledTask(dto);
		
		if (response.getSuccess()) {
			to.setId(response.getSavedDTO().getId());
		}
	}

	public void inactivateScheduledTask(TOScheduledTask to) throws Exception {
		to.setActive(false);
		webClient.saveScheduledTask(scheduledTaskMapper.getScheduledTaskDTOFrom(to));
	}
	
	private Long getMillis(String interval, ChronoUnit unit) {
		Long longValue = Long.valueOf(interval);
		Duration duration = unit.getDuration();
		
        return duration.multipliedBy(longValue).toMillis();
	}
	
	public ChronoUnit getChronoUnitFor(Long interval) {
		List<ChronoUnit> validUnitsFromScheduledTask = labeledEnumMapper.getValidUnitsFromScheduledTask();
		return ScheduledTaskTemporalUtils.getChronoUnitFor(interval, validUnitsFromScheduledTask);
	}
	
	public String getIntervalOfUnit(Long interval, ChronoUnit unit) {
		List<ChronoUnit> validUnitsFromScheduledTask = labeledEnumMapper.getValidUnitsFromScheduledTask();
		return ScheduledTaskTemporalUtils.getIntervalOfUnit(interval, unit, validUnitsFromScheduledTask);
	}
	
	public String getLabelChronoUnit(ChronoUnit unit) {
		return labeledEnumMapper.getLabelChronoUnit(unit);
	}
	
	public List<ChronoUnit> getValidChronoUnits() {
		return labeledEnumMapper.getValidUnitsFromScheduledTask();
	}
}
