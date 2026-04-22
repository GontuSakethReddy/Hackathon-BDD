package runner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
(
    features = "src/test/java/feature",
    glue = {"stepdefinition"},
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests 
{
    public static String browserName;

    @BeforeClass
    @Parameters("browser")
    public void defineBrowser(String browser) 
    {
        browserName = browser;
    }
}