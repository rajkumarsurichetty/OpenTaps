package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class CRMHomePage extends OpentapsWrappers {

	public CRMHomePage() {
		if (!verifyTitle("My Home | opentaps CRM"))
			Reporter.reportStep("This is not MY Home CRM Page", "FAIL");
	}

	public MyLeadsPage clickLead() {
		clickByLink(prop.getProperty("CRMHome.Lead.link"));
		return new MyLeadsPage();
	}

	public CreateLeadPage clickCreateLead() {
		clickByLink(prop.getProperty("CRMHome.createLead.link"));
		return new CreateLeadPage();
	}

}
