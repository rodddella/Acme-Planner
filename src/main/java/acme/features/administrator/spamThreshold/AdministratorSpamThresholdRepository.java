package acme.features.administrator.spamThreshold;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spamfilter.SpamThreshold;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSpamThresholdRepository extends AbstractRepository {
	@Query("SELECT threshold FROM SpamThreshold threshold ORDER BY threshold.id DESC")
	List<SpamThreshold> getSpamThresholds();
}
