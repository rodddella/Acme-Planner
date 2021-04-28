package acme.features.administrator.spamWord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spamfilter.SpamWord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorSpamWordShowService implements AbstractShowService<Administrator, SpamWord> {
	@Autowired
	AdministratorSpamWordRepository repository;
	
	@Override
	public boolean authorise(Request<SpamWord> request) {
		assert request != null;
		
		return true;
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
		return repository.getSpamWordById(request.getModel().getInteger("id"));
	}
}
