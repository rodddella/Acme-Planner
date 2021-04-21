package acme.entities.tasks;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import acme.entities.workPlans.WorkPlan;
import acme.enums.Visibility;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task extends DomainEntity {

	static final long serialVersionUID = 8331441137571663368L;

	@NotEmpty
	@Length(max = 80, min = 0)
	String title;
	
	@NotEmpty
	@Length(max = 500, min = 0)
	String description;
	
	@NotNull
	Double workload;
	
	String link;
	
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
	@ManyToMany()
	protected Set<WorkPlan> workPlans;
	
	
}
