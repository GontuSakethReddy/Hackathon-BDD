package runner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
    features = "src/test/java/feature",
    glue = {"stepdefinition"},
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests
{
	@BeforeClass
    @Parameters("browser")
    public void defineBrowser(String browser) 
	{
        // This takes the "Chrome" value from testng.xml 
        // and sets it so System.getProperty("browser") works in Hooks.java
        System.setProperty("browser", browser);
	}
	
}