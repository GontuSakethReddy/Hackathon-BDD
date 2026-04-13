package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class ResultsPage 
{
    WebDriver driver;

    @FindBy(css = "button[data-testid='filter-dropdown-productDifficultyLevel']")
    WebElement levelDropDown;

    @FindBy(xpath = "//span[text()='Beginner']")
    WebElement beginnerOption;

    @FindBy(css = "button[data-testid='filter-dropdown-language']")
    WebElement langDropDown;

    @FindBy(xpath = "//span[text()='English']")
    WebElement englishOption;

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

        // Final wait for results to populate
        wait.until(ExpectedConditions.visibilityOfAllElements(courseCards));

	
		List<WebElement> allCourses = driver.findElements(By.cssSelector("li.cds-grid-item"));

		
		for (int i = 0; i < Math.min(2, allCourses.size()); i++) 
		{
		    WebElement course = allCourses.get(i);
		    
		    try 
		    {
		        // Extract Title
		        String title = course.findElement(By.cssSelector("h3.cds-CommonCard-title")).getText();
		        
		        // Extract Rating (Locating the span inside the rating meter)
		        String rating = course.findElement(By.cssSelector("div.cds-RatingStat-meter span.css-4s48ix")).getText();
		        
		        // Extract Metadata (Level and Duration)
		        String details = course.findElement(By.className("cds-CommonCard-metadata")).getText();

		        // Print Output
		        System.out.println("COURSE #" + (i + 1));
		        System.out.println("Title:   " + title);
		        System.out.println("Rating:  " + rating);
		        System.out.println("Details: " + details);
		        System.out.println("--------------------------------------");
		        
		    }
		    catch (NoSuchElementException e) 
		    {
		        System.out.println("Could not extract full data for course index: " + i);
		    }
		}
    }

//    public void printTopCourses(int count) {
//        for (int i = 0; i < Math.min(count, courseCards.size()); i++) {
//            WebElement card = courseCards.get(i);
//            String title = card.findElement(By.cssSelector("h3.cds-CommonCard-title")).getText();
//            System.out.println("COURSE #" + (i + 1) + ": " + title);
//        }
//    }
}