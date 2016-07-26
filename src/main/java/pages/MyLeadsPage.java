package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class MyLeadsPage extends OpentapsWrappers {

	public MyLeadsPage() {
		if (!verifyTitle("My Leads | opentaps CRM"))
			Reporter.reportStep("This is not MY LEAD Page", "FAIL");
	}

	public FindLeadPage clickFindLead() {
		clickByLink(prop.getProperty("LeadHome.FindLead.link"));
		return new FindLeadPage();
	}
	
	public MergeLeads clickMergeLeads() {
		clickByLink(prop.getProperty("LeadsHome.MergeLeads.link"));
		return new MergeLeads();
	}
	public CreateLeadPage clickCreateLead() {
		clickByXpath(prop.getProperty("LeadHome.FindLead.xpath"));
		return new CreateLeadPage();
	}

}
