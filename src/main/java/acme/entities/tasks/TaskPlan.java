package acme.entities.tasks;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.entities.workPlans.WorkPlan;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TaskPlan extends DomainEntity{
	protected static final long	serialVersionUID	= 1L;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	protected Task task;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	protected WorkPlan workPlan;

}
