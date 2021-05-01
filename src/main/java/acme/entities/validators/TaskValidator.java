
package acme.entities.validators;

import java.util.Date;

import org.springframework.stereotype.Component;


import acme.entities.tasks.Task;
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

		final long workLoadMilis = (long) (target.getWorkload() * TaskValidator.HOUR_TO_MILLIS);

		if (workLoadMilis > periodDuration) {
			errors.add("workload", "Workload cannot be greater than the task period.");
		}

	}

}
