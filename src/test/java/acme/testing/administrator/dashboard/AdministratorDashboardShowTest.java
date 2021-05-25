
package acme.testing.administrator.dashboard;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import acme.testing.AcmePlannerTest;

public class AdministratorDashboardShowTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/dashboard/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void showPositive(final int recordIndex, final Integer totalNumberOfPublicTasks, final Integer totalNumberOfPrivateTasks, final Integer totalNumberOfFinishedTasks, final Integer totalNumberOfNonFinishedTasks,
		final String averageNumberOfTaskExecutionPeriods, final String deviationNumberOfTaskExecutionPeriods, final String minimumNumberOfTaskExecutionPeriods, final String maximumNumberOfTaskExecutionPeriods, final String averageNumberOfTaskWorkloads,
		final String deviationNumberOfTaskWorkloads, final String minimumNumberOfTaskWorkloads, final String maximumNumberOfTaskWorkloads) {

		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Dashboard");

		final List<WebElement> attributesDashborad = super.locateMany(By.tagName("td"));

		Assertions.assertEquals(Integer.valueOf(attributesDashborad.get(0).getText()), totalNumberOfPublicTasks);
		Assertions.assertEquals(Integer.valueOf(attributesDashborad.get(1).getText()), totalNumberOfPrivateTasks);
		Assertions.assertEquals(Integer.valueOf(attributesDashborad.get(2).getText()), totalNumberOfFinishedTasks);
		Assertions.assertEquals(Integer.valueOf(attributesDashborad.get(3).getText()), totalNumberOfNonFinishedTasks);
		Assertions.assertEquals(attributesDashborad.get(4).getText(), averageNumberOfTaskExecutionPeriods);
		Assertions.assertEquals(attributesDashborad.get(5).getText(), deviationNumberOfTaskExecutionPeriods);
		Assertions.assertEquals(attributesDashborad.get(6).getText(), minimumNumberOfTaskExecutionPeriods);
		Assertions.assertEquals(attributesDashborad.get(7).getText(), maximumNumberOfTaskExecutionPeriods);
		Assertions.assertEquals(attributesDashborad.get(8).getText(), averageNumberOfTaskWorkloads);
		Assertions.assertEquals(attributesDashborad.get(9).getText(), deviationNumberOfTaskWorkloads);
		Assertions.assertEquals(attributesDashborad.get(10).getText(), minimumNumberOfTaskWorkloads);
		Assertions.assertEquals(attributesDashborad.get(11).getText(), maximumNumberOfTaskWorkloads);

	}
}
