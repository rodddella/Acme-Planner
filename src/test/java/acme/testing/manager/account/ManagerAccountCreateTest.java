package acme.testing.manager.account;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerAccountCreateTest extends AcmePlannerTest{

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/account/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String company, final String sector){
		
		super.signIn("authenticated", "authenticated");
		super.clickOnMenu("Account", "Become a manager");
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		
		super.clickOnSubmitButton("Register");
		
		super.clickOnMenu("Account", "Manager data");
		
		super.checkInputBoxHasValue("company", company);
		super.checkInputBoxHasValue("sector", sector);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/account/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex, final String company, final String sector){
		
		super.signIn("authenticated2", "authenticated2");
		super.clickOnMenu("Account", "Become a manager");
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		
		super.clickOnSubmitButton("Register");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
}
