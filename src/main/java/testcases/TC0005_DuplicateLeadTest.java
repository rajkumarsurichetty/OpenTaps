package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import wrappers.OpentapsWrappers;

public class TC0005_DuplicateLeadTest extends OpentapsWrappers{
	@Test(dataProvider="fetchData")
	public void login(String userName, String passWord,String uname, 
			String emailId) {

		new LoginPage()
		.enterUserName(userName)
		.enterPassword(passWord)
		.clickLogin()
		.verifyUserName(uname)
		.clickCRMSFA()
		.clickLead()
		.clickFindLead()
		.clickEmailTab()
		.enterEmail(emailId)
		.clickFindByLead()
		.verifyLeadResultsRowCount()
		.clickFirstResultingLead()
		.clickDuplicateLead()
		.clickCreateLead()
		.clickFindLead()
		.clickEmailTab()
		.enterEmail(emailId)
		.clickFindByLead()
		.verifyLeadResultsRowCount();
		
		
		
	}
	@BeforeClass
	public void beforeClass() {
		dataSheetName="TC0005_DuplicateLeadTest";
		browserName="chrome";
		testCaseName="Edit Lead";
		testDescription="Edit Lead to Opentaps using POM";
	}

}
