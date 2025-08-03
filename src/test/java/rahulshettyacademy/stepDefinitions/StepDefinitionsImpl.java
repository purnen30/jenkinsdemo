package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.Testcomponents.BaseTest;
import rahulshettyacademy.pageobject.CartPage;
import rahulshettyacademy.pageobject.CheckoutPage;
import rahulshettyacademy.pageobject.ConfirmationPage;
import rahulshettyacademy.pageobject.ProductCatalogue;
import rahulshettyacademy.pageobject.landingpage;

public class StepDefinitionsImpl extends BaseTest {
	public landingpage lPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmpage;
	@Given("I landed on Ecommerce Page")
	public void I_Landed_on_Ecommerce_Page() throws IOException
	{
		//code
		lPage=launchApplication();
	}
	
	 @Given ("^Logged in with username (.+) and password (.+)$")
	 public void logged_in_with_username_and_password(String username,String password)
	 {
		  productCatalogue= lpage.loginApplication(username,password);
		}
	 
	 @When("^I add product (.+) to Cart$")
	 public void i_add_product_to_cart(String productName) throws InterruptedException
	 {
		 
		  List<WebElement> products = productCatalogue.getProductList();
		     productCatalogue.addProductToCart(productName); 
	 }
	 
	 @When("^Checkout (.+) and submit the order$")
	 public void Checkout_productName_and_submit_the_order(String productName)
	 {
		 CartPage cartPage= productCatalogue.goToCartPage();
		  Boolean match= cartPage.VerifyProductDisplay(productName);
		   AssertJUnit.assertTrue(true);
		   CheckoutPage checkoutpage = cartPage.Gotocheckout();
		   checkoutpage.SelectCountry("India");
		   JavascriptExecutor js =(JavascriptExecutor)driver;

		   js.executeScript("window.scrollBy(0,100000000000)");
		   confirmpage=checkoutpage.submitOrder();
}
	 
	 @Then("{string} message is displayed on ConfirmationPage")
	 public void message_displayed_confirmationPage(String string)
	 {
		   String Message =confirmpage.confirmationmessage();
		   AssertJUnit.assertTrue(Message.equalsIgnoreCase(string));
		   driver.close();
		  
	 }
	 
	 @Then("{string} message is displayed")
	    public void incorrectEmailOrPasswordMessageIsDisplayed(String str) {
	        // Check if the error message is the one expected
		 AssertJUnit.assertEquals(str,lpage.getErrorMessage());	    
		 driver.close();
		  }
}
