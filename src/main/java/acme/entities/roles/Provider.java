/*
 * Provider.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.entities.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Provider extends UserRole {

	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(min = 0, max = 255)
	protected String company;

	@NotBlank
	@Length(min = 0, max = 255)
	protected String sector;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
