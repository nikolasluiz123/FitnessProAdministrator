package br.com.administrator.managedbean.scheduledtask;

import java.time.temporal.ChronoUnit;
import java.util.List;

import br.com.administrator.managedbean.common.beans.AbstractBaseMBean;
import br.com.administrator.managedbean.common.labeledenum.LabeledEnum;
import br.com.administrator.to.TOScheduledTask;
import br.com.administrator.utils.FacesUtils;
import br.com.administrator.viewmodel.scheduledtask.ScheduledTaskDialogViewModel;
import br.com.fitnesspro.shared.communication.exception.ExpiredTokenException;
import br.com.fitnesspro.shared.communication.exception.NotFoundTokenException;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("scheduledTaskDialogMBean")
@ViewScoped
public class ScheduledTaskDialogMBean extends AbstractBaseMBean {

	private static final long serialVersionUID = 1L;

	private TOScheduledTask toScheduledTask;
	
	private List<LabeledEnum<ChronoUnit>> temporalUnits;
	private ChronoUnit selectedTemporalUnit;
	
	private String interval;
	
	@Inject
	private ScheduledTaskDialogViewModel viewModel;	
	
	@PostConstruct
	public void init() {
		this.toScheduledTask = new TOScheduledTask();
		this.temporalUnits = viewModel.getValidChronoUnits().stream().map(this::getLabeledEnum).toList();
	}
	
	private LabeledEnum<ChronoUnit> getLabeledEnum(ChronoUnit unit) {
		return new LabeledEnum<ChronoUnit>(unit, viewModel.getLabelChronoUnit(unit));
	}
	
	public void init(TOScheduledTask to) {
		this.toScheduledTask = to;
		this.selectedTemporalUnit = viewModel.getChronoUnitFor(to.getIntervalMillis());
		this.interval = viewModel.getIntervalOfUnit(to.getIntervalMillis(), this.selectedTemporalUnit);
	}

	public void onScheduledTaskSaveClick() {
		try {
			viewModel.saveScheduledTask(this.toScheduledTask, selectedTemporalUnit, interval);
			
			FacesUtils.addSucccessMessage(getScreenBundleString("save_scheduled_task_success"), 
										  getMaintenanceBundleString("save_success_summary"));
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler(exception, getMaintenanceBundleString("save_error_summary"));
			showLoginDialog();
		} catch (Exception exception) {
			exceptionHandler(exception, getMaintenanceBundleString("save_error_summary"));
		}
	}

	public void onScheduledTaskInactivateClick() {
		try {
			viewModel.inactivateScheduledTask(this.toScheduledTask);
			
			FacesUtils.addSucccessMessage(getScreenBundleString("inactivate_scheduled_task_success"), getMaintenanceBundleString("inactivation_success_summary"));
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler(exception, getMaintenanceBundleString("save_error_summary"));
			showLoginDialog();
		} catch (Exception exception) {
			exceptionHandler(exception, getMaintenanceBundleString("inactivation_error_summary"));
		}
	}
	
	public String getTitle() {
		return this.toScheduledTask.getId() != null ? 
				getScreenBundleString("scheduled_task_title", this.toScheduledTask.getName()) : getScreenBundleString("new_scheduled_task_title");
	}
	
	public String getMessageConfirmationInactivation() {
		return getScreenBundleString("message_dialog_confirmation_inactivation");
	}

	public Boolean getDisabledInactivateButton() {
		return getDisabledDialog() || this.toScheduledTask.getId() == null;
	}
	
	public Boolean getDisabledDialog() {
		return this.toScheduledTask.getId() != null && !this.toScheduledTask.getActive();
	}

	@Override
	public String getScreenBundleFilePath() {
		return "messages.scheduledtask.scheduled_task_dialog";
	}

	public TOScheduledTask getToScheduledTask() {
		return toScheduledTask;
	}

	public void setToScheduledTask(TOScheduledTask toScheduledTask) {
		this.toScheduledTask = toScheduledTask;
	}

	public List<LabeledEnum<ChronoUnit>> getTemporalUnits() {
		return temporalUnits;
	}

	public void setTemporalUnits(List<LabeledEnum<ChronoUnit>> temporalUnits) {
		this.temporalUnits = temporalUnits;
	}

	public ChronoUnit getSelectedTemporalUnit() {
		return selectedTemporalUnit;
	}

	public void setSelectedTemporalUnit(ChronoUnit selectedTemporalUnit) {
		this.selectedTemporalUnit = selectedTemporalUnit;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}
	
}
