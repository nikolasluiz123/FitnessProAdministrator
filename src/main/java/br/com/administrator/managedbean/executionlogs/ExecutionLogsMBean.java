package br.com.administrator.managedbean.executionlogs;

import java.util.List;

import org.primefaces.event.SelectEvent;

import br.com.administrator.managedbean.common.beans.AbstractPagingSearchMBean;
import br.com.administrator.managedbean.common.labeledenum.LabeledEnum;
import br.com.administrator.managedbean.executionlogs.enums.EnumThreeOptions;
import br.com.administrator.managedbean.executionlogs.lazymodel.LazyExecutionLogDataModel;
import br.com.administrator.to.TOExecutionLog;
import br.com.administrator.viewmodel.log.ExecutionLogsViewModel;
import br.com.fitnesspro.shared.communication.enums.execution.EnumExecutionState;
import br.com.fitnesspro.shared.communication.enums.execution.EnumExecutionType;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("executionLogsMBean")
@ViewScoped
public class ExecutionLogsMBean extends AbstractPagingSearchMBean<TOExecutionLog, LazyExecutionLogDataModel> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private LazyExecutionLogDataModel lazyModel;
	
	@Inject
	private ExecutionLogsViewModel viewModel;
	
	@Inject 
	private LogDialogMBean logDialogMBean;

	private List<LabeledEnum<EnumExecutionType>> executionTypes;
	private List<LabeledEnum<EnumExecutionState>> executionStates;
	private List<LabeledEnum<EnumThreeOptions>> showExecutionsOptions;
	
	private EnumThreeOptions showExecutionsWithInsertedEntities;
	private EnumThreeOptions showExecutionsWithUpdatedEntities;
	
	@Override
	public void onInit() {
		this.executionTypes = EnumExecutionType.getEntries().stream().map(x -> getLabeledType(x)).toList();
		this.executionStates = EnumExecutionState.getEntries().stream().map(x -> getLabeledState(x)).toList();
		this.showExecutionsOptions = List.of(EnumThreeOptions.values()).stream().map(x -> getLabeledThreeOptions(x)).toList();
		
		this.showExecutionsWithInsertedEntities = EnumThreeOptions.ALL;
		this.showExecutionsWithUpdatedEntities = EnumThreeOptions.ALL;
	}

	@Override
	public void onRowSelect(SelectEvent<TOExecutionLog> event) {
		logDialogMBean.init(event.getObject());
	}
	
	public void onAdvancedFilterChange() {
		viewModel.setShowExecutionsWithInsertedEntities(showExecutionsWithInsertedEntities);
		viewModel.setShowExecutionsWithUpdatedEntities(showExecutionsWithUpdatedEntities);
	}

	private LabeledEnum<EnumExecutionType> getLabeledType(EnumExecutionType x) {
		return new LabeledEnum<EnumExecutionType>(x, viewModel.getLabelExecutionType(x));
	}
	
	private LabeledEnum<EnumExecutionState> getLabeledState(EnumExecutionState x) {
		return new LabeledEnum<EnumExecutionState>(x, viewModel.getLabelExecutionState(x));
	}
	
	private LabeledEnum<EnumThreeOptions> getLabeledThreeOptions(EnumThreeOptions x) {
		return new LabeledEnum<EnumThreeOptions>(x, viewModel.getLabelThreeOption(x));
	}
	
	public List<LabeledEnum<EnumExecutionType>> getExecutionTypes() {
		return executionTypes;
	}

	public List<LabeledEnum<EnumExecutionState>> getExecutionStates() {
		return executionStates;
	}
	
	public List<LabeledEnum<EnumThreeOptions>> getShowExecutionsOptions() {
		return showExecutionsOptions;
	}

	public void setShowExecutionsOptions(List<LabeledEnum<EnumThreeOptions>> showExecutionsOptions) {
		this.showExecutionsOptions = showExecutionsOptions;
	}

	public EnumThreeOptions getShowExecutionsWithInsertedEntities() {
		return showExecutionsWithInsertedEntities;
	}

	public void setShowExecutionsWithInsertedEntities(EnumThreeOptions showExecutionsWithInsertedEntities) {
		this.showExecutionsWithInsertedEntities = showExecutionsWithInsertedEntities;
	}

	public EnumThreeOptions getShowExecutionsWithUpdatedEntities() {
		return showExecutionsWithUpdatedEntities;
	}

	public void setShowExecutionsWithUpdatedEntities(EnumThreeOptions showExecutionsWithUpdatedEntities) {
		this.showExecutionsWithUpdatedEntities = showExecutionsWithUpdatedEntities;
	}

	@Override
	public LazyExecutionLogDataModel getLazyModel() {
		return lazyModel;
	}
	
	@Override
	public String getScreenBundleFilePath() {
		return "messages.logs.execution_logs";
	}
}
