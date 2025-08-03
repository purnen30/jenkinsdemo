package rahulshettyacademy.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.Testcomponents.BaseTest;
import rahulshettyacademy.Testcomponents.Retry;
import rahulshettyacademy.pageobject.CartPage;
import rahulshettyacademy.pageobject.CheckoutPage;
import rahulshettyacademy.pageobject.ConfirmationPage;
import rahulshettyacademy.pageobject.OrderPage;
import rahulshettyacademy.pageobject.ProductCatalogue;
import rahulshettyacademy.pageobject.landingpage;

public class submitordertest extends BaseTest{
	//String productName="IPHONE 13 PRO";
@Test(dataProvider="getData",groups= {"Purchase"})
public void submitOrder(HashMap<String,String> input) throws IOException {
		// TODO Auto-generated method stub
		
       ProductCatalogue productCatalogue= lpage.loginApplication(input.get("email"),input.get("password"));
     //ProductCatalogue productCatalogue	= new  ProductCatalogue(driver);
     List<WebElement>products = productCatalogue.getProductList();
     productCatalogue.addProductToCart(input.get("productName"));
     CartPage cartPage= productCatalogue.goToCartPage();
        //My own code without java streams
/*for(int i=0;i<products.size();i++)
{

String name=products.get(i).getText();
if(name.contains("IPHONE 13 PRO"))
{
	driver.findElements(By.xpath("//button[@class='btn w-10 rounded']")).get(i).click();
}
}*/
     /*//My own code
     driver.findElement(By.cssSelector("[placeholder*='Country']")).sendKeys("Ind");
     List<WebElement> options = driver.findElements(By.cssSelector(".ng-star-inserted"));

     for(WebElement option :options)
     {
     	
     	if(option.getText().equalsIgnoreCase("India"))
     	{
     		option.click();
     		break;
     	}
     }
     JavascriptExecutor js =(JavascriptExecutor)driver;

     js.executeScript("window.scrollBy(0,600)");
     driver.findElement(By.cssSelector(".action__submit")).click();*/ // My own code
        
        //Using java streams by rahul shetty
    Boolean match= cartPage.VerifyProductDisplay(input.get("productName"));
   AssertJUnit.assertTrue(true);
   CheckoutPage checkoutpage = cartPage.Gotocheckout();
   checkoutpage.SelectCountry("India");
   JavascriptExecutor js =(JavascriptExecutor)driver;

   js.executeScript("window.scrollBy(0,100000000000)");
   ConfirmationPage confirmpage=checkoutpage.submitOrder();
   String Message =confirmpage.confirmationmessage();
   AssertJUnit.assertTrue(Message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

}

@Test(dataProvider="getData",dependsOnMethods= {"submitOrder"})
public void orderHistoryTest(HashMap<String,String> input)
{
	//to verify is product displays in my orders page
	ProductCatalogue productCatalogue= lpage.loginApplication(input.get("email"),input.get("password"));
	
	OrderPage ordersPage = productCatalogue.goToOrdersPage();
	
	Assert.assertTrue(ordersPage.VerifyOrderDisplay(input.get("productName")));
}



@DataProvider

public Object[][] getData() throws IOException
{
	List<HashMap<String, String>> data = getJsonDataToMap("C:\\Users\\PK\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
return new Object [][] {{data.get(0)}, {data.get(1)} };

} 
}
/*@DataProvider

public Object[][] getData()
{
	HashMap<String,String> map = new HashMap<String,String>();
	map.put("email", "purnenduchaturvedi@gmail.com");
	map.put("password", "Sachin100@");
	map.put("productName","ZARA COAT 3");
	
	HashMap<String,String> map1 = new HashMap<String,String>();
	map1.put("email", "purnenduchaturvedi1@gmail.com");
	map1.put("password", "Sachin100@");
	map1.put("productName","IPHONE 13 PRO");
return new Object [][] {{map}, {map1} };

} 
}*/
/*@DataProvider

public Object[][] getData()
{
	
return new Object [][] {{"purnenduchaturvedi@gmail.com","Sachin100@","ZARA COAT 3"}, {"purnenduchaturvedi1@gmail.com","Sachin100@","IPHONE 13 PRO"} };

} 
}*/

