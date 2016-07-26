package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class MergeLookUp extends OpentapsWrappers{

	public MergeLookUp()
	{
		if(!verifyTitle("Find Leads"))
			Reporter.reportStep("This is not Merge - Find Leads Page", "FAIL");
	}	
		
	public MergeLookUp enterLeadId(String data) {
		enterByName(prop.getProperty("LeadsLookUp.LeadId.Name"), data);
		return this;
	}
	
	public MergeLookUp clickFindLeads(){
		clickByXpath(prop.getProperty("LeadsLookUp.FindLeadButton.Xpath"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}

	public MergeLeads clickFirstLead(){
		clickByXpathWithOutSnap(prop.getProperty("LeadsLookUp.FirstLead.Xpath"));
		switchToParentWindow();
		return new MergeLeads();
	}

}
