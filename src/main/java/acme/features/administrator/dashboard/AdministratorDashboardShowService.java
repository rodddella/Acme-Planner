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

	public Double getAverageNumberOfTaskExecutionPeriods(final Request<Dashboard> request) {
		assert request != null;

		Long averageTimeExecutionPeriods = 0L;
		final Set<Task> tasks = this.repository.findAllTasks();

		for (final Task task : tasks) {
			final Long executionPeriodDiff = task.getEndPeriod().getTime() - task.getStartPeriod().getTime();
			averageTimeExecutionPeriods += executionPeriodDiff;
		}
		
		averageTimeExecutionPeriods = averageTimeExecutionPeriods / tasks.size();
		return averageTimeExecutionPeriods.doubleValue() * AdministratorDashboardShowService.MILISECONDS_TO_MINUTES;
	}

	public Double getDeviationNumberOfTaskExecutionPeriods(final Request<Dashboard> request) {
		assert request != null;

		final Double averageTaskExecutionPeriods = this.getAverageNumberOfTaskExecutionPeriods(request)
				/ AdministratorDashboardShowService.MILISECONDS_TO_MINUTES;

		Double deviationTimeExecutionPeriods = 0.0;
		final Set<Task> tasks = this.repository.findAllTasks();

		for (final Task task : tasks) {
			final Long executionPeriodDiff = task.getEndPeriod().getTime() - task.getStartPeriod().getTime();
			final Double individualDeviation = Math.pow(executionPeriodDiff - averageTaskExecutionPeriods, 2);
			deviationTimeExecutionPeriods += individualDeviation;
		}
		
		deviationTimeExecutionPeriods = Math.sqrt(deviationTimeExecutionPeriods / tasks.size());
		return deviationTimeExecutionPeriods * AdministratorDashboardShowService.MILISECONDS_TO_MINUTES;
	}

	public Double getMinNumberOfTaskExecutionPeriods(final Request<Dashboard> request) {
		assert request != null;

		final Double maxTimeExecutionPeriod = this.getMaxNumberOfTaskExecutionPeriods(request) / 0.0000166667;
		Long minTimeExecutionPeriod = maxTimeExecutionPeriod.longValue();

		final Set<Task> tasks = this.repository.findAllTasks();

		for (final Task task : tasks) {
			final Long executionPeriodDiff = task.getEndPeriod().getTime() - task.getStartPeriod().getTime();
			if (minTimeExecutionPeriod > executionPeriodDiff) {
				minTimeExecutionPeriod = executionPeriodDiff;
			}
		}
		
		return minTimeExecutionPeriod.doubleValue() * AdministratorDashboardShowService.MILISECONDS_TO_MINUTES;
	}

	public Double getMaxNumberOfTaskExecutionPeriods(final Request<Dashboard> request) {
		assert request != null;

		Long maxTimeExecutionPeriod = 0L;
		final Set<Task> tasks = this.repository.findAllTasks();

		for (final Task task : tasks) {
			final Long executionPeriodDiff = task.getEndPeriod().getTime() - task.getStartPeriod().getTime();
			if (maxTimeExecutionPeriod < executionPeriodDiff) {
				maxTimeExecutionPeriod = executionPeriodDiff;
			}
		}
		return maxTimeExecutionPeriod.doubleValue() * AdministratorDashboardShowService.MILISECONDS_TO_MINUTES;
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		final Dashboard result = new Dashboard();

		result.setTotalNumberOfPublicTasks(this.repository.totalNumberOfPublicTasks());
		result.setTotalNumberOfPrivateTasks(this.repository.totalNumberOfPrivateTasks());
		result.setTotalNumberOfFinishedTasks(this.repository.totalNumberOfFinishedTasks());
		result.setTotalNumberOfNonFinishedTasks(this.repository.totalNumberOfNonFinishedTasks());
		result.setAverageNumberOfTaskExecutionPeriods(this.getAverageNumberOfTaskExecutionPeriods(request));
		result.setDeviationNumberOfTaskExecutionPeriods(this.getDeviationNumberOfTaskExecutionPeriods(request));
		result.setMinimumNumberOfTaskExecutionPeriods(this.getMinNumberOfTaskExecutionPeriods(request));
		result.setMaximumNumberOfTaskExecutionPeriods(this.getMaxNumberOfTaskExecutionPeriods(request));
		result.setAverageNumberOfTaskWorkloads(this.repository.averageNumberOfTaskWorkloads());
		result.setDeviationNumberOfTaskWorkloads(this.repository.deviationNumberOfTaskWorkloads());
		result.setMinimumNumberOfTaskWorkloads(this.repository.minimumNumberOfTaskWorkloads());
		result.setMaximumNumberOfTaskWorkloads(this.repository.maximumNumberOfTaskWorkloads());

		return result;
	}
}
