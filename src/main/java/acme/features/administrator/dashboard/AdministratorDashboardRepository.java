package acme.features.administrator.dashboard;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository{
	
	@Query("select t from Task t ")
	Set<Task> findAllTasks ();
	
	@Query("select count(t) from Task t where t.visibility = 'Public'")
	Double totalNumberOfPublicTasks ();
		
	@Query("select count(t) from Task t where t.visibility = 'Private'")
	Double totalNumberOfPrivateTasks ();
	
	@Query("select count(t) from Task t where t.endPeriod <= current_timestamp()")
	Double totalNumberOfFinishedTasks ();
	
	@Query("select count(t) from Task t where t.endPeriod > current_timestamp()")
	Double totalNumberOfNonFinishedTasks ();
	
	@Query("select avg(t.workload) from Task t")
	Double averageNumberOfTaskWorkloads ();
	
	@Query("select stddev(t.workload) from Task t")
	Double deviationNumberOfTaskWorkloads ();
	
	@Query("select min(t.workload) from Task t")
	Double minimumNumberOfTaskWorkloads ();
	
	@Query("select max(t.workload) from Task t")
	Double maximumNumberOfTaskWorkloads ();
}