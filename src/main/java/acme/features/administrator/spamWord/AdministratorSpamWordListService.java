package acme.features.administrator.spamWord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spamfilter.SpamWord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorSpamWordListService implements AbstractListService<Administrator, SpamWord> {
	@Autowired
	AdministratorSpamWordRepository repository;

	@Override
	public boolean authorise(Request<SpamWord> request) {
		return true;
	}

	@Override
	public void unbind(Request<SpamWord> request, SpamWord entity, Model model) {
		request.unbind(entity, model, "text");
	}

	@Override
	public Collection<SpamWord> findMany(Request<SpamWord> request) {
		return repository.getSpamWords();
	}
}
