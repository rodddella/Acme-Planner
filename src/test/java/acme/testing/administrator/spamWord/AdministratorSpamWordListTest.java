package acme.testing.administrator.spamWord;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordListTest extends AcmePlannerTest {
	/*
	 * Positive administrator spam word list (customization parameters)
	 * 
	 * This test will check that all the initial registered words are correctly
	 * shown in the list
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamWord/list-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listPositive(final int recordIndex, final String text) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam words");
		
		super.checkColumnHasValue(recordIndex, 0, text);
		
		super.signOut();
	}
}
