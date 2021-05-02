package acme.entities.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Errors;
import acme.framework.components.Request;

@Service
public class SpamFilterService {
	@Autowired
	SpamFilterRepository repository;
	
	public SpamFilterValidator getValidator() {
		return new SpamFilterValidator(repository.getSpamThresholds().get(0), repository.getSpamWords());
	}
	
	public void validate(final Request<?> request, final String attribute, final String text, final Errors errors) {
		getValidator().validate(request, attribute, text, errors);
	}
}
