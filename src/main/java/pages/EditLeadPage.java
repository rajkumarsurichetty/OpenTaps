package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class EditLeadPage extends OpentapsWrappers {
	
	public  EditLeadPage() {
		if (!verifyTitle("opentaps CRM"))
			Reporter.reportStep("This is not Open Taps CRM Page", "FAIL");
	}
	
	public EditLeadPage selectDataSource(String indexNumber ){
		selectIndexById(prop.getProperty("EditLead.DataSource.id"), Integer.parseInt(indexNumber));
		return this;
		
	}
	public EditLeadPage addDataSource( ){
		clickByXpath(prop.getProperty("EditLead.DataSourceADD.xpath"));
		return this;
		
	}
	public EditLeadPage selectMarketing(String indexNumber ){
		selectIndexById(prop.getProperty("EditLead.Marketing.id"), Integer.parseInt(indexNumber));
		return this;
		
	}
	public EditLeadPage addMarketing( ){
		clickByXpath(prop.getProperty("EditLead.MarketingADD.xpath"));
		return this;
		
	}
	public ViewLead clickUpdate() {
		clickByXpath(prop.getProperty("EditLead.UpdateLead.xpath"));
		return new ViewLead();
	}
}

	
	
