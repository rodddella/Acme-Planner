package acme.features.authenticated.task;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedTaskListService implements AbstractListService<Authenticated, Task> {
	@Autowired
	AuthenticatedTaskRepository authenticatedTaskRepository;
	
	@Override
	public boolean authorise(Request<Task> request) {
		assert request != null;
		return true;
	}
	
	@Override
	public void unbind(Request<Task> request, Task entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "description", "workload", "link", "startPeriod", "endPeriod", "visibility");
	}

	@Override
	public Collection<Task> findMany(Request<Task> request) {
		return authenticatedTaskRepository.findManyFinished(Date.from(Instant.now()));
	}
}
