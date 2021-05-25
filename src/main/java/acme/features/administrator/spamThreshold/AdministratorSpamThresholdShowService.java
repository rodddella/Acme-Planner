package acme.features.administrator.spamThreshold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spamfilter.SpamThreshold;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorSpamThresholdShowService implements AbstractShowService<Administrator, SpamThreshold> {
	@Autowired
	AdministratorSpamThresholdRepository repository;

	@Override
	public boolean authorise(Request<SpamThreshold> request) {
		return true;
	}

	@Override
	public void unbind(Request<SpamThreshold> request, SpamThreshold entity, Model model) {
		request.unbind(entity, model, "value");
	}

	@Override
	public SpamThreshold findOne(Request<SpamThreshold> request) {
		return repository.getSpamThresholds().get(0);
	}
}
