package rahulshettyacademy.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {
	WebDriver driver;
	
	public OrderPage(WebDriver driver)
	{
		//initialization code
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
   //WebElement UserEmail = driver.findElement(By.id("userEmail"));
     //page factory can use to declare above
    @FindBy(css="tr td:nth-child(3)")
    List <WebElement> productNames;
    
    @FindBy(css=".totalRow button")
   WebElement totProducts;
  
    
    public boolean VerifyOrderDisplay(String productName)
    
    {
    	Boolean match = productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
    	return match;
    }
    }


    
