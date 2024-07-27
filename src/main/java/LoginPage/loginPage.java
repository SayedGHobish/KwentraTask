package LoginPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import PageBase.pageBase;

public class loginPage extends pageBase {

	public loginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "id_auth-username")
	public WebElement userFiled;
	@FindBy(id ="id_auth-password")
	WebElement passwordField;
	@FindBy(id= "login-submit-btn")
	WebElement loginBTN;
	
	
	public void userLogin(String username, String pass) {
		userFiled.sendKeys(username);
		passwordField.sendKeys(pass);
		loginBTN.click();
	}
	
	
}
