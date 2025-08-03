package rahulshettyacademy.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class landingpage extends AbstractComponents {
	WebDriver driver;
	
	public landingpage(WebDriver driver)
	{
		//initialization code
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
   //WebElement UserEmail = driver.findElement(By.id("userEmail"));
     //page factory can use to declare above
    @FindBy(id="userEmail")
    WebElement UserEmail;
    
    @FindBy(id="userPassword")
    WebElement PasswordEle;
    
    @FindBy(id="login")
    WebElement submit;
    
    @FindBy(css="[class*='flyInOut']")
    WebElement errorMessage;
    
    public ProductCatalogue loginApplication(String email,String password)
    
    {
    	UserEmail.sendKeys(email);
    	PasswordEle.sendKeys(password);
    	submit.click();
    	ProductCatalogue productCatalogue	= new  ProductCatalogue(driver);
    	return productCatalogue;
    }
    
    public String getErrorMessage()
    
    {
    	waitForWebElementToAppear(errorMessage);
    	return errorMessage.getText();
    	
    }
    
    public void Goto()
    {
    	
    	driver.get("https://rahulshettyacademy.com/client");
    }
    
    }


    
