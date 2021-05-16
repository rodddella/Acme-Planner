
package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class ManagerTaskCreateTest extends AcmePlannerTest {

	/*
	 * We test various valid attributes
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createTaskPositive(final int recordIndex, final String title, final String description, final String workload, final String link, final String startPeriod, final String endPeriod, final String visibility) {
		super.signIn("manager2", "manager2");
		super.clickOnMenu("Manager", "Create task");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startPeriod", startPeriod);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("endPeriod", endPeriod);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("visibility", visibility);

		super.clickOnSubmitButton("Create task");

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
	 * We check that empty validation works
	 */
	@Test
	@Order(20)
	public void createEmptyTaskNegative() {
		super.signIn("manager2", "manager2");
		super.clickOnMenu("Manager", "Create task");
		super.clickOnSubmitButton("Create task");

		final String xpath = String.format("//div[@class='form-group'][textarea[@id='%s'] and div[@class='text-danger']]", "description");
		final By description = By.xpath(xpath);

		super.checkErrorsExist("title");
		super.checkErrorsExist("startPeriod");
		super.checkErrorsExist("endPeriod");
		super.checkErrorsExist("workload");
		super.checkExists(description);

	}

	/*
	 * We check all the possible invalid variations of periods:
	 * start > end
	 * start = end
	 * end - start < workload
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/dates-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void invalidPeriodNegative(final String startPeriod, final String endPeriod,final String workload) {
		super.signIn("manager2", "manager2");
		super.clickOnMenu("Manager", "Create task");

		super.fillInputBoxIn("title", "title");
		super.fillInputBoxIn("description", "description");
		super.fillInputBoxIn("link", "http://example.org");
		super.fillInputBoxIn("visibility", "PUBLIC");

		super.fillInputBoxIn("startPeriod", startPeriod);
		super.fillInputBoxIn("endPeriod", endPeriod);
		super.fillInputBoxIn("workload", workload);

		super.clickOnSubmitButton("Create task");
		
		super.checkErrorsExist();

		

	}
}
