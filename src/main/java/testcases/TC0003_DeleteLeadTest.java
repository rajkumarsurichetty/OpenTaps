package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import wrappers.OpentapsWrappers;

public class TC0003_DeleteLeadTest extends OpentapsWrappers{
	@Test(dataProvider="fetchData")
	public void login(String userName, String passWord,String uname, 
			String phoneNumber,String expectedText) {

		new LoginPage()
		.enterUserName(userName)
		.enterPassword(passWord)
		.clickLogin()
		.verifyUserName(uname)
		.clickCRMSFA()
		.clickLead()
		.clickFindLead()
		.clickPhoneTab()
		.enterPhoneNumber(phoneNumber)
		.clickFindByLead()
		.clickFirstResultingLead()
		.clickDelete()
		.clickFindLead()
		.clickPhoneTab()
		.enterPhoneNumber(phoneNumber)
		.clickFindByLead()
		.verifyDisplayMessage(expectedText);
		
		
	}
	@BeforeClass
	public void beforeClass() {
		dataSheetName="TC0003_DeleteLeadTest";
		browserName="firefox";
		testCaseName="Delete Lead";
		testDescription="Delete Lead to Opentaps using POM";
	}

}
