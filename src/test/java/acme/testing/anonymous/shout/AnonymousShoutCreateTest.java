
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
	public void createPositive(final int recordIndex, final String author, final String text, final String info) {

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
	5.Shout with text attribute more than 100 characters
	6.Shout with author attribute with more than 10% of spam
	7.Shout with text attribute with more than 10% of spam
	8.Shout with text attribute with more than 10% of spam
	9.Shout with author attribute with more than 10% of spam
	10.Shout with text attribute with 10% of spam
	11.Shout with info attribute with spam
	12.Shout with info attribute with more than 255 characters
	13.Shout with info attribute with spam and 254 characters
	14.Shout with all attributes empty
	
	*/
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex, final String author, final String text, final String info) {

		super.clickOnMenu("Anonymous", "Shout!");

		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();
	}
}
