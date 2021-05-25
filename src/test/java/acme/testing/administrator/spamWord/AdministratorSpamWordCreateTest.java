package acme.testing.administrator.spamWord;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordCreateTest extends AcmePlannerTest {
	/*
	 * Principal: Administrator
	 * Entity: SpamWord
	 * Action: create (positive)
	 * Cases: We test whether an administrator principal is able to create new words
	 * and register them as spam words in the system.
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
	 * Principal: Administrator
	 * Entity: SpamWord
	 * Action: create (negative)
	 * Cases: We test whether an administrator principal is unable to register
	 * empty words or words that are already registered in the system.
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
