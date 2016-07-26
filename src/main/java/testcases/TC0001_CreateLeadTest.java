package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import wrappers.OpentapsWrappers;

public class TC0001_CreateLeadTest extends OpentapsWrappers{
	@Test(dataProvider="fetchData")
	public void login(String userName, String passWord,String uname, 
			String companyName,String firstName,String lastName,
			String phoneNumber,String email) {

		new LoginPage()
		.enterUserName(userName)
		.enterPassword(passWord)
		.clickLogin()
		.verifyUserName(uname)
		.clickCRMSFA()
		.clickLead()
		.clickCreateLead()
		.enterCompanyName(companyName)
		.enterFirstName(firstName)
		.enterLastName(lastName)
		.enterPhoneNo(phoneNumber)
		.enterEmailId(email)
		.clickCreateLead();
		
	}
	@BeforeClass
	public void beforeClass() {
		dataSheetName="TC0001_CreateLeadTest";
		browserName="firefox";
		testCaseName="Create Lead";
		testDescription="Create Lead to Opentaps using POM";
	}

}
