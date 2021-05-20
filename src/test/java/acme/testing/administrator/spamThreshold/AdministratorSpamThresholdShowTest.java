package acme.testing.administrator.spamThreshold;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamThresholdShowTest extends AcmePlannerTest {
	/*
	 * Positive administrator spam threshold show (customization parameters)
	 * 
	 * This test will check that spam threshold value is correctly shown in the
	 * corresponding view for the administrator principal
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamThreshold/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void showPositive(final String value) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam threshold");

		super.checkInputBoxHasValue("value", value);
	}
}
