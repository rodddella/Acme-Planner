package acme.testing.authenticated.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedTaskListTest extends AcmePlannerTest {
	/*
	 * Positive authenticated principal list tasks
	 * 
	 * The seven tasks of the list will be checked for correct values
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/task/list-show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listPositive(final int recordIndex, final String title, final String description, final String workload,
			final String link, final String start, final String end, final String visibility) {		
		super.signIn("authenticated", "authenticated");
		super.clickOnMenu("Authenticated", "Task list");

		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, workload);
		super.checkColumnHasValue(recordIndex, 2, link);
		super.checkColumnHasValue(recordIndex, 3, start);
		super.checkColumnHasValue(recordIndex, 4, end);
		super.checkColumnHasValue(recordIndex, 5, visibility);
	}
}
