package acme.features.administrator.spamWord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spamfilter.SpamWord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorSpamWordCreateService implements AbstractCreateService<Administrator, SpamWord> {
	@Autowired
	AdministratorSpamWordRepository repository;

	@Override
	public boolean authorise(Request<SpamWord> request) {
		return true;
	}

	@Override
	public void bind(Request<SpamWord> request, SpamWord entity, Errors errors) {
		request.bind(entity, errors);
	}

	@Override
	public void unbind(Request<SpamWord> request, SpamWord entity, Model model) {
		request.unbind(entity, model, "text");
	}

	@Override
	public SpamWord instantiate(Request<SpamWord> request) {
		SpamWord word = new SpamWord();
		word.setText("");

		return word;
	}

	@Override
	public void validate(Request<SpamWord> request, SpamWord entity, Errors errors) {
		if (!errors.hasErrors()) {
			errors.state(request, repository.getSpamWordsByText(entity.getText()).isEmpty(), "text",
					"acme.validation.already-exists");
		}
	}

	@Override
	public void create(Request<SpamWord> request, SpamWord entity) {
		repository.save(entity);
	}
}
