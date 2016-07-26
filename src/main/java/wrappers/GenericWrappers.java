package wrappers;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Reporter;

public class GenericWrappers {

	protected static RemoteWebDriver driver;
	protected static Properties prop;
	public String sUrl,primaryWindowHandle,sHubUrl,sHubPort;

	public GenericWrappers() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./config.properties")));
			sHubUrl = prop.getProperty("HUB");
			sHubPort = prop.getProperty("PORT");
			sUrl = prop.getProperty("URL");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will launch only firefox and maximise the browser and set the
	 * wait for 30 seconds and load the url
	 * @author Babu - TestLeaf
	 * @param url - The url with http or https
	 * 
	 */
	public boolean invokeApp(String browser) {
		boolean bReturn = false;
		try {

			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setBrowserName(browser);
			dc.setPlatform(Platform.WINDOWS);
			if(browser.equalsIgnoreCase("chrome")){
				//System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				//driver = new ChromeDriver();
			}else
				driver = new FirefoxDriver();
			
			driver = new RemoteWebDriver(new URL("http://"+sHubUrl+":"+sHubPort+"/wd/hub"), dc);
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(sUrl);

			primaryWindowHandle = driver.getWindowHandle();		
			Reporter.reportStep("The browser:" + browser + " launched successfully", "PASS");
			bReturn = true;

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.reportStep("The browser:" + browser + " could not be launched", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will enter the value to the text field using id attribute to locate
	 * 
	 * @param idValue - id of the webelement
	 * @param data - The data to be sent to the webelement
	 * @author Babu - TestLeaf
	 * @throws IOException 
	 * @throws COSVisitorException 
	 */
	public boolean enterById(String idValue, String data) {
		boolean bReturn = false;
		try {
			driver.findElement(By.id(idValue)).clear();
			driver.findElement(By.id(idValue)).sendKeys(data);	
			Reporter.reportStep("The data: "+data+" entered successfully in field :"+idValue, "PASS");
			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The data: "+data+" could not be entered in the field :"+idValue, "FAIL");
		}
		return bReturn;
	}
	
	/**
	 * This method will enter the value to the text field using Name attribute to locate
	 * @param nameValue - name of the WebElement
	 * @param data -The data to be sent to the WebElement
	 * @author RajaKumar
	 * @throws NoSuchElementException
	 * @throws Exception
	 */
	public boolean enterByName(String nameValue, String data) {
		boolean bReturn=false;
		try {
			driver.findElement(By.name(nameValue)).clear();
			driver.findElement(By.name(nameValue)).sendKeys(data);
			bReturn=true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The data: "+data+" entered successfully in field :"+nameValue, "PASS");
						
		} catch (Exception e) {
			Reporter.reportStep("The data: "+data+" could not be entered in the field :"+nameValue, "FAIL");
			
		}
		return bReturn;
	}
	/**
	 * This method will enter the value to the text Filed using className Attribute to locate
	 * @param className - className of the webElement .
	 * @param data -the data to sent to the webElement
	 * @throws NoSuchElementException
	 * @throws Exception
	 * @author RajaKumar
	 */
	public boolean enterByclassName(String className, String data) {
		boolean bReturn=false;
		try {
			driver.findElement(By.className(className)).clear();
			driver.findElement(By.className(className)).sendKeys(data);
			Reporter.reportStep("The data: "+data+" entered successfully in field :"+className, "PASS");
			bReturn=true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The data: "+data+" could not be entered in the field :"+className, "FAIL");
			
		}catch (Exception e) {
			Reporter.reportStep("The data: "+data+" could not be entered in the field :"+className, "FAIL");
		}
		
		return bReturn;
	}
	/**
	 * This method will enter the value to the text Filed using xpath Attribute to locate
	 * @param xpath - xpath of the webElement .
	 * @param data -the data to sent to the webElement
	 * @throws NoSuchElementException
	 * @throws Exception
	 * @author RajaKumar
	 */
	public boolean enterByxpath(String xpath, String data) {
		boolean bReturn=false;
		try {
			driver.findElement(By.xpath(xpath)).clear();
			driver.findElement(By.xpath(xpath)).sendKeys(data);
			Reporter.reportStep("The data: "+data+" entered successfully in field :"+xpath, "PASS");
			bReturn=true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The data: "+data+" could not be entered in the field :"+xpath, "FAIL");
			bReturn=false;
		}catch (Exception e) {
			Reporter.reportStep("The data: "+data+" could not be entered in the field :"+xpath, "FAIL");
		}
		
		return bReturn;
	}
	/**
	 * This method will enter the value to the text Filed using CSS Selector Attribute to locate
	 * @param cssSelctor is the webElement 
	 * @param data to sent to the WebElement 
	 * @throws NoSuchElementException
	 * @throws Exception
	 * @author RajaKumar
	 * @return bReturn boolean
	 */
	public boolean enterByCSSSelector(String cssSelctor, String data) {
		boolean bReturn=false;
		try {
			driver.findElement(By.cssSelector(cssSelctor)).clear();
			driver.findElement(By.cssSelector(cssSelctor)).sendKeys(data);
			bReturn=true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The data: "+data+" could not be entered in the field :"+cssSelctor, "FAIL");
			
		}catch (Exception e) {
			Reporter.reportStep("The data: "+data+" could not be entered in the field :"+cssSelctor, "FAIL");
		}
		finally {
			//screenshot();
		}
		return bReturn;
	}
	/**
	 * This method will enter the value to the text Filed using tagName Attribute to locate
	 * @param tagName - tagName of the webElement .
	 * @param data -the data to sent to the webElement
	 * throws Exception
	 * @author RajaKumar
	 * @return bReturn boolean
	 */
	public boolean enterByTagName(String tagName, String data) {
		boolean bReturn=false;
		try {
			driver.findElement(By.tagName(tagName)).clear();
			driver.findElement(By.tagName(tagName)).sendKeys(data);
			bReturn=true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The data: "+data+" could not be entered in the field :"+tagName, "FAIL");
		}catch (Exception e) {
			Reporter.reportStep("The data: "+data+" could not be entered in the field :"+tagName, "FAIL");
		}
		finally {
			//screenshot();
		}
		return bReturn;
	}
	/**
	 * This method will verify the title of the browser 
	 * @param title - The expected title of the browser
	 * @author Babu - TestLeaf
	 */
	public boolean verifyTitle(String title){
		boolean bReturn = false;
		try{
			if (driver.getTitle().equalsIgnoreCase(title)){
				Reporter.reportStep("The title of the page matches with the value :"+title, "PASS");
				bReturn = true;
			}else
				Reporter.reportStep("The title of the page:"+driver.getTitle()+" did not match with the value :"+title, "SUCCESS");

		}catch (Exception e) {
			Reporter.reportStep("The title did not match", "FAIL");
		}

		return bReturn;
	}

	/**
	 * This method will verify the given text
	 * @param xpath - The locator of the object in xpath
	 * @param text  - The text to be verified
	 * @author Babu - TestLeaf
	 */
	public boolean verifyTextByXpath(String xpath, String text){
		boolean bReturn = false;
		String sText = driver.findElementByXPath(xpath).getText();
		if (driver.findElementByXPath(xpath).getText().trim().equalsIgnoreCase(text)){
			Reporter.reportStep("The text: "+sText+" matches with the value :"+text, "PASS");
			bReturn = true;
		}else{
			Reporter.reportStep("The text: "+sText+" did not match with the value :"+text, "FAIL");
		}


		return bReturn;
	}
	
	/**
	 * This method will verify the given text
	 * @param xpath - The locator of the object in xpath
	 * @param text  - The text to be verified
	 * @author Babu - TestLeaf
	 */
	public boolean verifyTextContainsByXpath(String xpath, String text){
		boolean bReturn = false;
		String sText = driver.findElementByXPath(xpath).getText();
		if (driver.findElementByXPath(xpath).getText().trim().contains(text)){
			Reporter.reportStep("The text: "+sText+" contains the value :"+text, "PASS");
			bReturn = true;
		}else{
			Reporter.reportStep("The text: "+sText+" did not contain the value :"+text, "FAIL");
		}


		return bReturn;
	}

	
	/**
	 * This method Will verifying the Text /value on web page using id attribute
	 * @param id -id is the webElement 
	 * @param expectedText -is user expecting text/value
	 * @return -bReturn
	 */
	public boolean verifyTextByID(String id, String expectedText) {
			boolean bReturn  = false;String actualText="";
		try {
			actualText = driver.findElement(By.id(id)).getText();
			if (actualText.equals(expectedText)){
				Reporter.reportStep("The text: "+actualText+" contains the value :"+expectedText, "PASS");
				bReturn  = true;
			}
			else {
				Reporter.reportStep("The text: "+actualText+" did not contain the value :"+expectedText, "FAIL");
				bReturn  = false;
			}
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The text: "+actualText+" did not contain the value :"+expectedText, "FAIL");
			bReturn  = false;
		}
		finally
		{
			
		}
		return bReturn;
		
	}

	/**
	 * This method Will verifying the Text /value on web page using name attribute
	 * @param name -name is the webElement 
	 * @param expectedText -is user expecting text/value
	 * @return -bReturn
	 */
	public boolean verifyTextByName(String name, String expectedText) {
		boolean bReturn  = false;String actualText="";
	try {
		actualText = driver.findElement(By.name(name)).getText();
		if (actualText.equals(expectedText)){
			Reporter.reportStep("The text: "+actualText+" contains the value :"+expectedText, "PASS");
			bReturn  = true;
		}
		else {
			Reporter.reportStep("The text: "+actualText+" did not contain the value :"+expectedText, "FAIL");
		}
	} catch (NoSuchElementException e) {
		Reporter.reportStep("The text: "+actualText+" did not contain the value :"+expectedText, "FAIL");
			}
	finally
	{
		
	}
	return bReturn;
	
}
	/**
	 * This method Will verifying the Text /value on web page using className attribute
	 * @param className -className is the webElement 
	 * @param expectedText -is user expecting text/value
	 * @return -bReturn
	 */
	public boolean verifyTextByClassName(String className, String expectedText) {
		boolean bReturn  = false;String actualText="";
	try {
		actualText = driver.findElement(By.className(className)).getText();
		if (actualText.equals(expectedText)){
			Reporter.reportStep("The text: "+actualText+" contains the value :"+expectedText, "PASS");
			bReturn  = true;
		}
		else {
			Reporter.reportStep("The text: "+actualText+" did not contain the value :"+expectedText, "FAIL");
		}
	} catch (NoSuchElementException e) {
		Reporter.reportStep("The text: "+actualText+" did not contain the value :"+expectedText, "FAIL");
	}
	finally
	{
		
	}
	return bReturn;
	
}
	/**
	 * This method Will verifying the Text /value on web page using tagName attribute
	 * @param tagName -tagName is the webElement 
	 * @param expectedText -is user expecting text/value
	 * @return -bReturn
	 */
	public boolean verifyTextByTagName(String tagName, String expectedText) {
		boolean bReturn  = false;String actualText="";
	try {
		actualText = driver.findElement(By.tagName(tagName)).getText();
		if (actualText.equals(expectedText)){
			Reporter.reportStep("The text: "+actualText+" contains the value :"+expectedText, "PASS");
			bReturn  = true;
		}
		else {
			Reporter.reportStep("The text: "+actualText+" did not contain the value :"+expectedText, "FAIL");
		}
	} catch (NoSuchElementException e) {
		Reporter.reportStep("The text: "+actualText+" did not contain the value :"+expectedText, "FAIL");
	}
	finally
	{
		
	}
	return bReturn;
	
}

	/**
	 * This method Will verifying the Text /value on web page using css attribute
	 * @param css -css is the webElement 
	 * @param expectedText -is user expecting text/value
	 * @return -bReturn
	 */
	public boolean verifyTextByCssSElector(String cssSelector, String expectedText) {
		boolean bReturn  = false;String actualText="";
	try {
		actualText = driver.findElement(By.cssSelector(cssSelector)).getText();
		if (actualText.equals(expectedText)){
			Reporter.reportStep("The text: "+actualText+" contains the value :"+expectedText, "PASS");
			bReturn  = true;
		}
		else {
			Reporter.reportStep("The text: "+actualText+" did not contain the value :"+expectedText, "FAIL");
		}
	} catch (NoSuchElementException e) {
		Reporter.reportStep("The text: "+actualText+" did not contain the value :"+expectedText, "FAIL");
	}
	finally
	{
		
	}
	return bReturn;
	
}
	/**
	 * This method Will verifying the Text /value on web page using linkText attribute
	 * @param linkText -linkText is the webElement 
	 * @param expectedText -is user expecting text/value
	 * @return -bReturn
	 */
	public boolean verifyTextByLinkText(String linktext, String expectedText) {
		boolean bReturn  = false;String actualText="";
	try {
		actualText = driver.findElement(By.linkText(linktext)).getText();
		if (actualText.equals(expectedText)){
			Reporter.reportStep("The text: "+actualText+" contains the value :"+expectedText, "PASS");
			bReturn  = true;
		}
		else {
			Reporter.reportStep("The text: "+actualText+" did not contain the value :"+expectedText, "FAIL");
		}
	} catch (NoSuchElementException e) {
		Reporter.reportStep("The text: "+actualText+" did not contain the value :"+expectedText, "FAIL");
	}
	finally
	{
		
	}
	return bReturn;
	
	}

	/**
	 * This method will close all the browsers
	 * @author Babu - TestLeaf
	 */
	public void quitBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			Reporter.reportStep("The browser:"+driver.getCapabilities().getBrowserName()+" could not be closed.", "FAIL");
		}

	}

	/**
	 * This method will click the element using id as locator
	 * @param id  The id (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickById(String id) {
		boolean bReturn = false;
		try{
			driver.findElement(By.id(id)).click();
			Reporter.reportStep("The element with id: "+id+" is clicked.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with id: "+id+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will click the element using id as locator
	 * @param id  The id (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickByClassName(String classVal) {
		boolean bReturn = false;
		try{
			driver.findElement(By.className(classVal)).click();
			Reporter.reportStep("The element with class Name: "+classVal+" is clicked.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with class Name: "+classVal+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}
	/**
	 * This method will click the element using name as locator
	 * @param name  The name (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickByName(String name) {
		boolean bReturn = false;
		try{
			driver.findElement(By.name(name)).click();
			Reporter.reportStep("The element with name: "+name+" is clicked.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with name: "+name+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will click the element using link name as locator
	 * @param name  The link name (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickByLink(String name) {
		boolean bReturn = false;
		try{
			driver.findElement(By.linkText(name)).click();
			Reporter.reportStep("The element with link name: "+name+" is clicked.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with link name: "+name+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will click the element using xpath as locator
	 * @param xpathVal  The xpath (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickByXpath(String xpathVal) {
		boolean bReturn = false;
		try{
			driver.findElement(By.xpath(xpathVal)).click();
			Reporter.reportStep("The element : "+xpathVal+" is clicked.", "PASS");
			bReturn = true;
		} catch (Exception e) {
			Reporter.reportStep("The element with xpath: "+xpathVal+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}
	/**
	 * This method will clicking the element by tagName 
	 * @param tagName -tagName is the webElement
	 * @throws NoSuchElementException
	 */
	public boolean clickByTagName(String tagName) {
		boolean bReturn=false;
		try {
			driver.findElement(By.tagName(tagName)).click();
			Reporter.reportStep("The element : "+tagName+" is clicked.", "PASS");
			bReturn=true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The element with tagName: "+tagName+" could not be clicked.", "FAIL");
		}catch (Exception e) {
			Reporter.reportStep("The element with tagName: "+tagName+" could not be clicked.", "FAIL");
		}
		finally{
			
		}
		return bReturn;
	}
	/**
	 * This method will clicking the element by cssSelector 
	 * @param tagName -cssSelector is the webElement
	 * @throws NoSuchElementException
	 * @throws Exception
	 * @author RajaKumar
	 */
	public boolean clickByCssSelector(String cssSelector) {
		boolean bReturn=false;
		try {
			driver.findElement(By.cssSelector(cssSelector)).click();
			Reporter.reportStep("The element : "+cssSelector+" is clicked.", "PASS");
			bReturn=true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The element with cssSelector: "+cssSelector+" could not be clicked.", "FAIL");
		}catch (Exception e) {
			Reporter.reportStep("The element with cssSelector: "+cssSelector+" could not be clicked.", "FAIL");
		}
		finally{
			
		}
		return bReturn;
	}
	//Select 
	/**
	 * This method will selecting the drop down option by value using WebElemenet ID 
	 * @param id -id of webElement
	 * @param Value -Selecting the dropdown option By using value
	 * @throws IOException
	 * @throws ElementNotVisibleExcetion
	 * @throws- NoSuchElementException
	 */
	public boolean selectValueByID(String idValue, String value){
		boolean flag=false;
		try {
			new Select(driver.findElement(By.id(idValue))).selectByValue(value);
			Reporter.reportStep("The element with id - "+idValue+ " of dropdown is selected ", "PASS");
			 flag=true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The element with id - "+idValue+ " not visible to select option", "FAIL");
		}
		catch(ElementNotVisibleException e){
			Reporter.reportStep("The element with id - "+idValue+ " not visible to select option", "FAIL");
		}catch(Exception e){
			Reporter.reportStep("The element with id - "+idValue+ " not visible to select option", "FAIL");
		}
		finally
		{
					
		}
		return flag;
	}

	/**
	 * This method will selecting the drop down option by value using WebElemenet Name
	 * @param name -name  of the webElement
	 * @param Value -Selecting the dropdown option By using value
	 * @throws IOException
	 * @throws ElementNotVisibleExcetion
	 * @throws- NoSuchElementException
	 */
	public boolean selectValueByName(String name, String value) {
		boolean flag=false;
		try {
			 new Select(driver.findElement(By.name(name))).selectByValue(value);
			//List<WebElement> allOptions = getAlloptions.getOptions();
			 flag=true;
			 Reporter.reportStep("The element with name - "+name+ " selected for drop down", "PASS");
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The element with name - "+name+ " not visible to select option", "FAIL");	
		}catch(ElementNotVisibleException e){
			Reporter.reportStep("The element with name - "+name+ " not visible to select option", "FAIL");
		}catch(Exception e){
			Reporter.reportStep("The element with name - "+name+ " not visible to select option", "FAIL");
		}
		finally
		{
					
		}
		
		return flag;
	}

	/**
	 * This method will selecting the drop down option by value using WebElemenet ClassName
	 * @param className -name  of the webElement
	 * @param value -Selecting the dropdown option By using value
	 * @throws ElementNotVisibleExcetion
	 * @throws- NoSuchElementException
	 */
	public boolean selectValueByClassName(String className, String value) {
		boolean flag=false;
		try {
			new Select(driver.findElement(By.className(className))).selectByValue(value);
			//List<WebElement> allOptions = dropdown.getOptions();
			 Reporter.reportStep("The element with className - "+className+ " of the drop down selected ", "PASS");
			flag=true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The element with className - "+className+ " not visible to select option", "FAIL");	
		}catch(ElementNotVisibleException e){
			Reporter.reportStep("The element with className - "+className+ " not visible to select option", "FAIL");
		}catch(Exception e){
			Reporter.reportStep("The element with className - "+className+ " not visible to select option", "FAIL");
		}
		
		finally
		{
					
		}
		return flag;
	}

	/**
	 * This method will selecting the drop down option by value using WebElemenet tagname
	 * @param tagName -TagName  of the webElement
	 * @param Value -Selecting the dropdown option By using value
	 * @throws ElementNotVisibleExcetion
	 * @throws- NoSuchElementException
	 */

	public boolean selectValueByTagName(String tagName, String value) {
		boolean flag=false;
		try {
			 new Select(driver.findElement(By.tagName(tagName))).selectByValue(value);;
			//List<WebElement> allOptions = dropdown.getOptions();
			 Reporter.reportStep("The element with className - "+tagName+ " of the drop down selected ", "PASS");
			flag=true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The element with tagName - "+tagName+ " not visible to select option", "FAIL");	
			
		}catch(ElementNotVisibleException e){
			Reporter.reportStep("The element with tagName - "+tagName+ " not visible to select option", "FAIL");
			
		}catch(Exception e){
			Reporter.reportStep("The element with tagName - "+tagName+ " not visible to select option", "FAIL");
		}
		finally
		{
					
		}
		return flag;
	}		

	/**
	 * This method will selecting the drop down option by value using WebElemenet xpath
	 * @param Xpath -xpath  of the webElement
	 * @param Value -Selecting the dropdown option By using value
	 * @throws ElementNotVisibleExcetion
	 * @throws- NoSuchElementException
	 */

	public boolean selectValueByXpath(String xpath, String value) {
		boolean flag=false;
		try {
			new Select(driver.findElement(By.xpath(xpath))).selectByValue(value);
			Reporter.reportStep("The element with className - "+xpath+ " of the drop down selected ", "PASS");
			flag=true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The element with xpath - "+xpath+ " not visible to select option", "FAIL");	
		}catch(ElementNotVisibleException e){
				Reporter.reportStep("The element with xpath - "+xpath+ " not visible to select option", "FAIL");
		}catch(Exception e){
			Reporter.reportStep("The element with xpath - "+xpath+ " not visible to select option", "FAIL");
			
		}
		finally
		{
					
		}
		return flag;
	}	

	/**
	 * This method will select the dropdown option by Value using CSS WebElement 
	 * @param css -CSS is the WebElement
	 * @param value -value is the value of Drop down options 
	 * @return flag.
	 * @throws NoSuchElementException
	 * @throws ElementNotVisibleException
	 */

	public boolean selectValueByCSS(String css, String value) {
			boolean flag=false;
		try {
			new Select(driver.findElement(By.cssSelector(css))).selectByValue(value);
			Reporter.reportStep("The element with cssSelector - "+css+ " of the drop down selected ", "PASS");
			flag=true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The element with xpath - "+css+ " not visible to select option", "FAIL");
		}catch(ElementNotVisibleException e){
			Reporter.reportStep("The element with xpath - "+css+ " not visible to select option", "FAIL");
		}catch (Exception e) {
			Reporter.reportStep("The element with xpath - "+css+ " not visible to select option", "FAIL");
		}
		finally
		{
				
		}
		return flag;
	}	

	
	/**
	 * This method will select the dropdown option by visualText using name WebElement 
	 * @param name -name is the WebElement
	 * @param visualText -visualText is the value of Drop down options 
	 * @return flag.
	 * @throws NoSuchElementException
	 * @throws ElementNotVisibleException
	 */

	public boolean selectVisibletextByName(String name, String value) {
		boolean flag= false;
		try {
			 new Select(driver.findElement(By.name(name))).selectByVisibleText(value);
			 Reporter.reportStep("The element with name - "+name+ " of the drop down selected ", "PASS");
			flag=true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The element with name - "+name+ " not visible to select option", "FAIL");
			
		}catch(ElementNotVisibleException e){
			Reporter.reportStep("The element with name - "+name+ " not visible to select option", "FAIL");
		}catch(Exception e){
			Reporter.reportStep("The element with name - "+name+ " not visible to select option", "FAIL");
		}
		finally{
		
		}
		return flag;

	}

	/**
	 * This method will select the dropdown option by visibleText using ID WebElement 
	 * @param id -id is the WebElement
	 * @param visibleText -visibleText is the visibleText of Drop down options 
	 * @return flag.
	 * @throws NoSuchElementException
	 * @throws ElementNotVisibleException
	 */

	public boolean selectVisibleTextByID(String id, String visibleText) {
		boolean flag= false;
		try {
			new Select(driver.findElement(By.id(id))).selectByVisibleText(visibleText);
			Reporter.reportStep("The element with id - "+id+ " of the drop down selected ", "PASS");
			flag= true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The element with id - "+id+ " not visible to select option", "FAIL");
		}catch(ElementNotVisibleException e){
			Reporter.reportStep("The element with id - "+id+ " not visible to select option", "FAIL");
		}catch(Exception e){
			Reporter.reportStep("The element with id - "+id+ " not visible to select option", "FAIL");
		}
		finally{
			
		}
		return flag;

	}

	/**
	 * This method will select the dropdown option by visibleText using ClassName Element 
	 * @param className -ClassName is the WebElement
	 * @param visibleText -visibleText is the visible text of Drop down options 
	 * @return flag.
	 * @throws NoSuchElementException
	 * @throws ElementNotVisibleException
	 */

	public boolean selectVisibleTextByClassname(String className, String visibleText) {
		boolean flag= false;
		try {
			 new Select(driver.findElement(By.className(className))).selectByVisibleText(visibleText);
			 Reporter.reportStep("The element with className - "+className+ " of the drop down selected ", "PASS");
			flag=true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The element with className - "+className+ " not visible to select option", "FAIL");
		}catch(ElementNotVisibleException e){
			Reporter.reportStep("The element with className - "+className+ " not visible to select option", "FAIL");
		}
		finally{
			
		}
		return flag;

	}

	/**
	 * This method will select the dropdown option by visibleText using tagName WebElement 
	 * @param tagName -tagName is the WebElement
	 * @param visibleText -visibleText is the visible text  of Drop down options 
	 * @return flag.
	 * @throws NoSuchElementException
	 * @throws ElementNotVisibleException
	 */

	public boolean selectVisibleTextByTagName(String tagName, String visibleText) {
		boolean flag= false;
		try {
			 new Select(driver.findElement(By.tagName(tagName))).selectByVisibleText(visibleText);
			 Reporter.reportStep("The element with className - "+tagName+ " of the drop down selected ", "PASS");
			flag= true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The element with tagName - "+tagName+ " not visible to select option", "FAIL");
		}catch(ElementNotVisibleException e){
			Reporter.reportStep("The element with tagName - "+tagName+ " not visible to select option", "FAIL");
		}catch(Exception e){
			Reporter.reportStep("The element with tagName - "+tagName+ " not visible to select option", "FAIL");
		}
		finally{
			
			
		}
		return flag;

	}

	/**
	 * This method will select the dropdown option by visibleText using Xpath WebElement 
	 * @param xpath -xpath is the WebElement
	 * @param visibleText -visibleText is the visibleText of Drop down options 
	 * @return flag.
	 * @throws NoSuchElementException
	 * @throws ElementNotVisibleException
	 */

	public boolean selectVisibleTextByXpath(String xpath, String visibleText) {
		boolean flag= false;
		try {
			new Select(driver.findElement(By.xpath(xpath))).selectByVisibleText(visibleText);
			Reporter.reportStep("The element with className - "+xpath+ " of the drop down selected ", "PASS");
			flag=true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The element with xpath - "+xpath+ " not visible to select option", "FAIL");
			}catch(ElementNotVisibleException e){
				Reporter.reportStep("The element with xpath - "+xpath+ " not visible to select option", "FAIL");
			}
		finally{
			
			
		}
		return flag;

	}

	/**
	 * This method will select the dropdown option by index using css  WebElement 
	 * @param css -CSS is the WebElement
	 * @param visibleText -visibleText is the visible text of Drop down options 
	 * @return flag.
	 * @throws NoSuchElementException
	 * @throws ElementNotVisibleException
	 */

	public boolean selectVisibleTextByCSS(String css, String visibleText) {
		boolean flag= false;
		try {
			new Select(driver.findElement(By.cssSelector(css))).selectByVisibleText(visibleText);
			Reporter.reportStep("The element with cssSelector - "+css+ " of the drop down selected ", "PASS");
			flag=true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The element with cssSelector - "+css+ " not visible to select option", "FAIL");
		}catch(ElementNotVisibleException e){
			Reporter.reportStep("The element with cssSelector - "+css+ " not visible to select option", "FAIL");
		}catch(Exception e){
			Reporter.reportStep("The element with cssSelector - "+css+ " not visible to select option", "FAIL");
		}
		finally{
			
		}
		return flag;

	}
	/**
	 * This method will select the dropdown option by index using name WebElement 
	 * @param name -name is the WebElement
	 * @param indexNumber -indexNumber is the index of Drop down options 
	 * @return flag.
	 * @throws NoSuchElementException
	 * @throws ElementNotVisibleException
	 */
	public boolean selectIndexByName(String name, int indexNumber){
		boolean flag= false;
		try {
			 new Select(driver.findElement(By.name(name))).selectByIndex(indexNumber);
			 Reporter.reportStep("The element with name - "+name+ " of the drop down selected ", "PASS");
			flag=true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The element with name - "+name+ " not visible to select option", "FAIL");
		}catch(ElementNotVisibleException e){
			Reporter.reportStep("The element with name - "+name+ " not visible to select option", "FAIL");
		}catch(Exception e){
			Reporter.reportStep("The element with name - "+name+ " not visible to select option", "FAIL");
		}
		finally{
			
		}
		return flag;

	}

	/**
	 * This method will select the dropdown option by index using id WebElement 
	 * @param id -id is the WebElement
	 * @param indexNumber -indexNumber is the index of Drop down options 
	 * @return flag.
	 * @throws NoSuchElementException
	 * @throws ElementNotVisibleException
	 */

	public boolean selectIndexById(String id,int indexNumber) {
		boolean flag= false;
		try {
		new Select(driver.findElement(By.id(id))).selectByIndex(indexNumber);
		Reporter.reportStep("The element with name - "+id+ " of the drop down selected ", "PASS");
			flag=true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The element with id - "+id+ " not visible to select option", "FAIL");
		}catch(ElementNotVisibleException e){
			Reporter.reportStep("The element with id - "+id+ " not visible to select option", "FAIL");
		}catch(Exception e){
			Reporter.reportStep("The element with id - "+id+ " not visible to select option", "FAIL");
		}
		finally{
			
		}
		return flag;

	}

	/**
	 * This method will select the dropdown option by index using className WebElement 
	 * @param className -className is the WebElement
	 * @param indexNumber -indexNumber is the index of Drop down options 
	 * @return flag.
	 * @throws NoSuchElementException
	 * @throws ElementNotVisibleException
	 */

	public boolean selectIndexByclassName(String className, int indexNumber) {
		boolean flag= false;
		try {
			new Select(driver.findElement(By.className(className))).selectByIndex(indexNumber);
			Reporter.reportStep("The element with className - "+className+ " of the drop down selected ", "PASS");
			flag=true;
		} catch (NoSuchElementException e) {
			System.out.println("The Element with className :"+className+"is not available");
		}catch(ElementNotVisibleException e){
			System.out.println("The element with className - "+className+ " not visible to select option");
		}catch(Exception e){
			System.out.println("The element with className - "+className+ " not visible to select option");
		}
		finally{
			
		}
		return flag;

	}

	/**
	 * This method will select the dropdown option by index using tagName WebElement 
	 * @param tagName -tagName is the WebElement
	 * @param indexNumber -indexNumber is the index of Drop down options 
	 * @return flag.
	 * @throws NoSuchElementException
	 * @throws ElementNotVisibleException
	 */

	public boolean selectIndexBytagName(String tagName, int indexNumber) throws IOException{
		boolean flag= false;
		try {
			new Select(driver.findElement(By.tagName(tagName))).selectByIndex(indexNumber);
			Reporter.reportStep("The element with tagName - "+tagName+ " of the drop down selected ", "PASS");
			flag=true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The element with tagName - "+tagName+ " not visible to select option", "FAIL");
		}catch (Exception e) {
			Reporter.reportStep("The element with tagName - "+tagName+ " not visible to select option", "FAIL");
		}
		finally{
		
			
		}
		return flag;

	}

	
	/**
	 * This method will select the dropdown option by index using Xpath WebElement 
	 * @param xpath -xpath is the WebElement
	 * @param indexNumber -indexNumber is the index of Drop down options 
	 * @return flag.
	 * @throws NoSuchElementException
	 * @throws ElementNotVisibleException
	 */
	public boolean selectindexByXpath(String xpath, int indexNumber) throws IOException{
		boolean flag= false;
		try {
			new Select(driver.findElement(By.xpath(xpath))).selectByIndex(indexNumber);
			System.out.println(" System selected drop down option");
			flag=true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The element with xpath - "+xpath+ " not visible to select option", "FAIL");
		}catch(ElementNotVisibleException e){
			Reporter.reportStep("The element with xpath - "+xpath+ " not visible to select option", "FAIL");
		}catch(Exception e){
			Reporter.reportStep("The element with xpath - "+xpath+ " not visible to select option", "FAIL");
		}
		finally{
			
		}
		return flag;

	}

	/**
	 * This method will select the dropdown option by index using CSS Webelement 
	 * @param css -CSS is the WebElement
	 * @param indexNumber -indexNumber is the index of Drop down options 
	 * @return flag.
	 * @throws NoSuchElementException
	 * @throws ElementNotVisibleException
	 */
	public boolean selectindexByCSS(String css, int indexNumber) throws IOException{
		boolean flag= false;
		try {
		 new Select(driver.findElement(By.cssSelector(css))).selectByIndex(indexNumber);
			flag=true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The element with CSS - "+css+ " not visible to select option", "FAIL");
		}catch(ElementNotVisibleException e){
			Reporter.reportStep("The element with CSS - "+css+ " not visible to select option", "FAIL");
		}catch (Exception e) {
			Reporter.reportStep("The element with CSS - "+css+ " not visible to select option", "FAIL");
		}
		finally{
			
		}
		return flag;

	}

	/**
	 * This method will mouse over on the element using xpath as locator
	 * @param xpathVal  The xpath (locator) of the element to be moused over
	 * @author Babu - TestLeaf
	 */
	public boolean mouseOverByXpath(String xpathVal) {
		boolean bReturn = false;
		try{
			new Actions(driver).moveToElement(driver.findElement(By.xpath(xpathVal))).build().perform();
			Reporter.reportStep("The mouse over by xpath : "+xpathVal+" is performed.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The mouse over by xpath : "+xpathVal+" could not be performed.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will mouse over on the element using link name as locator
	 * @param xpathVal  The link name (locator) of the element to be moused over
	 * @author Babu - TestLeaf
	 */
	public boolean mouseOverByLinkText(String linkName) {
		boolean bReturn = false;
		try{
			new Actions(driver).moveToElement(driver.findElement(By.linkText(linkName))).build().perform();
			Reporter.reportStep("The mouse over by link : "+linkName+" is performed.", "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The mouse over by link : "+linkName+" could not be performed.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will getTExt on the element using xpath as locator
	 * @param xpathVal is the WebElement
	 * @throws Exception
	 * @return string 
	 * @author RajaKumar
	 */
	public String getTextByXpath(String xpathVal){
		String bReturn = "";
		try{
			return driver.findElement(By.xpath(xpathVal)).getText();
		} catch (Exception e) {
			Reporter.reportStep("The element with xpath: "+xpathVal+" could not be found.", "FAIL");
		}
		return bReturn; 
	}
	/**
	 * This method will getText on the element using cssSelector as locator
	 * @param xpathVal is the WebElement
	 * @throws Exception
	 * @return string 
	 * @author RajaKumar
	 */
	public String getTextByCssSelector(String cssSelector){
		String bReturn = "";
		try{
			return driver.findElement(By.cssSelector(cssSelector)).getText();
		} catch (Exception e) {
			Reporter.reportStep("The element with xpath: "+cssSelector+" could not be found.", "FAIL");
		}
		return bReturn; 
	}
	/**
	 * This method will getText on the element using linkValue as locator
	 * @param linkValue is the WebElement
	 * @throws Exception
	 * @return string 
	 * @author RajaKumar
	 */
	public String getTextBylink(String linkValue){
		String bReturn = "";
		try{
			return driver.findElement(By.linkText(linkValue)).getText();
		} catch (Exception e) {
			Reporter.reportStep("The element with xpath: "+linkValue+" could not be found.", "FAIL");
		}
		return bReturn; 
	}
	/**
	 * This method will getText on the element using nameValue as locator
	 * @param nameValue is the WebElement
	 * @throws Exception
	 * @return string 
	 * @author RajaKumar
	 */
	public String getTextByName(String nameValue){
		String bReturn = "";
		try{
			return driver.findElement(By.name(nameValue)).getText();
		} catch (Exception e) {
			Reporter.reportStep("The element with xpath: "+nameValue+" could not be found.", "FAIL");
		}
		return bReturn; 
	}
	/**
	 * This method will getText on the element using className as locator
	 * @param className is the WebElement
	 * @throws Exception
	 * @return string 
	 * @author RajaKumar
	 */
	public String getTextByClassName(String className){
		String bReturn = "";
		try{
			return driver.findElement(By.className(className)).getText();
		} catch (Exception e) {
			Reporter.reportStep("The element with xpath: "+className+" could not be found.", "FAIL");
		}
		return bReturn; 
	}
	/**
	 * This method will getText on the element using IDValue as locator
	 * @param IDValue is the WebElement
	 * @throws Exception
	 * @return string 
	 * @author RajaKumar
	 */
	public String getTextByID(String IDValue){
		String bReturn = "";
		try{
			return driver.findElement(By.id(IDValue)).getText();
		} catch (Exception e) {
			Reporter.reportStep("The element with xpath: "+IDValue+" could not be found.", "FAIL");
		}
		return bReturn; 
	}
	/**
	 * This method will select the drop down value using id as locator
	 * @param id The id (locator) of the drop down element
	 * @param value The value to be selected (visibletext) from the dropdown 
	 * @author Babu - TestLeaf
	 */
	public boolean selectById(String id, String value) {
		boolean bReturn = false;
		try{
			new Select(driver.findElement(By.id(id))).selectByVisibleText(value);;
			Reporter.reportStep("The element with id: "+id+" is selected with value :"+value, "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The value: "+value+" could not be selected.", "FAIL");
		}
		return bReturn;
	}
	/**
	 * This method Will get the parent window using getWindowhandle() 
	 * and save into parentWindow
	 * @return
	 */
	public String getParentWindowHandle() {
		String parentWindow = null;
		try {
			parentWindow = driver.getWindowHandle();
		} catch (NoSuchWindowException e) {
			Reporter.reportStep("The Window  is not Available", "FAIL");
		} finally {

		}
		return parentWindow;

	}
	/**
	 * This method will Handling the windows switch to child window
	 * @return
	 */
	public boolean switchToChildWindows() {
		boolean bReturn=false;
		try {
			Set<String> allWindows = driver.getWindowHandles();
			//switch to the secondwindow
			for (String childWindow : allWindows) {
				driver.switchTo().window(childWindow);
				}
			System.out.println(" switch to child");
		} catch (NoSuchWindowException e) {
			Reporter.reportStep("The Window  is not Available", "FAIL");
			 } finally {
			
			
		}
		return bReturn;
	}
	/**
	 * This method will switching window child to parent /parent to child 
	 * @param window -window is title of window
	 * @return boolean
	 * @throws NoSuchWindowException
	 * @throws IOException
	 * @author RajaKumar
	 */
	public boolean switchToParentWindow() {
		boolean bReturn=false;
		try {
			Set<String> allWindows = driver.getWindowHandles();
			//switch to the secondwindow
			for (String childWindow : allWindows) {
				driver.switchTo().window(childWindow);
				break;
			}
		} catch (NoSuchWindowException e) {
			Reporter.reportStep("The Window  is not Available", "FAIL");
			bReturn=false;
		} finally {


		}
		return bReturn;
	}
	
	public boolean clickByXpathWithOutSnap(String xpathVal) {
		boolean bReturn = false;
		try{
			driver.findElement(By.xpath(xpathVal)).click();
			//Reporter.reportStep("The element : "+xpathVal+" is clicked.", "PASS");
			bReturn = true;
		} catch (Exception e) {
			Reporter.reportStep("The element with xpath: "+xpathVal+" could not be clicked.", "FAIL");
		}
		return bReturn;
	}
	
	
	/**
	 * This method will switching frame by using the id attribute.
	 * @param iframeID -is the id of frame
	 * @return boolean
	 * throws NoSuchFrameException
	 * @author RajaKumar
	 */
	public boolean switchToFramesById(String iframeID) {
		boolean bReturn=false;
		try {
			driver.switchTo().frame(iframeID);
			bReturn =true;
		} catch (NoSuchFrameException e) {
			Reporter.reportStep("The Frame with id :"+iframeID+"is not available", "FAIL");
		}catch (Exception e) {
			Reporter.reportStep("The Frame with id :"+iframeID+"is not available", "FAIL");
			}
		finally{
			//screenshot();
		}
		return bReturn;
	}

	/**
	 * This method will be switch to frame by using the webElement
	 * @param WebElement  is WebElement of the frame
	 * @return boolean
	 * @throws NoSuchFrameException
	 * @throws Exception
	 * @author RajaKumar
	 */
	public boolean switchToFrameByWebElement(String WebElement) {
		boolean bReturn =false;
		try {
			driver.switchTo().frame(WebElement);
			bReturn=true;
		} catch (NoSuchFrameException e) {
			Reporter.reportStep("The Frame with webElement :"+WebElement+"is not available", "FAIL");
		}catch (Exception e) {
			Reporter.reportStep("The Frame with webElement :"+WebElement+"is not available", "FAIL");
			}
		finally{
			
		}
		return bReturn;
	}
	/**
	 * This method will be switching frame by using Index of the frame
	 * @param index- is the index of the frame 
	 * @return Boolean
	 * @throws NoSuchFrameException
	 * @throws Exception
	 * @author RajaKumar
	 */
	public boolean switchToFrameByIndex(int index){
		boolean bReturn=false;
		try {
			driver.switchTo().frame(index);
			bReturn=true;
		} catch (NoSuchFrameException e) {
			Reporter.reportStep("The Frame with index :"+index+"is not available", "FAIL");
		}
		finally{
			//screenshot();
		}
		return bReturn;
	}
	/** 
 	 * This method will  switch to Alert 
	 * @throws NoAlertPresentException
	 * @author RajaKumar 
	 */
	public boolean  switchToAlert() {
		boolean bReturn=false;
		try {
			driver.switchTo().alert();
			bReturn=true;
		} catch (NoAlertPresentException e) {
			Reporter.reportStep("The Alert is not available", "FAIL");
		}finally {
			/*screenshot();*/
		}
		return bReturn;
	}
	/**
	 * This method will verifying the alert Text by using getText() method.
	 * @param expectedAlertText
	 * @return
	 * 
	 */
	public boolean getTextFromAlert(String expectedAlertText)  {
		boolean bReturn=false;
		String actualAlertText = null;
		try {
			actualAlertText = driver.switchTo().alert().getText();
			if(actualAlertText.equals(expectedAlertText)){
				System.out.println("Alert TExt Matches ");
				bReturn=true;
			}else
				Reporter.reportStep("The Alert Text is not Match "+actualAlertText+"with"+ expectedAlertText, "FAIL");
			} catch (NoAlertPresentException e) {
			Reporter.reportStep("The Alert is not available", "FAIL");
			}finally {
			
		}
		return bReturn;
		
	}
	// Send keys to Alert

		/**
		 * This method will enter the data and accept the alert using accept() and sendKeys()
		 * @param data -enter user  data  to alert
		 * @return bReturn
		 
		 */
		public boolean sendDataToAlert(String data) {
			boolean bRturn=false;
			try {
				driver.switchTo().alert().sendKeys(data);
				bRturn=true;
			} catch (NoAlertPresentException e) {
				Reporter.reportStep("The Alert is not available", "FAIL");
			} finally {
				
			}
			return bRturn;
		}

	/**
	 * This method will accept the Alert using Accept()
	 * @return
	 */
	public boolean acceptAlert()  {
		boolean bReturn=false;
		try {
			driver.switchTo().alert().accept();
			bReturn=true;
			
		} catch (NoAlertPresentException e) {
			Reporter.reportStep("The Alert is not available", "FAIL");
		} catch (UnhandledAlertException e) {
			Reporter.reportStep("The Alert is not handled", "FAIL"); 
		}finally {
			
		}
		return bReturn;
	}
	/**
	 * This Method Will Dismiss the alert using Dismiss ()
	 * @return
	 */
	public boolean dismissAlert()  {
		boolean bReturn=false;
		try {
			driver.switchTo().alert().dismiss();
			bReturn=true;
		} catch (NoAlertPresentException e) {
			Reporter.reportStep("The Alert is not available", "FAIL");
		} finally {
			
		}
		return bReturn;
	}
	/**
	 * This method for wait for WebElement by visible text of Element by id
	 * @param element
	 * @return flag
	 */
	public boolean waitForVisiblityOfElementByID(String elementId){
		boolean flag=false;
		try {
			WebDriverWait wait =new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementId)));
			flag=true;
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			Reporter.reportStep("The Frame with webElement :"+elementId+"is not available", "FAIL");
		} catch (Exception e) {
			Reporter.reportStep("The Frame with webElement :"+elementId+"is not available", "FAIL");
		}
		return flag;
	}
	/**
	 * This method for wait for WebElement by visible text of Element by name
	 * @param element
	 * @return flag
	 */
	public boolean waitForVisiblityOfElementByName(String elementName){
		boolean flag=false;
		try {
			WebDriverWait wait =new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(elementName)));
			flag=true;
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			Reporter.reportStep("The Frame with webElement :"+elementName+"is not available", "FAIL");
		} catch (Exception e) {
			Reporter.reportStep("The Frame with webElement :"+elementName+"is not available", "FAIL");
		}
		return flag;
	}
	/**
	 * This method for wait for WebElement by visible text of Element by elementClassName
	 * @param element
	 * @return flag
	 */
	public boolean waitForVisiblityOfElementByClassName(String elementClassName){
		boolean flag=false;
		try {
			WebDriverWait wait =new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(elementClassName)));
			flag=true;
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			Reporter.reportStep("The Frame with webElement :"+elementClassName+"is not available", "FAIL");
		} catch (Exception e) {
			Reporter.reportStep("The Frame with webElement :"+elementClassName+"is not available", "FAIL");
		}
		return flag;
	}
	/**
	 * This method for wait for WebElement by visible text of Element by cssSelector
	 * @param cssSelector
	 * @return flag
	 */
	public boolean waitForVisiblityOfElementBycssSelector(String cssSelector){
		boolean flag=false;
		try {
			WebDriverWait wait =new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
			flag=false;
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			Reporter.reportStep("The Frame with webElement :"+cssSelector+"is not available", "FAIL");
		} catch (Exception e) {
			Reporter.reportStep("The Frame with webElement :"+cssSelector+"is not available", "FAIL");
		}
		return flag;
	}
	public boolean waitForVisiblityOfElementByXpath(String elementXpath){
		try {
			WebDriverWait wait =new WebDriverWait(driver, 50);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
		} catch (NoSuchElementException e) {
			Reporter.reportStep("The Frame with webElement :"+elementXpath+"is not available", "FAIL");
		}catch (Exception e) {
			Reporter.reportStep("The Frame with webElement :"+elementXpath+"is not available", "FAIL");
		}
		return false;
	}
	/**
	 * This method will verifying the Current URL of the Browser
	 * @return bReturn
	 */
	public boolean getURLcurrent() 	{
		boolean bReturn=false;
		try {
			driver.getCurrentUrl();
			bReturn=true;
		} catch (WebDriverException e) {
			Reporter.reportStep("The URL is not able to get", "FAIL");
		}
		finally{
			
		}
		return bReturn;
	}
	public List<WebElement> verifyTableByXpath(String xpath){
		List<WebElement>row=	driver.findElements(By.xpath(xpath));
		//System.out.println("Row Count  "+row.size());
		return row;
	}

	public void loadObjects() throws FileNotFoundException, IOException{
		prop = new Properties();
		prop.load(new FileInputStream(new File("./object.properties")));
	
	}
	
	

}

