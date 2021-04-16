package acme.entities.tasks;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import acme.enums.Visibility;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task extends DomainEntity {

	static final long serialVersionUID = 8331441137571663368L;

	@Column
	@NotEmpty
	@Length(max = 80, min = 0)
	String title;
	
	@Column
	@NotEmpty
	@Length(max = 500, min = 0)
	String description;
	
	@Column
	@NotNull
	Double workload;
	
	@Column
	String link;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@Future
	@NotNull
	protected Date startPeriod;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@Future
	@NotNull
	protected Date endPeriod;	
	
	@Column
	@Enumerated(EnumType.STRING)
	Visibility visibility;
	
	
}
