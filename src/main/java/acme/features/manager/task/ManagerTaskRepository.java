package acme.features.manager.task;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerTaskRepository extends AbstractRepository {

	@Query("SELECT m FROM Manager m WHERE m.id = ?1")
	Optional<Manager> findManagerById(Integer id);
	
	@Query("SELECT t FROM Task t JOIN Manager m where t.id = :taskId and m.id = :managerId")
	Task findTaskByIdAndManager(@Param("taskId") Integer taskId, @Param("managerId") Integer managerId);
}
