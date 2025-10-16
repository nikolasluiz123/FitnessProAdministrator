package br.com.administrator.mappers.labeledenum;

import br.com.administrator.managedbean.common.constants.ICommonBundlePaths;
import br.com.administrator.managedbean.executionlogs.enums.EnumThreeOptions;
import br.com.administrator.utils.FacesUtils;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;
import java.util.ResourceBundle;

@ApplicationScoped
public class ThreeOptionsLabeledEnumMapper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ResourceBundle bundle = FacesUtils.getResourceBundle(ICommonBundlePaths.LABELED_ENUMS);

	public String getLabelThreeOptions(EnumThreeOptions option) {
		return switch (option) {
		case ALL -> bundle.getString("label_three_options_all");
		case YES -> bundle.getString("label_three_options_yes");
		case NO -> bundle.getString("label_three_options_no");
		default -> null;
		};
	}
}
