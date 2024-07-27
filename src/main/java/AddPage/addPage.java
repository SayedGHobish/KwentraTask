package AddPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import PageBase.pageBase;

public class addPage extends pageBase{

	public addPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath ="/html/body/fo-root/cloudinn-nav/div/mat-sidenav-container/mat-sidenav-content/div")
	public WebElement addUserPage;
	@FindBy(id="first_name-field")
	WebElement firstName;
	@FindBy(id="last_name-field")
	WebElement lastName;
	@FindBy(id="save-btn")
	public WebElement saveBTN;
	@FindBy(id="first_name-required-error")
	public WebElement firstRquired;
	@FindBy(id="last_name-required-error")
	public WebElement secRquired;
	

	
	
	public void addprofilesJustReuired(String first, String last) {
		firstName.sendKeys(first);
		lastName.sendKeys(last);
		saveBTN.click();
	}
	
	

}
