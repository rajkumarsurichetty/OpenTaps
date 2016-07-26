package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class CreateLeadPage extends OpentapsWrappers{

	public CreateLeadPage() {
		if(!verifyTitle("Create Lead | opentaps CRM"))
			Reporter.reportStep("This is not Create Lead Page", "FAIL");
	}	
	public CreateLeadPage enterCompanyName(String data){
		enterById(prop.getProperty("CreateLead.CompanyName.id"), data);
		return this;
	}

	public CreateLeadPage enterFirstName(String data){
		enterById(prop.getProperty("CreateLead.FirstName.id"), data);
		return this;
	}
	public CreateLeadPage enterLastName(String data){
		enterById(prop.getProperty("CreateLead.LastName.id"), data);
		return this;
	}
	public CreateLeadPage selectSource(int data){
		selectIndexById(prop.getProperty("CreateLead.selectSource.id"), data);
		return this;
	}
	public CreateLeadPage selectMarket(int data){
		selectIndexById(prop.getProperty("CreateLead.selectMarket.id"), data);
		return this;
	}

	public CreateLeadPage enterEmailId(String data){
		enterById(prop.getProperty("CreateLead.EmailId.id"), data);
		return this;
	}
	public CreateLeadPage enterPhoneNo(String data){
		enterById(prop.getProperty("CreateLead.enterPhoneNo.id"), data);
		return this;
	}
	
	

	public ViewLead clickCreateLead(){
		clickByClassName(prop.getProperty("CreateLead.CreateLEAD.name"));
		return new ViewLead();
	}















}
