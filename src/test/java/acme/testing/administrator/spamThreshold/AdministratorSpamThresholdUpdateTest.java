package acme.testing.administrator.spamThreshold;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamThresholdUpdateTest extends AcmePlannerTest {
	/*
	 * Positive administrator spam threshold update (customization parameters)
	 * 
	 * This test will check that the spam threshold value can be successfully changed
	 * when the entered value is correct (between 0 and 100)
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamThreshold/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updatePositive(final String value) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam threshold");

		super.fillInputBoxIn("value", value);
		super.clickOnSubmitButton("Update");
		
		super.clickOnMenu("Administrator", "Spam threshold");
		super.checkInputBoxHasValue("value", value);
		
		super.signOut();
	}
	
	/*
	 * Negative administrator spam threshold update (customization parameters)
	 * 
	 * This test will check that the spam threshold value can NOT be successfully changed
	 * when the entered value is incorrect (lower than 0 or greater than 100)
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamThreshold/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updateNegative(final String value) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam threshold");

		super.fillInputBoxIn("value", value);
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist("value");
		
		super.signOut();
	}
}
