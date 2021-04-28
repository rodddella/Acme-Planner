package acme.features.manager.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class ManagerTaskShowService implements AbstractShowService<Manager, Task> {

	@Autowired
	ManagerTaskRepository repository;
	
	@Override
	public boolean authorise(final Request<Task> request) {
		final Integer managerId = request.getPrincipal().getAccountId();
		final Integer taskId = request.getModel().getInteger("id");
		
		assert managerId != null;
		assert taskId != null;
		
		final Task task = this.repository.findTaskByIdAndManager(taskId, managerId);
		
		return task != null;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "workload", "link", "startPeriod", "endPeriod", "visibility");
	}

	@Override
	public Task findOne(final Request<Task> request) {
		final Integer taskId = request.getModel().getInteger("id");
		return (Task)this.repository.findById(taskId).get();
	}

}
