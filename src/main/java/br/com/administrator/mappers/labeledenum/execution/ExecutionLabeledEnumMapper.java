package br.com.administrator.mappers.labeledenum.execution;

import java.util.ResourceBundle;

import br.com.administrator.managedbean.common.constants.ICommonBundlePaths;
import br.com.administrator.utils.FacesUtils;
import br.com.fitnesspro.shared.communication.enums.execution.EnumExecutionState;
import br.com.fitnesspro.shared.communication.enums.execution.EnumExecutionType;

public final class ExecutionLabeledEnumMapper {

	private ResourceBundle bundle = FacesUtils.getResourceBundle(ICommonBundlePaths.LABELED_ENUMS);

	public String getLabelExecutionType(EnumExecutionType type) {
		return switch (type) {
		case DELETE -> bundle.getString("label_execution_type_delete");
		case EXPORTATION -> bundle.getString("label_execution_type_exportation");
		case GET -> bundle.getString("label_execution_type_get");
		case IMPORTATION -> bundle.getString("label_execution_type_importation");
		case POST -> bundle.getString("label_execution_type_post");
		case PUT -> bundle.getString("label_execution_type_put");
		case SCHEDULED_TASK -> bundle.getString("label_execution_type_scheduled_task");
		case STORAGE -> bundle.getString("label_execution_type_storage");
		default -> null;
		};
	}

	public String getLabelExecutionState(EnumExecutionState state) {
		return switch (state) {
		case ERROR -> bundle.getString("label_execution_state_error");
		case FINISHED -> bundle.getString("label_execution_state_finished");
		case PENDING -> bundle.getString("label_execution_state_pending");
		case RUNNING -> bundle.getString("label_execution_state_running");
		};
	}
}
