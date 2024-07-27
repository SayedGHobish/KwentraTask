package ScenariosCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Data.excelFiles;
import LoginPage.loginPage;
import PageBase.pageBase;
import TestBase.testBase;
import homePage.homePage;
import AddPage.addPage;

public class testCases extends testBase{
	
	@DataProvider(name = "loginUser")
	public Object [][] loginUser() throws IOException
	{
		excelFiles ER = new excelFiles();

		return ER.getExcelData("data.xlsx/");
	}
	
	@DataProvider(name = "addUser")
	public Object [][] addUsers() throws IOException
	{
		excelFiles ER = new excelFiles();

		return ER.getExcelData("adduser.xlsx/");
	}
	
	
			
	pageBase baseObj;
	loginPage loginObj;
	homePage homeObj;
	addPage addObj;
	 
	
	@Test (dataProvider = "loginUser")
	public void userLoginSuccess(String username, String pass) {
		loginObj = new loginPage(driver);
		homeObj = new homePage(driver);
        waitForElementVisible(loginObj.userFiled);
        loginObj.userLogin(username, pass);
        waitForElementVisible(homeObj.homePage);
        Assert.assertTrue(homeObj.homePage.isDisplayed(),"user not reach home page");
        try {
            screens.screenShots.takeScreenshot(driver, "userLoginSuccess", homeObj.homePage);
        } catch (IOException e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
        }
	}
	
	@Test(dependsOnMethods = {"userLoginSuccess"})
	public void userReachProfile() throws InterruptedException
	{
		homeObj = new homePage(driver);
		waitForElementVisible(homeObj.homePage);
		homeObj.frontOffice.click();
		waitForElementVisible(homeObj.profileIcon);
		homeObj.profileIcon.click();
		Thread.sleep(1500);
		Assert.assertTrue(homeObj.profilePage.isDisplayed(), "user not reach profile page");
		try {
            screens.screenShots.takeScreenshot(driver, "userReachProfile", homeObj.profilePage);
        } catch (IOException e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
        }
	}
	
	@Test(dependsOnMethods = {"userReachProfile"})
	public void userReachAddProfiles() throws InterruptedException
	{
		homeObj = new homePage(driver);
		addObj = new addPage(driver);
		waitForElementVisible(homeObj.profilePage);
		homeObj.createBTN.click();
		waitForElementVisible(addObj.addUserPage);
		Assert.assertEquals(driver.getCurrentUrl(), "https://testingtasks.kwentra.com/frontoffice/#/profileslist/add");
		try {
            screens.screenShots.takeScreenshot(driver, "userReachAddProfiles", addObj.addUserPage);
        } catch (IOException e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
        }
		driver.navigate().back();
		Thread.sleep(1500);
	}
	
	@Test(dependsOnMethods = {"userReachProfile"},dataProvider ="addUser" )
	public void addProfilesWithJsutRequiredField(String first, String last) throws InterruptedException
	
	{
		homeObj = new homePage(driver);
		addObj = new addPage(driver);
		Thread.sleep(2000);
		homeObj.createBTN.click();
		waitForElementVisible(addObj.addUserPage);
		addObj.addprofilesJustReuired(first, last);
		waitForElementVisible(homeObj.successMessage);
		Assert.assertEquals(homeObj.successMessage.getText(),"Your Profile is created successfully");
		
		try {
            screens.screenShots.takeScreenshot(driver, "addProfilesWithJsutRequiredField", homeObj.successMessage);
        } catch (IOException e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
        }
		
	}
	
	

	@SuppressWarnings("deprecation")
	@Test(dependsOnMethods = {"userReachAddProfiles"})
	public void addUserWithoutData() throws InterruptedException
	{
		homeObj = new homePage(driver);
		addObj = new addPage(driver);
		baseObj = new pageBase(driver);
		waitForElementVisible(homeObj.profilePage);
		homeObj.createBTN.click();
		waitForElementVisible(addObj.addUserPage);
		addObj.saveBTN.click();
		waitForElementVisible(addObj.firstRquired);
		Assert.assertEquals(addObj.firstRquired.getText(), "this field is required");
		Assert.assertEquals(addObj.secRquired.getText(), "this field is required");
		baseObj.scrollToElement(addObj.firstRquired);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		try {
            screens.screenShots.takeScreenshot(driver, "addUserWithoutDataFirst", addObj.firstRquired);
            screens.screenShots.takeScreenshot(driver,"addUserWithoutDataLast", addObj.secRquired);
        } catch (IOException e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
        }
		driver.navigate().back();	
		Thread.sleep(1500);

	}
	
	@Test(dependsOnMethods = {"addProfilesWithJsutRequiredField"})
	public void searchOnSpecialUser() throws InterruptedException

	{
		homeObj = new homePage(driver);
		waitForElementVisible(homeObj.profilePage);
		String name="SayedFirst UserSecond";
		homeObj.searchName.sendKeys(name);
		homeObj.searchBTN.click();
		Thread.sleep(2000);
		if(homeObj.getSearch.isDisplayed())
		{
			Assert.assertEquals(homeObj.getName.getText(), name);
			System.out.print("user exists successfully");
			try {
	            screens.screenShots.takeScreenshot(driver, "searchOnSpecialUser", homeObj.getName);
	        } catch (IOException e) {
	            System.out.println("Screenshot capture failed: " + e.getMessage());
	        }
			
		}else {System.out.print("the user not exist");
		try {
            screens.screenShots.takeScreenshot(driver, "searchOnSpecialUser", homeObj.profilePage);
        } catch (IOException e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
        }
		}
		

	}
	
	
	


}
