package acme.features.administrator.dashboard;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	@Autowired
	protected AdministratorDashboardRepository repository;

	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, 
			"totalNumberOfPublicTasks","totalNumberOfPrivateTasks",
			"totalNumberOfFinishedTasks","totalNumberOfNonFinishedTasks",
			"averageNumberOfTaskExecutionPeriods","deviationNumberOfTaskExecutionPeriods",
			"minimumNumberOfTaskExecutionPeriods","maximumNumberOfTaskExecutionPeriods",
			"averageNumberOfTaskWorkloads", "deviationNumberOfTaskWorkloads",
			"minimumNumberOfTaskWorkloads", "maximumNumberOfTaskWorkloads");
		
	}
	
	public Double getAverageNumberOfTaskExecutionPeriods (final Request<Dashboard> request) {
		assert request != null;
		
		Long res =  0L;
		final Set<Task> tasks= this.repository.findAllTasks();
		
		for(final Task task: tasks) {
			System.out.println(task.getWorkload());
			final Long executionPeriodDiff=task.getEndPeriod().getTime()-task.getStartPeriod().getTime();
			res+=executionPeriodDiff;
			System.out.println(res);
		}
		res = res/tasks.size();
		return res.doubleValue()*0.0000166667;
	}
	
	public Double getDeviationNumberOfTaskExecutionPeriods (final Request<Dashboard> request) {
		assert request != null;
		
		final Double num =  this.getAverageNumberOfTaskExecutionPeriods(request)/0.0000166667;
		final Long average= num.longValue();
		
		Long res =  0L;
		final Set<Task> tasks= this.repository.findAllTasks();
		
		for(final Task task: tasks) {
			System.out.println(task.getWorkload());
			final Long executionPeriodDiff = task.getEndPeriod().getTime()-task.getStartPeriod().getTime();
			final Long individualDeviation = Math.abs(executionPeriodDiff-average)^2;
			res+=individualDeviation;
			System.out.println(res);
		}
		res = res/tasks.size();
		return res.doubleValue()*0.0000166667;
	}
	
	public Double geMinNumberOfTaskExecutionPeriods (final Request<Dashboard> request) {
		assert request != null;
		
		final Double num =  this.geMaxNumberOfTaskExecutionPeriods(request)/0.0000166667;
		Long min= num.longValue();
		
		
		final Set<Task> tasks= this.repository.findAllTasks();
		
		for(final Task task: tasks) {
			final Long executionPeriodDiff=task.getEndPeriod().getTime()-task.getStartPeriod().getTime();
			if(min>executionPeriodDiff) {
				min=executionPeriodDiff;
			}
		}
		return min.doubleValue()*0.0000166667;
	}
	
	public Double geMaxNumberOfTaskExecutionPeriods (final Request<Dashboard> request) {
		assert request != null;
		
		Long max =  0L;
		final Set<Task> tasks= this.repository.findAllTasks();
		
		for(final Task task: tasks) {
			final Long executionPeriodDiff=task.getEndPeriod().getTime()-task.getStartPeriod().getTime();
			if(max<executionPeriodDiff) {
				max=executionPeriodDiff;
			}
		}
		return max.doubleValue()*0.0000166667;
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		final Dashboard result;
		
		final Double totalNumberOfPublicTasks;
		final Double totalNumberOfPrivateTasks;
		final Double totalNumberOfFinishedTasks;
		final Double totalNumberOfNonFinishedTasks;
		final Double averageNumberOfTaskExecutionPeriods;
		final Double deviationNumberOfTaskExecutionPeriods;
		final Double minimumNumberOfTaskExecutionPeriods;
		final Double maximumNumberOfTaskExecutionPeriods;
		final Double averageNumberOfTaskWorkloads;
		final Double deviationNumberOfTaskWorkloads;
		final Double minimumNumberOfTaskWorkloads;
		final Double maximumNumberOfTaskWorkloads;
		
		
		totalNumberOfPublicTasks = this.repository.totalNumberOfPublicTasks();
		totalNumberOfPrivateTasks = this.repository.totalNumberOfPrivateTasks();
		totalNumberOfFinishedTasks = this.repository.totalNumberOfFinishedTasks();
		totalNumberOfNonFinishedTasks = this.repository.totalNumberOfNonFinishedTasks();
		averageNumberOfTaskExecutionPeriods = this.getAverageNumberOfTaskExecutionPeriods(request);
		deviationNumberOfTaskExecutionPeriods = this.getDeviationNumberOfTaskExecutionPeriods(request);
		minimumNumberOfTaskExecutionPeriods = this.geMinNumberOfTaskExecutionPeriods(request);
		maximumNumberOfTaskExecutionPeriods = this.geMaxNumberOfTaskExecutionPeriods(request);
		averageNumberOfTaskWorkloads = this.repository.averageNumberOfTaskWorkloads();
		deviationNumberOfTaskWorkloads = this.repository.deviationNumberOfTaskWorkloads();
		minimumNumberOfTaskWorkloads = this.repository.minimumNumberOfTaskWorkloads();
		maximumNumberOfTaskWorkloads = this.repository.maximumNumberOfTaskWorkloads();
		
		result = new Dashboard();
		
		result.setTotalNumberOfPublicTasks(totalNumberOfPublicTasks);
		result.setTotalNumberOfPrivateTasks(totalNumberOfPrivateTasks);
		result.setTotalNumberOfFinishedTasks(totalNumberOfFinishedTasks);
		result.setTotalNumberOfNonFinishedTasks(totalNumberOfNonFinishedTasks);
		result.setAverageNumberOfTaskExecutionPeriods(averageNumberOfTaskExecutionPeriods);
		result.setDeviationNumberOfTaskExecutionPeriods(deviationNumberOfTaskExecutionPeriods);
		result.setMinimumNumberOfTaskExecutionPeriods(minimumNumberOfTaskExecutionPeriods);
		result.setMaximumNumberOfTaskExecutionPeriods(maximumNumberOfTaskExecutionPeriods);
		result.setAverageNumberOfTaskWorkloads(averageNumberOfTaskWorkloads);
		result.setDeviationNumberOfTaskWorkloads(deviationNumberOfTaskWorkloads);
		result.setMinimumNumberOfTaskWorkloads(minimumNumberOfTaskWorkloads);
		result.setMaximumNumberOfTaskWorkloads(maximumNumberOfTaskWorkloads);
		
		return result;
	}

}
