package acme.entities.roles;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import acme.entities.tasks.Task;
import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Manager extends UserRole {
	protected static final long serialVersionUID = 1L;

	@NotBlank
	protected String company;

	@NotBlank
	protected String sector;

	@OneToMany(mappedBy = "manager")
	Set<Task> tasks;
}
