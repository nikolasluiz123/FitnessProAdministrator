package br.com.administrator.utils;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ResourceBundle;

import br.com.administrator.managedbean.scheduledtask.ScheduledTaskSearchMBean;

public final class ScheduledTaskTemporalUtils {

	public static String getFormatedInterval(Long interval, List<ChronoUnit> validUnitsFromScheduledTask) {
		Duration duration = Duration.ofMillis(interval);

		for (ChronoUnit chronoUnit : validUnitsFromScheduledTask) {
			long value = getMillisValue(duration, chronoUnit);

			if (value >= 1) {
				return value + " " + pluralize(chronoUnit, value);
			}
		}

		return null;
	}
	
	public static ChronoUnit getChronoUnitFor(Long interval, List<ChronoUnit> validUnitsFromScheduledTask) {
		Duration duration = Duration.ofMillis(interval);

		for (ChronoUnit chronoUnit : validUnitsFromScheduledTask) {
			long value = getMillisValue(duration, chronoUnit);

			if (value >= 1) {
				return chronoUnit;
			}
		}

		return null;
	}
	
	public static String getIntervalOfUnit(Long interval, ChronoUnit unit, List<ChronoUnit> validUnitsFromScheduledTask) {
		Duration duration = Duration.ofMillis(interval);
		
		for (ChronoUnit chronoUnit : validUnitsFromScheduledTask) {
			Long value = getMillisValue(duration, chronoUnit);

			if (value >= 1) {
				return value.toString();
			}
		}

		return null;
	}

	private static String pluralize(ChronoUnit unit, long value) {
		ResourceBundle resourceBundle = FacesUtils.getResourceBundle(ScheduledTaskSearchMBean.MESSAGES_SCHEDULED_TASK_SEARCH);
		
		String name = switch (unit) {
		case DAYS -> value == 1 ? resourceBundle.getString("day") : resourceBundle.getString("plural_day");
		case HOURS -> value == 1 ? resourceBundle.getString("hour") : resourceBundle.getString("plural_hour");
		case MINUTES -> value == 1 ? resourceBundle.getString("minute") : resourceBundle.getString("plural_minute");
		case SECONDS -> value == 1 ? resourceBundle.getString("second") : resourceBundle.getString("plural_second");
		default -> "";
		};

		return name;
	}

	private static long getMillisValue(Duration duration, ChronoUnit chronoUnit) {
		long value = switch (chronoUnit) {
		case DAYS -> duration.toDays();
		case HOURS -> duration.toHours();
		case MINUTES -> duration.toMinutes();
		case SECONDS -> duration.getSeconds();
		default -> 0L;
		};
		return value;
	}
}
