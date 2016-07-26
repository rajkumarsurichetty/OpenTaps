package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class DuplicateLeadPage extends OpentapsWrappers {
	
	public  DuplicateLeadPage() {
		if (!verifyTitle("Duplicate Lead | opentaps CRM"))
			Reporter.reportStep("This is not Duplicate Lead opentaps CRM Page", "FAIL");
	}
	
	public ViewLead clickCreateLead() {
		clickByXpath(prop.getProperty("Duplicate.createLead.xpath"));
		return new ViewLead();
	}
}

	
	
