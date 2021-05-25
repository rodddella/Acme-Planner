
package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskShowTest extends AcmePlannerTest {

	/*
	 * Positive Manager Task Show test
	 * 
	 * The details of the task will be shown and check if their values are correct
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/list-show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void showPositive(final int recordIndex,final String user, final String password, final String title, final String description, final String workload,
			final String link, final String startPeriod, final String endPeriod, final String visibility) {
		super.signIn(user, password);
		super.clickOnMenu("Manager", "Task list");
		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("startPeriod", startPeriod);
		super.checkInputBoxHasValue("endPeriod", endPeriod);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("visibility", visibility);


	}

	/*
	 * Negative Manager Task Show test
	 * 
	 * Unauthorized and invalid tasks will be shown, panic is expected to happen.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/show-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegative(final int id, final String user, final String password) {
		super.signIn(user, password);
		super.navigate("/managers/task/show", String.format("id=%d", id));

		super.checkPanicExists();
	}
}
