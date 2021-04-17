package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;

import acme.framework.repositories.AbstractRepository;

public interface AdministratorDashboardRepository extends AbstractRepository{
	
	@Query("select count(t) from Task t where t.visibility = 'Public'")
	Double totalNumberOfPublicTasks ();
	
	@Query("select count(t) from Task t where t.visibility = 'Private'")
	Double totalNumberOfPrivateTasks ();
	
	@Query("select count(t) from Task t where t.endPeriod <= current_timestamp()")
	Double totalNumberOfFinishedTasks ();
	
	@Query("select count(t) from Task t where t.endPeriod > current_timestamp()")
	Double totalNumberOfNonFinishedTasks ();
	
	
	Double averageNumberOfTaskExecutionPeriods ();
	
	
	Double deviationNumberOfTaskExecutionPeriods ();
	
	
	Double minimumNumberOfTaskExecutionPeriods ();
	
	
	Double maximumNumberOfTaskExecutionPeriods ();
	
	@Query("select avg(t.workload) from Task t")
	Double averageNumberOfTaskWorkloads ();
	
	@Query("select stddev(t.workload) from Task t")
	Double deviationNumberOfTaskWorkloads ();
	
	@Query("select min(t.workload) from Task t")
	Double minimumNumberOfTaskWorkloads ();
	
	@Query("select max(t.workload) from Task t")
	Double maximumNumberOfTaskWorkloads ();
}
