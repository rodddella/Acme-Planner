
package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest {

	/*
	Positive shout-creation test
	
	Different valid shouts will be 
	created with different characteristics in order to have various positive cases.
	*/
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createShoutPositive(final int recordIndex, final String author, final String text, final String info) {

		super.clickOnMenu("Anonymous", "Shout!");

		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmitButton("Shout!");

		super.clickOnMenu("Anonymous", "Shout list");

		super.checkColumnHasValue(0, 1, author);
		super.checkColumnHasValue(0, 2, info);
		super.checkColumnHasValue(0, 3, text);
	}
	
	/*
	Negative shout-creation test
	
	Different invalid shouts will be 
	created with different characteristics in order to have various negative cases.
	
	There will be five test cases. These are as follows:
	0.Shout with blank author attribute.
	1.Shout with author attribute less than 5 characters
	2.Shout with author attribute with more than 25 characters
	3.Shout with blank text attribute
	4.Shout with info attribute with invalid url
	*/
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createShoutNegative(final int recordIndex, final String author, final String text, final String info) {

		super.clickOnMenu("Anonymous", "Shout!");

		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();
	}
}
