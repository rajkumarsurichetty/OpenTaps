package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class ViewLead extends OpentapsWrappers {

	public ViewLead() {
		if (!verifyTitle("View Lead | opentaps CRM"))
			Reporter.reportStep("This is not View Lead  Page", "FAIL");
	}

	public ViewLead verifyFirstName(String firstName) {
		verifyTextByID(prop.getProperty("ViewLead.FirstName.Id"), firstName);
		return this;
	}

	public EditLeadPage clickEdit() {
		clickByLink(prop.getProperty("ViewLead.Edit.link"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new EditLeadPage();

	}

	public MyLeadsPage clickDelete() {
		clickByLink(prop.getProperty("ViewLead.Delete.link"));
		return new MyLeadsPage();
	}
	public DuplicateLeadPage clickDuplicateLead() {
		clickByLink(prop.getProperty("ViewLead.DuplicateLead.link"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new DuplicateLeadPage();
	}

	public ViewLead verifyMarketing(String data) {
		verifyTextByID(prop.getProperty("ViewLead.Marketing.id"), data);
		return this;
	}

	public ViewLead verifyDataSource(String data) {
		verifyTextByID(prop.getProperty("ViewLead.Datasource.id"), data);
		return this;
	}
	public FindLeadPage clickFindLead() {
		clickByXpath(prop.getProperty("ViewLead.FindLead.xpath"));
		return new FindLeadPage();
	}
	
}
