package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{
    WebDriver driver;

    @FindBy(css = "div[class='css-guxf6x']")
    WebElement homeButton;
    
    @FindBy(id = "search-autocomplete-input")
    WebElement searchInput;

    @FindBy(xpath = "//*[@id=\"rendered-content\"]/div/span/span/div/nav/div[1]/div/div/div/div/div/ul/li[2]/a/span/span")
    WebElement forBusinessLink;

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
        
        Thread.sleep(3000);
        
        js.executeScript("arguments[0].click();", forBusinessLink);
    }
}