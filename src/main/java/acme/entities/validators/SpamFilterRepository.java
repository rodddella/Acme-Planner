package acme.entities.validators;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spamfilter.SpamThreshold;
import acme.entities.spamfilter.SpamWord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SpamFilterRepository extends AbstractRepository {
	@Query("SELECT threshold FROM SpamThreshold threshold ORDER BY threshold.id DESC")
	List<SpamThreshold> getSpamThresholds();
	
	@Query("SELECT word FROM SpamWord word ORDER BY word.text ASC")
	List<SpamWord> getSpamWords();
}
