package Pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactSalesPage 
{
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "div.css-9rsjro")
    WebElement contactSalesBtn;

    @FindBy(id = "FirstName")
    WebElement firstName;

    @FindBy(id = "LastName")
    WebElement lastName;

    @FindBy(id = "Email")
    WebElement email;

    @FindBy(css = "button[type='submit']")
    WebElement submitBtn;

    @FindBy(id = "ValidMsgEmail")
    WebElement emailError;

    public ContactSalesPage(WebDriver driver) 
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void fillForm(String fname, String lname, String mail) 
    {
    	
        wait.until(ExpectedConditions.elementToBeClickable(contactSalesBtn)).click();
        wait.until(ExpectedConditions.visibilityOf(firstName)).sendKeys(fname);
        lastName.sendKeys(lname);
        email.sendKeys(mail);
    }

    public void submitForm() 
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", submitBtn);
    }

    public String getEmailErrorMessage() 
    {
        return wait.until(ExpectedConditions.visibilityOf(emailError)).getText();
    }
}