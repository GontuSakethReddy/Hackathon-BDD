package Pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage 
{
    WebDriver driver;

    @FindBy(css = "div[class='css-guxf6x']")
    private WebElement homeButton;
    
    @FindBy(id = "search-autocomplete-input")
    private WebElement searchInput;

    @FindBy(linkText = "For Businesses")
    private WebElement forBusinessLink;

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    public HomePage(WebDriver driver) 
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchCourse(String courseName) 
    {
        searchInput.sendKeys(courseName + Keys.ENTER);
    }

    public void clickForBusiness() throws InterruptedException 
    {
    		
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        js.executeScript("arguments[0].click();", homeButton);
        
        wait.until(ExpectedConditions.elementToBeClickable(forBusinessLink)).click();
        
        //js.executeScript("arguments[0].click();", forBusinessLink);
    }
}