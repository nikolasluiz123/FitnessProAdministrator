package br.com.administrator.managedbean.executionlogs;

import java.io.Serializable;
import java.util.List;

import br.com.administrator.managedbean.common.labeledenum.LabeledEnum;
import br.com.administrator.viewmodel.ExecutionLogsViewModel;
import br.com.fitnesspro.models.executions.enums.EnumExecutionType;
import br.com.fitnesspro.shared.communication.enums.execution.EnumExecutionState;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("executionLogsMBean")
@ViewScoped
public class ExecutionLogsMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private LazyExecutionLogDataModel lazyModel;
	
	@Inject
	private ExecutionLogsViewModel viewModel;

	private List<LabeledEnum<EnumExecutionType>> executionTypes;
	private List<LabeledEnum<EnumExecutionState>> executionStates;
	
	@PostConstruct
	public void init() {
		this.executionTypes = EnumExecutionType.getEntries().stream().map(x -> getLabeledType(x)).toList();
		this.executionStates = EnumExecutionState.getEntries().stream().map(x -> getLabeledState(x)).toList();
	}

	private LabeledEnum<EnumExecutionType> getLabeledType(EnumExecutionType x) {
		return new LabeledEnum<EnumExecutionType>(x, viewModel.getLabelExecutionType(x));
	}
	
	private LabeledEnum<EnumExecutionState> getLabeledState(EnumExecutionState x) {
		return new LabeledEnum<EnumExecutionState>(x, viewModel.getLabelExecutionState(x));
	}
	
	public LazyExecutionLogDataModel getLazyModel() {
		return lazyModel;
	}

	public List<LabeledEnum<EnumExecutionType>> getExecutionTypes() {
		return executionTypes;
	}

	public List<LabeledEnum<EnumExecutionState>> getExecutionStates() {
		return executionStates;
	}
	
}
