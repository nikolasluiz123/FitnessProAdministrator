package br.com.administrator.mappers.labeledenum.chronounit;

import br.com.administrator.managedbean.common.constants.ICommonBundlePaths;
import br.com.administrator.utils.FacesUtils;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@ApplicationScoped
public class ChronoUnitLabeledEnumMapper {

	private ResourceBundle bundle = FacesUtils.getResourceBundle(ICommonBundlePaths.LABELED_ENUMS);
	
	public String getLabelChronoUnit(ChronoUnit type) {
		String label = null;
		
		switch(type) {
		case SECONDS:
			label = bundle.getString("label_chrono_unit_seconds");
			break;
		case MINUTES:
			label = bundle.getString("label_chrono_unit_minutes");
			break;
		case HOURS:
			label = bundle.getString("label_chrono_unit_hours");
			break;
		case DAYS:
			label = bundle.getString("label_chrono_unit_days");
			break;
		default:
			break;
		}
		
		return label;
	}
	
	public List<ChronoUnit> getValidUnitsFromScheduledTask() {
		List<ChronoUnit> result = new ArrayList<>();
		result.add(ChronoUnit.DAYS);
		result.add(ChronoUnit.HOURS);
		result.add(ChronoUnit.MINUTES);
		result.add(ChronoUnit.SECONDS);
		
		return result;
	}
}
