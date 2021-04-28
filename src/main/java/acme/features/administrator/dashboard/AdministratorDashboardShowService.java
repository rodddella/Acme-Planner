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
	static final Double MILISECONDS_TO_MINUTES = 1.0 / (60 * 1000);
	static final Double DECIMAL_CONVERTER = 1 / 0.6;

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

		request.unbind(entity, model, "totalNumberOfPublicTasks", "totalNumberOfPrivateTasks",
				"totalNumberOfFinishedTasks", "totalNumberOfNonFinishedTasks", "averageNumberOfTaskExecutionPeriods",
				"deviationNumberOfTaskExecutionPeriods", "minimumNumberOfTaskExecutionPeriods",
				"maximumNumberOfTaskExecutionPeriods", "averageNumberOfTaskWorkloads", "deviationNumberOfTaskWorkloads",
				"minimumNumberOfTaskWorkloads", "maximumNumberOfTaskWorkloads");

	}
	public Double doDecimalTransformation(final Double workload) {
		final Double decimalWorkloadPart= workload-Math.floor(workload);
		final Double integerWorkloadPart=Math.floor(workload);
		return  integerWorkloadPart+(decimalWorkloadPart*AdministratorDashboardShowService.DECIMAL_CONVERTER);
	}
	
	public Double undoDecimalTransformation(final Double workload) {
		final Double decimalWorkloadPart= workload-Math.floor(workload);
		final Double integerWorkloadPart=Math.floor(workload);
		return  integerWorkloadPart+(decimalWorkloadPart/AdministratorDashboardShowService.DECIMAL_CONVERTER);
	}
	
	public String stringTimeTransformation(final Double workload) {
		final Double hours=Math.floor(workload);
		final Double minutes=Math.floor((workload-Math.floor(workload))*100);
		return String.format("%.2f hours %.2f minutes", hours,minutes);
	}
	
	public Double getAverageNumberOfTaskWorkloads(final Request<Dashboard> request) {
		assert request != null;

		final Set<Task> tasks = this.repository.findAllTasks();
		
		if(tasks.isEmpty()) {
			return 0.0;
		}else {
			Double averageWorkloads = 0.0;
			
			for (final Task task : tasks) {
				final Double decimalWorkLoad= this.doDecimalTransformation(task.getWorkload());
				averageWorkloads += decimalWorkLoad;
			}
			
			averageWorkloads = this.undoDecimalTransformation(averageWorkloads / tasks.size());
			return averageWorkloads;
		}
	}
	
	public Double getDeviationNumberOfTaskWorkloads(final Request<Dashboard> request) {
		assert request != null;

		final Set<Task> tasks = this.repository.findAllTasks();
		
		if(tasks.isEmpty()) {
			return 0.0;
		}else {
			Double deviationWorkloads = 0.0;
			final Double averageWorkloads = this.doDecimalTransformation(this.getAverageNumberOfTaskWorkloads(request));
			
			for (final Task task : tasks) {
				final Double decimalWorkLoad= this.doDecimalTransformation(task.getWorkload());
				final Double individualDeviation = Math.pow(decimalWorkLoad - averageWorkloads, 2);
				deviationWorkloads += individualDeviation;
			}
			
			deviationWorkloads = this.undoDecimalTransformation(averageWorkloads / tasks.size());
			return deviationWorkloads;
		}
	}
	public Double getMinimumNumberOfTaskWorkloads(final Request<Dashboard> request) {
		assert request != null;

		final Set<Task> tasks = this.repository.findAllTasks();
		
		if(tasks.isEmpty()) {
			return 0.0;
		}else {
			Double minTimeWorkload = this.doDecimalTransformation(this.getMaximumNumberOfTaskWorkloads(request));

			for (final Task task : tasks) {
				final Double decimalWorkLoad= this.doDecimalTransformation(task.getWorkload());
				if (minTimeWorkload > decimalWorkLoad) {
					minTimeWorkload = decimalWorkLoad;
				}
			}
			return this.undoDecimalTransformation(minTimeWorkload);
		}
	}
	
	public Double getMaximumNumberOfTaskWorkloads(final Request<Dashboard> request) {
		assert request != null;

		final Set<Task> tasks = this.repository.findAllTasks();
		
		if(tasks.isEmpty()) {
			return 0.0;
		}else {
			Double maxTimeWorkload = 0.0;
			
			for (final Task task : tasks) {
				final Double decimalWorkLoad= this.doDecimalTransformation(task.getWorkload());
				if (maxTimeWorkload < decimalWorkLoad) {
					maxTimeWorkload = decimalWorkLoad;
				}
			}
			return this.undoDecimalTransformation(maxTimeWorkload);
		}
	}
	public Double getAverageNumberOfTaskExecutionPeriods(final Request<Dashboard> request) {
		assert request != null;

		final Set<Task> tasks = this.repository.findAllTasks();
		
		if(tasks.isEmpty()) {
			return 0.0;
		}else {
			Long averageTimeExecutionPeriods = 0L;
			
			for (final Task task : tasks) {
				final Long executionPeriodDiff = task.getEndPeriod().getTime() - task.getStartPeriod().getTime();
				averageTimeExecutionPeriods += executionPeriodDiff;
			}
			
			averageTimeExecutionPeriods = averageTimeExecutionPeriods / tasks.size();
			return averageTimeExecutionPeriods.doubleValue() * AdministratorDashboardShowService.MILISECONDS_TO_MINUTES;
		}
	}

	public Double getDeviationNumberOfTaskExecutionPeriods(final Request<Dashboard> request) {
		assert request != null;

		final Set<Task> tasks = this.repository.findAllTasks();
		
		if(tasks.isEmpty()) {
			return 0.0;
		}else {
			final Double averageTaskExecutionPeriods = this.getAverageNumberOfTaskExecutionPeriods(request)
				/ AdministratorDashboardShowService.MILISECONDS_TO_MINUTES;
			Double deviationTimeExecutionPeriods = 0.0;
			
			for (final Task task : tasks) {
				final Long executionPeriodDiff = task.getEndPeriod().getTime() - task.getStartPeriod().getTime();
				final Double individualDeviation = Math.pow(executionPeriodDiff - averageTaskExecutionPeriods, 2);
				deviationTimeExecutionPeriods += individualDeviation;
			}
			
			deviationTimeExecutionPeriods = Math.sqrt(deviationTimeExecutionPeriods / tasks.size());
			return deviationTimeExecutionPeriods * AdministratorDashboardShowService.MILISECONDS_TO_MINUTES;
		}
	}

	public Double getMinNumberOfTaskExecutionPeriods(final Request<Dashboard> request) {
		assert request != null;

		final Set<Task> tasks = this.repository.findAllTasks();

		if (tasks.isEmpty()) {
			return 0.0;
		} else {
			final Double maxTimeExecutionPeriod = this.getMaxNumberOfTaskExecutionPeriods(request) 
				/ AdministratorDashboardShowService.MILISECONDS_TO_MINUTES;
			Long minTimeExecutionPeriod = maxTimeExecutionPeriod.longValue();

			for (final Task task : tasks) {
				final Long executionPeriodDiff = task.getEndPeriod().getTime() - task.getStartPeriod().getTime();
				if (minTimeExecutionPeriod > executionPeriodDiff) {
					minTimeExecutionPeriod = executionPeriodDiff;
				}
			}
			return minTimeExecutionPeriod.doubleValue() * AdministratorDashboardShowService.MILISECONDS_TO_MINUTES;
		}
	}

	public Double getMaxNumberOfTaskExecutionPeriods(final Request<Dashboard> request) {
		assert request != null;
		final Set<Task> tasks = this.repository.findAllTasks();
		if(tasks.isEmpty()) {
			return 0.0;
		}else {	
			Long maxTimeExecutionPeriod = 0L;
			
			for (final Task task : tasks) {
				final Long executionPeriodDiff = task.getEndPeriod().getTime() - task.getStartPeriod().getTime();
				if (maxTimeExecutionPeriod < executionPeriodDiff) {
					maxTimeExecutionPeriod = executionPeriodDiff;
				}
			}
			return maxTimeExecutionPeriod.doubleValue() * AdministratorDashboardShowService.MILISECONDS_TO_MINUTES;
		}
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		final Dashboard result = new Dashboard();

		result.setTotalNumberOfPublicTasks(this.repository.totalNumberOfPublicTasks());
		result.setTotalNumberOfPrivateTasks(this.repository.totalNumberOfPrivateTasks());
		result.setTotalNumberOfFinishedTasks(this.repository.totalNumberOfFinishedTasks());
		result.setTotalNumberOfNonFinishedTasks(this.repository.totalNumberOfNonFinishedTasks());
		result.setAverageNumberOfTaskExecutionPeriods(this.stringTimeTransformation(this.getAverageNumberOfTaskExecutionPeriods(request)));
		result.setDeviationNumberOfTaskExecutionPeriods(this.stringTimeTransformation(this.getDeviationNumberOfTaskExecutionPeriods(request)));
		result.setMinimumNumberOfTaskExecutionPeriods(this.stringTimeTransformation(this.getMinNumberOfTaskExecutionPeriods(request)));
		result.setMaximumNumberOfTaskExecutionPeriods(this.stringTimeTransformation(this.getMaxNumberOfTaskExecutionPeriods(request)));
		result.setAverageNumberOfTaskWorkloads(this.stringTimeTransformation(this.getAverageNumberOfTaskWorkloads(request)));
		result.setDeviationNumberOfTaskWorkloads(this.stringTimeTransformation(this.getDeviationNumberOfTaskWorkloads(request)));
		result.setMinimumNumberOfTaskWorkloads(this.stringTimeTransformation(this.getMinimumNumberOfTaskWorkloads(request)));
		result.setMaximumNumberOfTaskWorkloads(this.stringTimeTransformation(this.getMaximumNumberOfTaskWorkloads(request)));

		return result;
	}
}
