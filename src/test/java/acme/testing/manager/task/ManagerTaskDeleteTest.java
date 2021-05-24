
package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskDeleteTest extends AcmePlannerTest {
	/*
	 * Positive manager task delete (customization parameters)
	 * 
	 * This test will check that an manager is able to delete their tasks
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deletePositive(final int id) {
		super.signIn("manager2", "manager2");

		super.navigate("/managers/task/delete", String.format("id=%d", id));

		super.navigate("/managers/task/show", String.format("id=%d", id));
		super.checkPanicExists();

		super.signOut();
	}

	/*
	 * Negative manager task delete (customization parameters)
	 * 
	 * This test will check the manager can only delete valid tasks
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/delete-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deleteNegative(final int id) {
		super.signIn("manager2", "manager2");

		super.navigate("/managers/task/delete", String.format("id=%d", id));
		super.checkPanicExists();

		super.signOut();
	}
}
