package acme.testing.administrator.spamThreshold;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamThresholdShowTest extends AcmePlannerTest {
	/*
	 * Principal: Administrator
	 * Entity: SpamThreshold
	 * Action: show (positive)
	 * Cases: We test if the value of the spam threshold registered in the system is shown
	 * correctly to an administrator principal. 
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamThreshold/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void showPositive(final String value) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam threshold");

		super.checkInputBoxHasValue("value", value);
		
		super.signOut();
	}
	
	/*
	 * Principal: Administrator
	 * Entity: SpamThreshold
	 * Action: show (negative)
	 * Cases: We test whether any principal (authenticated or manager) that is not an administrator
	 * doesn't have access to the spam threshold value registered in the system.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamThreshold/show-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void showNegative(final String user, final String password) {
		super.signIn(user, password);
		super.navigate("/administrator/spam-threshold/show", "");

		super.checkPanicExists();
		
		super.signOut();
	}
}
