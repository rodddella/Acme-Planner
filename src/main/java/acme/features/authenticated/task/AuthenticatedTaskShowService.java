
package acme.features.authenticated.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.enums.Visibility;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedTaskShowService implements AbstractShowService<Authenticated, Task> {

	@Autowired
	protected AuthenticatedTaskRepository repository;


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		return false;
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
		// TODO Auto-generated method stub
		assert request != null;
		Task result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneTaskById(id);
		assert this.isAccesible(result);
		return result;

	}

	private Boolean isAccesible(final Task task) {
			return task.getEndPeriod().getTime() < (System.currentTimeMillis()) && task.getVisibility() == Visibility.PUBLIC;
	}
}
