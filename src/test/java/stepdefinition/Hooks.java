package stepdefinition;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import utils.DriverFactory;
import runner.TestRunner; 

public class Hooks 
{
    @Before
    public void setup() 
    {
        String browser = TestRunner.browserName;
        
        if (browser == null) 
        {
            browser = "chrome";
        }
        
        DriverFactory.initDriver(browser);
    }

    @After
    public void tearDown() 
    {
        DriverFactory.quitDriver();
    }
}