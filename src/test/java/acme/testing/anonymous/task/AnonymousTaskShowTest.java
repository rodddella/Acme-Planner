package acme.testing.anonymous.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousTaskShowTest extends AcmePlannerTest {

	/*
	 * Positive show-task-details test
	 * 
	 * The details of the first five Task will be shown and checked if their values
	 * are correct
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/task/list-show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void showPositive(final int recordIndex, final String title, final String description,
			final String workload, final String link, final String start, final String end, final String visibility) {

		super.clickOnMenu("Anonymous", "Task list");

		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("startPeriod", start);
		super.checkInputBoxHasValue("endPeriod", end);
		super.checkInputBoxHasValue("visibility", visibility);
	}
}
