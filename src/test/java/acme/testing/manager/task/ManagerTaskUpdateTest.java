
package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskUpdateTest extends AcmePlannerTest {

	/*
	 * Positive manager task create test, we test various valid attributes and we
	 * check the task is successfully created
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updatePositive(final int recordIndex, final String title, final String description,
			final String workload, final String link, final String startPeriod, final String endPeriod,
			final String visibility) {
		super.signIn("manager2", "manager2");
		super.clickOnMenu("Manager", "Task list");
		super.clickOnListingRecord(recordIndex);

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startPeriod", startPeriod);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("endPeriod", endPeriod);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("visibility", visibility);

		super.clickOnSubmitButton("Update task");

		super.clickOnMenu("Manager", "Task list");

		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, workload);
		super.checkColumnHasValue(recordIndex, 2, link);
		super.checkColumnHasValue(recordIndex, 3, startPeriod);
		super.checkColumnHasValue(recordIndex, 4, endPeriod);
		super.checkColumnHasValue(recordIndex, 5, visibility);

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
	 * Negative manager create task test. We check that empty validation works, in
	 * addition to date and time constraints: start > end start = end end - start <
	 * workload
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegative(final int recordIndex, final String title, final String description,
			final String workload, final String link, final String startPeriod, final String endPeriod,
			final String visibility) {
		super.signIn("manager2", "manager2");
		super.clickOnMenu("Manager", "Task list");

		super.clickOnListingRecord(recordIndex);

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startPeriod", startPeriod);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("endPeriod", endPeriod);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("link", link);
		if (visibility != null) {
			super.fillInputBoxIn("visibility", visibility);
		}

		super.clickOnSubmitButton("Update task");

		super.checkErrorsExist();
	}
}
