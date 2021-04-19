package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository{
	
	@Query("select count(t) from Task t where t.visibility = 'Public'")
	Double totalNumberOfPublicTasks ();
	
	
	@Query("select count(t) from Task t where t.visibility = 'Private'")
	Double totalNumberOfPrivateTasks ();
	
	
	@Query("select count(t) from Task t where t.endPeriod <= current_timestamp()")
	Double totalNumberOfFinishedTasks ();
	
	
	@Query("select count(t) from Task t where t.endPeriod > current_timestamp()")
	Double totalNumberOfNonFinishedTasks ();
	
	@Query("select avg((day(t.endPeriod)*24 + hour(t.endPeriod))-(day(t.startPeriod)*24 + hour(t.startPeriod))) from Task t")
	Double averageNumberOfTaskExecutionPeriods ();
	
	@Query("select stddev((day(t.endPeriod)*24 + hour(t.endPeriod))-(day(t.startPeriod)*24 + hour(t.startPeriod))) from Task t")
	Double deviationNumberOfTaskExecutionPeriods ();
	
	@Query("select min((day(t.endPeriod)*24 + hour(t.endPeriod))-(day(t.startPeriod)*24 + hour(t.startPeriod))) from Task t")
	Double minimumNumberOfTaskExecutionPeriods ();
	
	@Query("select max((day(t.endPeriod)*24 + hour(t.endPeriod))-(day(t.startPeriod)*24 + hour(t.startPeriod))) from Task t")
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
