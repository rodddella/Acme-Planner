package acme.framework.repositories;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;

@Repository
@Transactional(TxType.SUPPORTS)
public interface TaskRepository extends AbstractRepository {

	@Query("SELECT task FROM Task task where task.id = ?1")
	Optional<Task> findOne(int id);

}
