package br.com.administrator.managedbean.scheduledtask;

import org.primefaces.event.SelectEvent;

import br.com.administrator.managedbean.common.beans.AbstractSearchMBean;
import br.com.administrator.to.TOScheduledTask;
import br.com.administrator.viewmodel.scheduledtask.ScheduledTaskSearchViewModel;
import br.com.fitnesspro.shared.communication.exception.ExpiredTokenException;
import br.com.fitnesspro.shared.communication.exception.NotFoundTokenException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("scheduledTaskSearchMBean")
@ViewScoped
public class ScheduledTaskSearchMBean extends AbstractSearchMBean<TOScheduledTask> {

	public static final String MESSAGES_SCHEDULED_TASK_SEARCH = "messages.scheduledtask.scheduled_task_search";

	private static final long serialVersionUID = 1L;

	@Inject
	private ScheduledTaskSearchViewModel viewModel;
	
	@Inject
	private ScheduledTaskDialogMBean scheduledTaskDialogMBean;

	@Override
	public void onRequestReloadDatatable() {
		loadScheduledTaskList();
	}
	
	@Override
	public void onRowSelect(SelectEvent<TOScheduledTask> event) {
		scheduledTaskDialogMBean.init(event.getObject());
	}
	
	public String getFormatedInterval(TOScheduledTask task) {
		return viewModel.getFormatedInterval(task.getIntervalMillis());
	}
	
	private void loadScheduledTaskList() {
		try {
			setData(viewModel.getListScheduledTask());
		} catch(ExpiredTokenException | NotFoundTokenException exception) {
			exceptionHandler(exception, getSearchBundleString("search_error_summary"));
			showLoginDialog();
		} catch (Exception e) {
			this.exceptionHandler(e, getSearchBundleString("search_error_summary"));
		}
	}
	
	public void onNewScheduledTaskClick() {
		scheduledTaskDialogMBean.init();
	}

	@Override
	public String getScreenBundleFilePath() {
		return MESSAGES_SCHEDULED_TASK_SEARCH;
	}

}
