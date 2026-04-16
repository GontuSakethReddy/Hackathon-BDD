package stepdefinition;

import io.cucumber.java.en.*;
import utils.ConfigReader;
import utils.DriverFactory;
import io.cucumber.datatable.DataTable;
import Pages.HomePage;
import Pages.ResultsPage;
import Pages.ContactSalesPage;
import java.util.Map;
import java.util.Properties;
import java.util.List;

public class CourseSteps 
{
	ConfigReader cr = new ConfigReader();
	Properties prop = cr.init_prop();
	
    HomePage home = new HomePage(DriverFactory.getDriver());
    ResultsPage courses = new ResultsPage(DriverFactory.getDriver());
    ContactSalesPage contact = new ContactSalesPage(DriverFactory.getDriver());

    @Given("the user is on the Coursera homepage")
    public void navigateToHome() 
    {
    		String url = prop.getProperty("url");
    		DriverFactory.getDriver().get(url);
    }

    @When("the user searches for {string}")
    public void searchForCourse(String courseName) 
    {
        home.searchCourse(courseName);
    }

    @When("the user filters by Beginner level and English language")
    public void applyFilters() throws InterruptedException 
    {
        courses.filterBeginnerEnglish();
    }

    @Then("the system should display the top 2 course details")
    public void verifyCourses() 
    {
        System.out.println("Course details extraction completed.");
    }

    @When("the user navigates to the For Business section")
    public void goToBusiness() throws InterruptedException 
    {
        home.clickForBusiness();
    }

    @When("fills the contact sales form with:")
    public void fillForm(DataTable dataTable) 
    {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        
        for(Map<String, String> row : data)
        {
        	    String fname = row.get("firstName");
            String lname = row.get("lastName");
            String email = row.get("email");
            contact.fillForm(fname, lname, email);
        }
    }

    @When("submits the form")
    public void submit() 
    {
        contact.submitForm();
    }

    @Then("an email validation error {string} should be displayed")
    public void verifyError(String expectedPart) 
    {
        String actualError = contact.getEmailErrorMessage();
        
        assert actualError.contains(expectedPart); 

        System.out.println("Validation Error Verified: " + actualError);
    }
}