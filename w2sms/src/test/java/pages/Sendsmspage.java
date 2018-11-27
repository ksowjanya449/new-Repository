package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Sendsmspage 
{
	public WebDriver driver;
	
	//Locators
	
	@FindBy(xpath="//*[text()='Send Sms']")
	public WebElement send_sms;
	
	@FindBy(xpath="//*[@class='fa icon-logout hidden-xs']")
	public WebElement logout;
	
	//Constructor method for driver object
	
	public Sendsmspage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//Operations
	public void clicklogout()
	{
		logout.click();
	}
	
	
	
}
