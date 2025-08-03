package rahulshettyacademy.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		//initialization code
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
   //WebElement UserEmail = driver.findElement(By.id("userEmail"));
     //page factory can use to declare above
    @FindBy(css=".cartSection h3")
    List <WebElement> cartProducts;
    
    @FindBy(css=".totalRow button")
   WebElement totProducts;
  
    
    public boolean VerifyProductDisplay(String productName)
    
    {
    	Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
    	return match;
    }
    
    public CheckoutPage Gotocheckout()
    {
    	
    totProducts.click();
    return new CheckoutPage(driver);
    }
    
    }


    
