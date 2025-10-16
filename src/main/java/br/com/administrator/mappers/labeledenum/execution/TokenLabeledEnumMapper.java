package br.com.administrator.mappers.labeledenum.execution;

import br.com.administrator.managedbean.common.constants.ICommonBundlePaths;
import br.com.administrator.utils.FacesUtils;
import br.com.fitnesspro.shared.communication.enums.serviceauth.EnumTokenType;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ResourceBundle;

@ApplicationScoped
public class TokenLabeledEnumMapper {

	private ResourceBundle bundle = FacesUtils.getResourceBundle(ICommonBundlePaths.LABELED_ENUMS);
	
	public String getLabelTokenType(EnumTokenType type) {
		String label = null;
		
		switch (type) {
		case APPLICATION_TOKEN:
			label = bundle.getString("label_type_token_application");
			break;
		case DEVICE_TOKEN:
			label = bundle.getString("label_type_token_device");
			break;
		case USER_AUTHENTICATION_TOKEN:
			label = bundle.getString("label_type_token_user");
			break;
		default:
			break;
		}
		
		return label;
	}
}
