package acme.entities.validators;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import acme.entities.tasks.Task;
import acme.entities.tasks.TaskPlan;
import acme.entities.workPlans.WorkPlan;
import acme.enums.Visibility;

public class WorkPlanValidator implements Validator{

	@Override
	public boolean supports(final Class<?> clazz) {
		return WorkPlanValidator.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {
		 final WorkPlan workPlan = (WorkPlan) target;
		 
		 final Date startPeriod = workPlan.getStartPeriod();
		 final Date endPeriod = workPlan.getEndPeriod();
		 final Visibility visibility = workPlan.getVisibility();
		 
		 for (final TaskPlan taskPlan: workPlan.getTaskPlans()) {
			 final Task task= taskPlan.getTask();
			 if(task.getStartPeriod().before(startPeriod)) {
				 errors.rejectValue("startPeriod", "The start date of the task must not be earlier than the start date of the work plan.");
			 }else if(task.getStartPeriod().after(endPeriod)) {
				 errors.rejectValue("endPeriod", "The end date of the task must not be later than the end date of the work plan.");
			 }else if(task.getVisibility().equals(Visibility.PRIVATE) && visibility.equals(Visibility.PUBLIC) ){
				 errors.rejectValue("visibility", "A public work plan cannot contain private tasks.");
			 }
		 }
		
	}

}
