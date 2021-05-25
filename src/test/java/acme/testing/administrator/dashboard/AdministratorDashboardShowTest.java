package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorDashboardShowTest extends AcmePlannerTest {
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/dashboard/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void showPositive(final String totalNumberOfPublicTasks, final String totalNumberOfPrivateTasks, final String totalNumberOfFinishedTasks, final String totalNumberOfNonFinishedTasks,
		final String averageNumberOfTaskExecutionPeriods, final String deviationNumberOfTaskExecutionPeriods, final String minimumNumberOfTaskExecutionPeriods, final String maximumNumberOfTaskExecutionPeriods, final String averageNumberOfTaskWorkloads,
		final String deviationNumberOfTaskWorkloads, final String minimumNumberOfTaskWorkloads, final String maximumNumberOfTaskWorkloads) {

		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Dashboard");

		super.checkInputBoxHasValue("totalNumberOfPublicTasks", totalNumberOfPublicTasks);
		super.checkInputBoxHasValue("totalNumberOfPrivateTasks", totalNumberOfPrivateTasks);
		super.checkInputBoxHasValue("totalNumberOfFinishedTasks", totalNumberOfFinishedTasks);
		super.checkInputBoxHasValue("totalNumberOfNonFinishedTasks", totalNumberOfNonFinishedTasks);
		super.checkInputBoxHasValue("averageNumberOfTaskExecutionPeriods", averageNumberOfTaskExecutionPeriods);
		super.checkInputBoxHasValue("deviationNumberOfTaskExecutionPeriods", deviationNumberOfTaskExecutionPeriods);
		super.checkInputBoxHasValue("minimumNumberOfTaskExecutionPeriods", minimumNumberOfTaskExecutionPeriods);
		super.checkInputBoxHasValue("maximumNumberOfTaskExecutionPeriods", maximumNumberOfTaskExecutionPeriods);
		super.checkInputBoxHasValue("averageNumberOfTaskWorkloads", averageNumberOfTaskWorkloads);
		super.checkInputBoxHasValue("deviationNumberOfTaskWorkloads", deviationNumberOfTaskWorkloads);
		super.checkInputBoxHasValue("minimumNumberOfTaskWorkloads", minimumNumberOfTaskWorkloads);
		super.checkInputBoxHasValue("maximumNumberOfTaskWorkloads", maximumNumberOfTaskWorkloads);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/dashboard/show-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void showNegative(final String user, final String password, final String totalNumberOfPublicTasks, final String totalNumberOfPrivateTasks, final String totalNumberOfFinishedTasks, final String totalNumberOfNonFinishedTasks,
		final String averageNumberOfTaskExecutionPeriods, final String deviationNumberOfTaskExecutionPeriods, final String minimumNumberOfTaskExecutionPeriods, final String maximumNumberOfTaskExecutionPeriods, final String averageNumberOfTaskWorkloads,
		final String deviationNumberOfTaskWorkloads, final String minimumNumberOfTaskWorkloads, final String maximumNumberOfTaskWorkloads) {

		super.signIn(user, password);
		super.navigate("/administrator/dashboard/show", null);

		super.checkPanicExists();
		
		super.signOut();
	}
}
