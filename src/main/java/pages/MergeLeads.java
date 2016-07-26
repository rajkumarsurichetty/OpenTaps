package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class MergeLeads extends OpentapsWrappers{

		// TODO Auto-generated method stub
		
		public MergeLeads()
		{
			if(!verifyTitle("Merge Leads | opentaps CRM"))
				Reporter.reportStep("This is not Merge Leads Page", "FAIL");
		}	
		
	
		public MergeLookUp clickLookupFromLead() {
			clickByXpath(prop.getProperty("MergeLeads.MergeLookUp1.Xpath"));
			switchToChildWindows();
			return new MergeLookUp();			
		}
		
		public MergeLookUp clickLookupToLead() {
			clickByXpath(prop.getProperty("MergeLeads.MergeLookUp2.Xpath"));
			switchToChildWindows();
			return new MergeLookUp();			
		}
		
		public MergeLeads clickMerge(){
			clickByXpath(prop.getProperty("MergeLeads.Merge.Xpath"));
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return this;
		}
		
		public ViewLead acceptMergeAlert(){
			acceptAlert();
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new ViewLead();
		}
		
		
		
		
	}

