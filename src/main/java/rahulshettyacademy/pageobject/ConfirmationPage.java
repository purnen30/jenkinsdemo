package rahulshettyacademy.pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponents;
public class ConfirmationPage extends AbstractComponents {
	WebDriver driver;
	
	
	public ConfirmationPage(WebDriver driver)
	{
		//initialization code
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	 @FindBy(css=".hero-primary")
	    WebElement confirm;
	    
	 public String confirmationmessage()
	 {
		 
		 return confirm.getText();
	 }
	 
	 
}
