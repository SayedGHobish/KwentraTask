package homePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import PageBase.pageBase;

public class homePage extends pageBase{

	public homePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id ="main-content")
	public WebElement homePage;
	@FindBy(linkText = "Front Office")
	public WebElement frontOffice;
	@FindBy(id ="Profiles")
	public WebElement profileIcon;
	@FindBy(xpath="/html/body/fo-root/cloudinn-nav/div/mat-sidenav-container/mat-sidenav-content/div")
	public WebElement profilePage;
	@FindBy(id ="create-btn")
	public WebElement createBTN;
	@FindBy(xpath="//snack-bar-container[contains(@class, 'mat-snack-bar-container') and contains(@class, 'success-bar')]//span")
	public WebElement successMessage;
	
	@FindBy(id="name-field")
	public WebElement searchName;
	@FindBy(id="search-button")
	public WebElement searchBTN;
	@FindBy(id="reset-button")
	public WebElement resetBTN;
	@FindBy(id ="listing-row-0")
	public WebElement getSearch;
	@FindBy(id="cell-2")
	public WebElement getName;
	public void searchProfile(String names)
	{
	
		searchName.sendKeys(names);
		searchBTN.click();
	
	}
}
