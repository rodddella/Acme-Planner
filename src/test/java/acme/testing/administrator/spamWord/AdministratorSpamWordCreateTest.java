package acme.testing.administrator.spamWord;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordCreateTest extends AcmePlannerTest {
	/*
	 * Positive administrator spam word create (customization parameters)
	 * 
	 * This test will check that an administrator principal can create
	 * new words for the spam filter
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamWord/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String text) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam words");
		
		super.clickOnReturnButton("Create");
		
		super.fillInputBoxIn("text", text);
		super.clickOnSubmitButton("Create");
		
		super.clickOnMenu("Administrator", "Spam words");
		super.checkColumnHasValue(recordIndex, 0, text);
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("text", text);
		
		super.signOut();
	}
	
	/*
	 * Negative administrator spam word create (customization parameters)
	 * 
	 * This test will check that an administrator principal can't create
	 * words that are already in the database, or register empty words
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamWord/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegative(final String text) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam words");
		
		super.clickOnReturnButton("Create");
		
		super.fillInputBoxIn("text", text);
		super.clickOnSubmitButton("Create");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
}
