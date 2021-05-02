package acme.entities.spamfilter;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SpamWord extends DomainEntity {
	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 23423423489L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(min = 0, max = 255)
	protected String text;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}
