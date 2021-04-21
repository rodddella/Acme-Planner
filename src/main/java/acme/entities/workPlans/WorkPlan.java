package acme.entities.workPlans;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.entities.tasks.Task;
import acme.enums.Visibility;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WorkPlan extends DomainEntity{
	protected static final long	serialVersionUID	= 1L;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date startPeriod;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date endPeriod;	

	@Enumerated(EnumType.STRING)
	Visibility visibility;
	
	@NotNull
	@Valid
	@ManyToMany(mappedBy="workPlans")
	protected Set<Task> tasks;

}
