
package acme.entities.validators;

import java.util.Date;

import org.springframework.stereotype.Component;


import acme.entities.tasks.Task;
import acme.forms.HoursAndMinutes;
import acme.framework.components.Errors;

@Component
public class TaskValidator {
	static final long HOUR_TO_MILLIS = 60 * 60 * 1000;

	public void validate(final Task target, final Errors errors) {
		final Date startPeriod = target.getStartPeriod();
		final Date endPeriod = target.getEndPeriod();

		if (startPeriod.after(endPeriod)) {
			errors.add("startPeriod", "Start period cannot be after end period.");
		}

		final long periodDuration = endPeriod.getTime() - startPeriod.getTime();

		double workLoad = 0.0;
		try {
			workLoad = HoursAndMinutes.fromFormattedTime(target.getWorkload()).getDecimalTime();
		} catch (Exception e) {
			errors.add("workload", "Invalid time format. Use hh.mm");
		}
		
		final long workLoadMilis = (long) (workLoad * TaskValidator.HOUR_TO_MILLIS);

		if (workLoadMilis > periodDuration) {
			errors.add("workload", "Workload cannot be greater than the task period.");
		}
	}
}
