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

public class CheckoutPage extends AbstractComponents {
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver)
	{
		//initialization code
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
   //WebElement UserEmail = driver.findElement(By.id("userEmail"));
     //page factory can use to declare above
    @FindBy(css="[placeholder='Select Country']")
    WebElement Country;
    
    @FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
    WebElement selectcountry;
    
    @FindBy(css=".action__submit")
    WebElement submit;
    
    By results =By.cssSelector(".ta-results");
    
    //JavascriptExecutor js =(JavascriptExecutor)driver;
      //js.executeScript("window.scrollBy(0,600)");
       
    public void SelectCountry(String countryName)
    
    {
    	Actions a = new Actions(driver);
    	a.sendKeys(Country,countryName).build().perform();
    	//w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
    	waitForElementToAppear(results);
    	selectcountry.click();
    	
    }
    
    public ConfirmationPage submitOrder()
    {
    	submit.click();
    	return new ConfirmationPage(driver);
    	
    	
    }
    
    }


    
