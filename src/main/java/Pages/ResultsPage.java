package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class ResultsPage 
{
    WebDriver driver;

    @FindBy(css = "button[data-testid='filter-dropdown-productDifficultyLevel']")
    private WebElement levelDropDown;

    @FindBy(xpath = "//span[text()='Beginner']")
    private WebElement beginnerOption;

    @FindBy(css = "button[data-testid='filter-dropdown-language']")
    private WebElement langDropDown;

    @FindBy(xpath = "//span[text()='English']")
    private WebElement englishOption;

    @FindBy(css = "button.cds-button-primary")
    List<WebElement> applyButtons; 

    @FindBy(css = "li.cds-grid-item")
    List<WebElement> courseCards;

    public ResultsPage(WebDriver driver) 
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void filterBeginnerEnglish() throws InterruptedException 
    {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Filter Level: Beginner
        wait.until(ExpectedConditions.elementToBeClickable(levelDropDown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(beginnerOption)).click();
        applyButtons.get(0).click();
        

        // Filter Language: English
        wait.until(ExpectedConditions.elementToBeClickable(langDropDown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(englishOption)).click();
        applyButtons.get(0).click();
        
		}
    

	public void getAndPrintTopCourses(Integer count) 
	{
		List<WebElement> allCourses = driver.findElements(By.cssSelector("li.cds-grid-item"));

	    for (int i = 0; i < Math.min(count, allCourses.size()); i++) 
	    {
	        WebElement course = allCourses.get(i);
	        try 
	        {
	            String title = course.findElement(By.cssSelector("h3.cds-CommonCard-title")).getText();
	            String rating = course.findElement(By.cssSelector("div.cds-RatingStat-meter span.css-4s48ix")).getText();
	            String details = course.findElement(By.className("cds-CommonCard-metadata")).getText();

	            System.out.println("COURSE #" + (i + 1));
	            System.out.println("Title:   " + title);
	            System.out.println("Rating:  " + rating);
	            System.out.println("Details: " + details);
	            System.out.println("--------------------------------------");
	        } catch (NoSuchElementException e) {
	            System.out.println("Could not extract data for course index: " + i);
	        }
	    }
		
		
		
	}


}