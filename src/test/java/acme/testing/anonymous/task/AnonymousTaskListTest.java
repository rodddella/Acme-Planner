package acme.testing.anonymous.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousTaskListTest extends AcmePlannerTest {
	/*
	 * Positive anonymous principal list tasks
	 * 
	 * The first five tasks of the list will be checked for correct values
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/task/list-show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listPositive(final int recordIndex, final String title, final String description, final String workload,
			final String link, final String start, final String end, final String visibility) {
		super.clickOnMenu("Anonymous", "Task list");

		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, workload);
		super.checkColumnHasValue(recordIndex, 2, link);
		super.checkColumnHasValue(recordIndex, 3, start);
		super.checkColumnHasValue(recordIndex, 4, end);
		super.checkColumnHasValue(recordIndex, 5, visibility);
	}
}