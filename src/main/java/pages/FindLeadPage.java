package pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class FindLeadPage extends OpentapsWrappers {

	public FindLeadPage() {
		if (!verifyTitle("Find Leads | opentaps CRM"))
			Reporter.reportStep("This is not FIND LEAD Page", "FAIL");
	}


	public FindLeadPage enterFirstName(String firstName) {
		enterByxpath(prop.getProperty("FindLead.FirstName.xpath"),firstName);
		return this;
	}

	public FindLeadPage clickFindByLead() {
		clickByXpath(prop.getProperty("FindLead.FindbyLead.xpath"));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
	
	public ViewLead clickFirstResultingLead() {
		clickByXpath(prop.getProperty("FindLead.LeadID.xpath"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ViewLead();
	}
	
	public FindLeadPage enterLeadID(String leadID) {
		enterByName(prop.getProperty("FindLead.Leadid.name"),leadID);
		return this;
	}
	
	public FindLeadPage verifyDisplayMessage(String expectedText ){
		verifyTextByClassName(prop.getProperty("FindLead.SearchResult.class"), expectedText);
		return this;
	}
	
	public FindLeadPage clickPhoneTab() {
		clickByXpath(prop.getProperty("FindLead.Phone.xpath"));
		return this;
	}
	
	public FindLeadPage enterPhoneNumber(String phoneNumber) {
		enterByName(prop.getProperty("FindLead.PoneNumber.name"),phoneNumber);
		return this;
	}
	public FindLeadPage clickEmailTab() {
		clickByXpath(prop.getProperty("FindLead.email.xpath"));
		return this;
	}
	public FindLeadPage enterEmail(String emailId) {
		enterByName(prop.getProperty("FindLead.emailid.name"),emailId);
		return this;
	}
	public FindLeadPage verifyLeadResultsRowCount(){
		List<WebElement> rc=verifyTableByXpath(prop.getProperty("FindLead.Tablepath.xpath"));
		System.out.println("Row Count   "+rc.size());
		return this;
	}
}
