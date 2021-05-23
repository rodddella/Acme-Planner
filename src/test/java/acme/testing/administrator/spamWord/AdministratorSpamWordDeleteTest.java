package acme.testing.administrator.spamWord;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordDeleteTest extends AcmePlannerTest {
	/*
	 * Positive administrator spam word delete (customization parameters)
	 * 
	 * This test will check that an administrator is able to delete every word
	 * that is initially registered as an spam word
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamWord/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deletePositive(final int id) {
		super.signIn("administrator", "administrator");
		
		super.navigate("/administrator/spam-word/delete", String.format("id=%d", id));
		
		super.navigate("/administrator/spam-word/show", String.format("id=%d", id));
		super.checkPanicExists();
		
		super.signOut();
	}
	
	/*
	 * Negative administrator spam word delete (customization parameters)
	 * 
	 * This test will check nobody but the administrator principals are able
	 * to delete the registered spam words
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamWord/delete-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deleteNegative(final int id) {
		super.signIn("manager1", "manager1");
		
		super.navigate("/administrator/spam-word/delete", String.format("id=%d", id));
		super.checkPanicExists();
		
		super.signOut();
	}
}
