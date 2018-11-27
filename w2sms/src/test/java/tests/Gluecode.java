package tests;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.Loginpage;
import pages.Sendsmspage;

public class Gluecode
{
	public WebDriverWait wait;
	public WebDriver driver;
	public Loginpage lp;
	public Sendsmspage sp;
	public Scenario s;
	public Properties pro;
	

	@Before
	public void readProperties(Scenario s) throws Exception
	{
		pro=new Properties();
		FileInputStream fip=new FileInputStream("F:\\batch239\\w2sms\\src\\test\\resources\\mypack\\prop.properties");
		pro.load(fip);	
		this.s=s;
		s.write("Properties loaded **");
	}
	
	@Given("^launch site using \"(.*)\"$")
	public void method2(String x)
	{
		if(x.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",pro.getProperty("cdriver"));
			driver =new ChromeDriver();
		}
	
	else if (x.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",pro.getProperty("ffdriver"));
			driver =new FirefoxDriver();
		}

	else if (x.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver",pro.getProperty("iedriver"));
			driver =new InternetExplorerDriver();
		}
	else
		{
			//OperaOptions o=new OperaOptions();
			//o.setBinary("C:\\Users\\sowjanya\\AppData\\Local\\Programs\\Opera\\53.0.2907.68\\Opera.exe");
			//System.setProperty("webdriver.opera.driver","F:\\batch239\\operadriver_win32\\operadriver.exe");
			//driver =new OperaDriver(o);
			s.write("unknown browser");
		}

		lp=new Loginpage(driver);
		sp=new Sendsmspage(driver);
		
		driver.get(pro.getProperty("url"));
		
		wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(lp.mb_no));
		driver.manage().window().maximize();
	}
	
		@Then("^title contains \"(.*)\"$")
		public void mentod3(String y)
		{
			//wait.until(ExpectedConditions.visibilityOf(lp.mb_no));
			String t=driver.getTitle();
			if(t.contains(y))
			{
				s.write("Title test was passed");
			}
			else 
			{
				byte ssbytes[]=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
				s.embed(ssbytes,"Title test was failed");
				Assert.fail();
			}
		}
	@And("^close site$")
	public void method4()
	{
		driver.close();
	}
	
	@When("^Enter mobile number as \"(.*)\"$")
	public void method5(String z)
	{
		wait.until(ExpectedConditions.visibilityOf(lp.mb_no));
		lp.fillmbno(z);
	}
	@And("^enter password as \"(.*)\"$")
	public void method6(String w)
	{
		wait.until(ExpectedConditions.visibilityOf(lp.pwd));
		lp.fillpwd(w);
	}
	
	@And("^click login$")
	public void method7()
	{
		wait.until(ExpectedConditions.visibilityOf(lp.login));
		lp.clicklogin();
	}
	
	@Then("^validate output for criteria \"(.*)\"$")
	public void method8(String c) throws Exception
	{
		//wait.until(temp->((JavascriptExecutor)driver).executeScript("return document.readystate").equals("completed"));
		Thread.sleep(5000);
		try
		{
			if(c.equals("all_valid") && sp.send_sms.isDisplayed())
			{
				s.write("Test passed for valid credentials");
			}
			else if(c.equals("all_valid")&&driver.findElement(By.xpath("//a[@href='send-sms'][@class='active']/span")).isDisplayed())
			{
				s.write("Test passed for valid credentials");
			}
			else if (c.equals("mb_no_blank")&& lp.mb_no_blank.isDisplayed())
			{
				s.write("Test passed for blank mobile number");
			}
			else if (c.equals("pwd_blank")&& lp.pwd_blank.isDisplayed())
			{
				s.write("Test passed for blank password");
			}
			else if (c.equals("mb_no_invalid")&& lp.mb_no_invalid.isDisplayed())
			{
				s.write("Test passed for invalid mobile number");
			}
		
			else if (c.equals("pwd_invalid")&& lp.pwd_invalid.isDisplayed())
			{
				s.write("Test passed for invalid password");
			}
			else
			{
				byte ssbytes[]=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
				s.embed(ssbytes,"login test failed");
				Assert.fail();
			}
		}
		catch(Exception ex)
		{
			byte ssbytes[]=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			s.embed(ssbytes,ex.getMessage());
		}
	}
	@When("^do login with valid data$")
	public void method9(DataTable dt)
	{
		//List<List<String>>l=dt.asList(String.class);
		List<List<String>> l = dt.raw();
		lp.fillmbno(l.get(1).get(0));
		lp.fillpwd(l.get(1).get(1));
		lp.clicklogin();
	}
	@And("^do logout$")
	public void method10()
	{
		//wait.until(temp->((JavascriptExecutor)driver).executeScript("return document.readystate").equals("completed"));
		try
		{
			Thread.sleep(5000);
			sp.clicklogout();
		}
		catch(Exception ex)
		{
			byte ssbytes[]=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			s.embed(ssbytes,ex.getMessage());
		}
}
	@Then ("^home page will be reopened$")
	public void method11()
	{
		if(lp.mb_no.isDisplayed())
		{
			s.write("Logout successfully");
		}
		else
		{
			byte ssbytes[]=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			s.embed(ssbytes,"Title test was failed");
			Assert.fail();

		}
	}

}














