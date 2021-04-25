package acme.entities.spam;

import java.util.Set;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SpamFilter {

	Set<String> words;

	@Min(0)
	@Max(100)
	Double threshold;
}
