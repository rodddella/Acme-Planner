package acme.features.manager.task;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerTaskRepository extends AbstractRepository {
	@Query("SELECT task FROM Task task JOIN Manager manager WHERE task.id = :taskId AND task.manager.id = :managerId")
	Task findTaskByIdAndManager(@Param("taskId") Integer taskId, @Param("managerId") Integer managerId);

	@Query("SELECT manager FROM Manager manager WHERE manager.id = :managerId")
	Manager findManagerById(@Param("managerId") Integer managerId);

	@Query("SELECT task FROM Task task WHERE task.id = :taskId")
	Task findTaskById(@Param("taskId") Integer taskId);
}
