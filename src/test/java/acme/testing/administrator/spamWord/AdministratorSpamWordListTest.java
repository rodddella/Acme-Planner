package acme.testing.administrator.spamWord;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordListTest extends AcmePlannerTest {
	/*
	 * Principal: Administrator
	 * Entity: SpamWord
	 * Action: list (positive)
	 * Cases: We test whether every spam word that is initially registered in the system
	 * is correctly shown in the list.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamWord/list-show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listPositive(final int recordIndex, final String text) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam words");
		
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("text", text);
		
		super.signOut();
	}
}
