package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable{
	
	protected static final long	serialVersionUID	= 1L;
	
	
	Double totalNumberOfPublicTasks;
	Double totalNumberOfPrivateTasks;
	Double totalNumberOfFinishedTasks;
	Double totalNumberOfNonFinishedTasks;
	Double averageNumberOfTaskExecutionPeriods;
	Double deviationNumberOfTaskExecutionPeriods;
	Double minimumNumberOfTaskExecutionPeriods;
	Double maximumNumberOfTaskExecutionPeriods;
	Double averageNumberOfTaskWorkloads;
	Double deviationNumberOfTaskWorkloads;
	Double minimumNumberOfTaskWorkloads;
	Double maximumNumberOfTaskWorkloads;

}
