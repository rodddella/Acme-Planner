package acme.features.administrator.spamWord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spamfilter.SpamWord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractDeleteService;

@Service
public class AdministratorSpamWordDeleteService implements AbstractDeleteService<Administrator, SpamWord> {
	@Autowired
	AdministratorSpamWordRepository repository;

	@Override
	public boolean authorise(Request<SpamWord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(Request<SpamWord> request, SpamWord entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(Request<SpamWord> request, SpamWord entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "text");
	}

	@Override
	public SpamWord findOne(Request<SpamWord> request) {
		assert request != null;

		return repository.getSpamWordById(request.getModel().getInteger("id"));
	}

	@Override
	public void validate(Request<SpamWord> request, SpamWord entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(Request<SpamWord> request, SpamWord entity) {
		assert request != null;
		assert entity != null;

		repository.delete(entity);
	}
}
