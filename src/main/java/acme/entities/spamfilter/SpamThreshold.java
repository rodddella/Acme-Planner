package acme.entities.spamfilter;

import javax.persistence.Entity;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SpamThreshold extends DomainEntity {
	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 21358278L;

	// Attributes -------------------------------------------------------------

	@NotNull
	@DecimalMin(value = "0.0")
	@DecimalMax(value = "100.0")
	Double value;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}
