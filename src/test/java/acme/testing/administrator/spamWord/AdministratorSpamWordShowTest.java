package acme.testing.administrator.spamWord;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordShowTest extends AcmePlannerTest {
	/*
	 * Positive administrator spam word list (customization parameters)
	 * 
	 * This test will check that all the initial registered words are correctly
	 * shown in the list
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamWord/list-show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void showPositive(final int recordIndex, final String text) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam words");
		
		super.checkColumnHasValue(recordIndex, 0, text);
		
		super.signOut();
	}
	
	/*
	 * Positive administrator spam word list (customization parameters)
	 * 
	 * This test will check that spam threshold value is not shown to
	 * anybody that is not an administrator principal
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamWord/show-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void showNegative(final int id) {
		super.navigate("/administrator/spamWord/show", String.format("id=%d", id));
		
		super.checkPanicExists();
	}
	
}
