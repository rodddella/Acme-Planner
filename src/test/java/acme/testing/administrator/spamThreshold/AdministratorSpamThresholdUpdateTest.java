package acme.testing.administrator.spamThreshold;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamThresholdUpdateTest extends AcmePlannerTest {
	/*
	 * Principal: Administrator
	 * Entity: SpamThreshold
	 * Action: update (positive)
	 * Cases: We test whether an administrator principal can change the value of the
	 * spam threshold with values between 0.0 and 100.0 successfully.
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
	 * Principal: Administrator
	 * Entity: SpamThreshold
	 * Action: update (negative)
	 * Cases: We test whether an administrator principal can't change the value of the
	 * spam threshold when it is out-of-range (lower than 0.0 or greater than 100.0)
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
