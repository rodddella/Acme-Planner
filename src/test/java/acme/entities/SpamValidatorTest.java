package acme.entities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import acme.entities.spam.SpamFilter;
import acme.entities.validators.SpamFilterValidator;

public class SpamValidatorTest {

	
	@Test
	void testSpamValidatorPositive() {
		final SpamFilter spamFilter = new SpamFilter();
		final Set<String> words = new HashSet<String>(Arrays.asList("sex","viagra","you've won"));
		
		spamFilter.setWords(words);
		spamFilter.setThreshold(10.0);
		
		final String phrase = "Congratulations! You've won a pack of viagra to celebrate with you loved ones having sex";
		final SpamFilterValidator validator = new SpamFilterValidator(spamFilter);
		final Errors errors = new BeanPropertyBindingResult(phrase, "");
		validator.validate(phrase, errors);
		
		assert errors.hasErrors();
	}
	
	@Test
	void testSpamValidatorNegative() {
		final SpamFilter spamFilter = new SpamFilter();
		final Set<String> words = new HashSet<String>(Arrays.asList("sex","viagra","you've won"));
		
		spamFilter.setWords(words);
		spamFilter.setThreshold(10.0);
		
		final String phrase = "Hello! I am an old friend from college, I see that you've won the lottery, I am very happy for you!";
		final SpamFilterValidator validator = new SpamFilterValidator(spamFilter);
		final Errors errors = new BeanPropertyBindingResult(phrase, "");
		validator.validate(phrase, errors);
		
		assert !errors.hasErrors();
	}
	
	
}
