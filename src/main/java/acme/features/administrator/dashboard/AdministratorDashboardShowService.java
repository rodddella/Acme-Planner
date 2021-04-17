package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

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
			"totalNumberOfPublicAndPrivateTasks", "totalNumberOfFinishedAndNonFinishedTasks", 
			"averageNumberOfTaskExecutionPeriods", "deviationNumberOfTaskExecutionPeriods", 
			"minimumNumberOfTaskExecutionPeriods", "maximumNumberOfTaskExecutionPeriods",
			"averageNumberOfTaskWorkloads", "deviationNumberOfTaskWorkloads",
			"minimumNumberOfTaskWorkloads", "maximumNumberOfTaskWorkloads");
		
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
		averageNumberOfTaskExecutionPeriods = this.repository.averageNumberOfTaskExecutionPeriods();
		deviationNumberOfTaskExecutionPeriods = this.repository.deviationNumberOfTaskExecutionPeriods();
		minimumNumberOfTaskExecutionPeriods = this.repository.minimumNumberOfTaskExecutionPeriods();
		maximumNumberOfTaskExecutionPeriods = this.repository.maximumNumberOfTaskExecutionPeriods();
		averageNumberOfTaskWorkloads = this.repository.averageNumberOfTaskWorkloads();
		deviationNumberOfTaskWorkloads = this.repository.deviationNumberOfTaskWorkloads();
		minimumNumberOfTaskWorkloads = this.repository.minimumNumberOfTaskWorkloads();
		maximumNumberOfTaskWorkloads = this.repository.minimumNumberOfTaskWorkloads();
		
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
