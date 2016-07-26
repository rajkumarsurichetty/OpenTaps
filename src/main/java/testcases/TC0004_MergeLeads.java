package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import wrappers.OpentapsWrappers;

public class TC0004_MergeLeads extends OpentapsWrappers{
	@Test(dataProvider="fetchData")
	public void login(String userName, String passWord,String uname, 
			String leadID1,String leadID2,String expectedMessage) {

		new LoginPage()
		.enterUserName(userName)
		.enterPassword(passWord)
		.clickLogin()
		.verifyUserName(uname)
		.clickCRMSFA()
		.clickLead()
		.clickMergeLeads()
		.clickLookupFromLead()
		.enterLeadId(leadID1)
		.clickFindLeads()
		.clickFirstLead()
		.clickLookupToLead()
		.enterLeadId(leadID2)
		.clickFindLeads()
		.clickFirstLead()
		.clickMerge()
		.acceptMergeAlert()
		.clickFindLead()
		.enterLeadID(leadID1)
		.clickFindByLead()
		.verifyDisplayMessage(expectedMessage);
		
	}
	@BeforeClass
	public void beforeClass() {
		dataSheetName="TC004_MergeLeads";
		browserName="chrome";
		testCaseName="Merge Lead";
		testDescription="Merge Lead to Opentaps using POM";
	}

}
