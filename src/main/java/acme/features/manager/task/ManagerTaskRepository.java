package acme.features.manager.task;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Manager;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerTaskRepository extends AbstractRepository {

	@Query("SELECT m from Manager m where m.id = ?1")
	Optional<Manager> findManagerById(Integer id);
}
