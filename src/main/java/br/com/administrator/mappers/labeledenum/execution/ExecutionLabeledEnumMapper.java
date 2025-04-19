package br.com.administrator.mappers.labeledenum.execution;

import java.util.ResourceBundle;

import br.com.administrator.managedbean.common.constants.ICommonBundlePaths;
import br.com.administrator.utils.FacesUtils;
import br.com.fitnesspro.models.executions.enums.EnumExecutionType;
import br.com.fitnesspro.shared.communication.enums.execution.EnumExecutionState;

public final class ExecutionLabeledEnumMapper {

	private ResourceBundle bundle = FacesUtils.getResourceBundle(ICommonBundlePaths.LABELED_ENUMS);
	
	public String getLabelExecutionType(EnumExecutionType type) {
		String label = null;
		
		switch(type) {
		case DELETE:
			label = bundle.getString("label_execution_type_delete");
			break;
		case EXPORTATION:
			label = bundle.getString("label_execution_type_exportation");
			break;
		case GET:
			label = bundle.getString("label_execution_type_get");
			break;
		case IMPORTATION:
			label = bundle.getString("label_execution_type_importation");
			break;
		case POST:
			label = bundle.getString("label_execution_type_post");
			break;
		case PUT:
			label = bundle.getString("label_execution_type_put");
			break;
		default:
			break;
		}
		
		return label;
	}
	
	public String getLabelExecutionState(EnumExecutionState state) {
		String label = null;
		
		switch(state) {
		case ERROR:
			label = bundle.getString("label_execution_state_error");
			break;
		case FINISHED:
			label = bundle.getString("label_execution_state_finished");
			break;
		case PENDING:
			label = bundle.getString("label_execution_state_pending");
			break;
		case RUNNING:
			label = bundle.getString("label_execution_state_running");
			break;
		default:
			break;
		}
		
		return label;
	}
}
