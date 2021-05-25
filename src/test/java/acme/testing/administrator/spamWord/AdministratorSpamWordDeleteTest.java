package acme.testing.administrator.spamWord;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordDeleteTest extends AcmePlannerTest {
	/*
	 * Principal: Administrator
	 * Entity: SpamWord
	 * Action: delete (positive)
	 * Cases: We test whether an administrator principal is able to delete every word
	 * that is initially registered within the sample population.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamWord/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deletePositive(final int id) {
		super.signIn("administrator", "administrator");
		
		super.navigate("/administrator/spam-word/show", String.format("id=%d", id));
		
		super.clickOnSubmitButton("Delete");
		
		super.navigate("/administrator/spam-word/show", String.format("id=%d", id));

		super.checkPanicExists();
		
		super.signOut();
	}
	
	/*
	 * Principal: Administrator
	 * Entity: SpamWord
	 * Action: delete (negative)
	 * Cases: We test whether every principal that is not an administrator (manager and authenticated)
	 * isn't able to delete any registered spam word in the system.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamWord/delete-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deleteNegative(final int id, final String user, final String password) {
		super.signIn(user, password);
		super.navigate("/administrator/spam-word/show", String.format("id=%d", id));
		
		super.checkPanicExists();
		
		super.signOut();
	}
}
