
package acme.entities.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import acme.entities.spam.SpamFilter;

@Component
public class SpamFilterValidator implements Validator {

	SpamFilter spamFilter;


	public SpamFilterValidator(final SpamFilter spamFilter) {
		this.spamFilter = spamFilter;
	}

	@Override
	public boolean supports(final Class<?> clazz) {
		return String.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {
		final String phrase = ((String) target).toLowerCase();;

		final Double spamCount = this.spamFilter.getWords().stream().map(censor -> {

			final Matcher m = Pattern.compile(censor).matcher(phrase);
			double count = 0;
			while (m.find())
				count++;
			return count;
		}).reduce(0.0, (x, y) -> x + y);
		
		final Double threshold = (spamCount / phrase.split(" ").length)*100;
		
		if(this.spamFilter.getThreshold() < threshold) {
			errors.reject("acme.validation.spam");
		}

	}

}
