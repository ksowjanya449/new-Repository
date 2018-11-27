package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage 
{
	public WebDriver driver;
	
	//Locators
	@FindBy(name="mobileNo")
	public WebElement mb_no;
	
	@FindBy(name="password")
	public WebElement pwd;
	
	@FindBy(xpath="(//button[contains(text(),'Login')] )[1]")
	public WebElement login;
	
	@FindBy(xpath="//*[text()='Enter your mobile number']")
	public WebElement mb_no_blank;
	
	@FindBy(xpath="(//*[text()='Enter password'])[1]")
	public WebElement pwd_blank;
	
	@FindBy(xpath="(//*[contains(text(),'register with us')])[1]")
	public WebElement mb_no_invalid;
	
	@FindBy(xpath="(//*[contains(text(),'Try Again')])[1]")
	public WebElement pwd_invalid;
	
	//constructor method for driver object 
	public Loginpage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//operations
	
	public void fillmbno(String x)
	{
		mb_no.sendKeys(x);
	}
	
	public void fillpwd(String x)
	{
		pwd.sendKeys(x);
	}
	public void clicklogin()
	{
		login.click();
	}
}
