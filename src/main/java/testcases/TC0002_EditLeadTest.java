package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import wrappers.OpentapsWrappers;

public class TC0002_EditLeadTest extends OpentapsWrappers{
	@Test(dataProvider="fetchData")
	public void login(String userName, String passWord,String uname, 
			String firstName,String market,String source) {

		new LoginPage()
		.enterUserName(userName)
		.enterPassword(passWord)
		.clickLogin()
		.verifyUserName(uname)
		.clickCRMSFA()
		.clickLead()
		.clickFindLead()
		.enterFirstName(firstName)
		.clickFindByLead()
		.clickFirstResultingLead()
		.clickEdit()
		.selectDataSource(source)
		.addDataSource()
		.selectMarketing(market)
		.addMarketing()
		.clickUpdate();
		
		
	}
	@BeforeClass
	public void beforeClass() {
		dataSheetName="TC0002_EditLeadTest";
		browserName="chrome";
		testCaseName="Edit Lead";
		testDescription="Edit Lead to Opentaps using POM";
	}

}
