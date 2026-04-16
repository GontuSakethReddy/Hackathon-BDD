package stepdefinition;

import io.cucumber.java.Before;
import utils.DriverFactory;
import io.cucumber.java.After;

public class Hooks 
{
    @Before
    public void setup() 
    {
        DriverFactory.getDriver().manage().window().maximize();
    }

    @After
    public void tearDown() 
    {
        DriverFactory.quitDriver();
    }

}