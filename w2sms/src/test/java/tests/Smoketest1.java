package tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

 @RunWith (Cucumber.class)
	@CucumberOptions(features={"src/test/resources\\mypack\\LoginFeature.feature"},
	//glue={"src/test/java\\tests\\test"},
	tags={"@Regression"},
	format={"pretty","html:target"},
	monochrome=true)
 
 
	public class Smoketest1
	{
		
	}
