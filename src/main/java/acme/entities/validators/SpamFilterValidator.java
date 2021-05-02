package acme.entities.validators;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import acme.entities.spamfilter.SpamThreshold;
import acme.entities.spamfilter.SpamWord;

@Component
public class SpamFilterValidator {
	SpamThreshold threshold;
	Set<SpamWord> words;

	public SpamFilterValidator(final SpamThreshold threshold, final Set<SpamWord> words) {
		this.threshold = threshold;
		this.words = words;
	}

	public void validate(String text, final Errors errors) {
		final String upperText = text.toLowerCase();

		final Double spamCount = this.words.stream().map(censor -> {
			final Matcher m = Pattern.compile(censor.getText()).matcher(upperText);
			double count = 0;
			while (m.find())
				count++;
			return count;
		}).reduce(0.0, (x, y) -> x + y);

		final Double threshold = (spamCount / text.split(" ").length) * 100;

		if (this.threshold.getValue() < threshold) {
			errors.reject("acme.validation.spam");
		}
	}
}
