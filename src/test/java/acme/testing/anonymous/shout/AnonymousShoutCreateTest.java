
package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest {

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

		super.checkColumnHasValue(recordIndex, 0, author);
		super.checkColumnHasValue(recordIndex, 1, text);
		super.checkColumnHasValue(recordIndex, 2, info);

		super.clickOnListingRecord(recordIndex);


		super.checkInputBoxHasValue("author", author);
		super.checkInputBoxHasValue("text", text);
		super.checkInputBoxHasValue("info", info);
	}
}
