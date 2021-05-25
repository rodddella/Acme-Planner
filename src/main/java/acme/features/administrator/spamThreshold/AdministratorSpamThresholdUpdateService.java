package acme.features.administrator.spamThreshold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spamfilter.SpamThreshold;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorSpamThresholdUpdateService implements AbstractUpdateService<Administrator, SpamThreshold> {
	@Autowired
	AdministratorSpamThresholdRepository repository;

	@Override
	public boolean authorise(Request<SpamThreshold> request) {
		return true;
	}

	@Override
	public void bind(Request<SpamThreshold> request, SpamThreshold entity, Errors errors) {
		request.bind(entity, errors);
	}

	@Override
	public void unbind(Request<SpamThreshold> request, SpamThreshold entity, Model model) {
		//Not used
	}

	@Override
	public SpamThreshold findOne(Request<SpamThreshold> request) {
		return repository.getSpamThresholds().get(0);
	}

	@Override
	public void validate(Request<SpamThreshold> request, SpamThreshold entity, Errors errors) {
		//Not used
	}

	@Override
	public void update(Request<SpamThreshold> request, SpamThreshold entity) {
		repository.save(entity);
	}
}
