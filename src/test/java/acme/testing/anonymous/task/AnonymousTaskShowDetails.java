package acme.testing.anonymous.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousTaskShowDetails extends AcmePlannerTest{
	
	/*
	Positive show-task-details test
	
	The details of the first five Task will be shown and checked if their values are correct
	*/
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/task/show-details-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void detailsPositive(final int recorIndex, final String title, final String description, final String Worload, final String Start, final String End, final String Visibility) {
		
		super.clickOnMenu("Anonymous", "Task list");
		
		super.clickOnListingRecord(recorIndex);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("workload", Worload);
		super.checkInputBoxHasValue("startPeriod", Start);
		super.checkInputBoxHasValue("endPeriod", End);
		super.checkInputBoxHasValue("visibility", Visibility);
	}
}
