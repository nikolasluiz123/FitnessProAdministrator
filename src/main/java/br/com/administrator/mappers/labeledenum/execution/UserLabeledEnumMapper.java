package br.com.administrator.mappers.labeledenum.execution;

import br.com.administrator.managedbean.common.constants.ICommonBundlePaths;
import br.com.administrator.utils.FacesUtils;
import br.com.fitnesspro.shared.communication.enums.general.EnumUserType;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ResourceBundle;

@ApplicationScoped
public class UserLabeledEnumMapper {

	private ResourceBundle bundle = FacesUtils.getResourceBundle(ICommonBundlePaths.LABELED_ENUMS);
	
	public String getLabelUserType(EnumUserType type) {
		String label = null;
		
		switch (type) {
		case PERSONAL_TRAINER:
			label = bundle.getString("label_user_type_personal");
			break;
		case NUTRITIONIST:
			label = bundle.getString("label_user_type_nutri");
			break;
		case ACADEMY_MEMBER:
			label = bundle.getString("label_user_type_member");
			break;
		case ADMINISTRATOR:
			label = bundle.getString("label_user_type_admin");
			break;
		default:
			break;
		}
		
		return label;
	}
}
