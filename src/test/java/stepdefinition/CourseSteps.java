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

import org.testng.Assert;

import java.util.List;

public class CourseSteps 
{
    
    ConfigReader cr = new ConfigReader();
    Properties prop = cr.init_prop();
    
    HomePage home = new HomePage(DriverFactory.getDriver());
    ResultsPage courses = new ResultsPage(DriverFactory.getDriver());
    ContactSalesPage contact = new ContactSalesPage(DriverFactory.getDriver());

    @Given("the user is on the Coursera homepage")
    public void the_user_is_on_the_coursera_homepage() 
    {
        String url = prop.getProperty("url");
        DriverFactory.getDriver().get(url);
    }

    @When("the user searches for {string}")
    public void the_user_searches_for(String courseName) 
    {
        home.searchCourse(courseName);
    }

    @When("the user filters by Beginner level and English language")
    public void the_user_filters_by_beginner_level_and_english_language() throws InterruptedException 
    {
        courses.filterBeginnerEnglish();
    }

    @Then("the system should display the top {int} course details")
    public void the_system_should_display_the_top_course_details(Integer count) 
    {
    	    courses.getAndPrintTopCourses(count);
    	    
        System.out.println("Extraction of top " + count + " courses completed.");
    }

    @When("the user navigates to the {string} section")
    public void the_user_navigates_to_the_section(String sectionName) throws InterruptedException 
    {
        home.clickForBusiness(); 
    }

    @When("fills the contact sales form with:")
    public void fills_the_contact_sales_form_with(DataTable dataTable) 
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
    public void submits_the_form() 
    {
        contact.submitForm();
    }

    @Then("an email validation error {string} should be displayed")
    public void an_email_validation_error_should_be_displayed(String expectedPart) 
    {
        String actualError = contact.getEmailErrorMessage();
        
        Assert.assertTrue(actualError.contains(expectedPart),"Error message mismatch!");

        System.out.println("Validation Error Verified: " + actualError);
    }
}