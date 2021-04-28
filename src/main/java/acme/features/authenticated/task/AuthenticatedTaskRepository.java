package acme.features.authenticated.task;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
@Transactional(TxType.SUPPORTS)
public interface AuthenticatedTaskRepository extends AbstractRepository  {
	@Query("SELECT task FROM Task task WHERE task.endPeriod < :endDate AND task.visibility = 'PUBLIC' ORDER BY task.startPeriod ASC, task.workload DESC")
	Collection<Task> findManyFinished(@Param("endDate") Date endDate);
	
	@Query("SELECT task FROM Task task where task.id = ?1 and task.visibility = 'PUBLIC")
	Task findOneTaskById(@Param("id") int id);	
}
