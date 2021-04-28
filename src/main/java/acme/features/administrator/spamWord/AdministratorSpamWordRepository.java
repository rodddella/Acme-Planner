package acme.features.administrator.spamWord;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.spamfilter.SpamWord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSpamWordRepository extends AbstractRepository {
	@Query("SELECT word FROM SpamWord word ORDER BY word.text ASC")
	List<SpamWord> getSpamWords();
	
	@Query("SELECT word FROM SpamWord word WHERE word.id = :id")
	SpamWord getSpamWordById(@Param("id") Integer id);
	
	@Query("SELECT word FROM SpamWord word WHERE lower(word.text) LIKE lower(concat('%', :text, '%'))")
	SpamWord getSpamWordByCaseInsensitiveText(@Param("text") String text);
}
