package rahulshettyacademy.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Arrays;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import rahulshettyacademy.pageobject.CartPage;
import rahulshettyacademy.pageobject.CheckoutPage;
import rahulshettyacademy.pageobject.ConfirmationPage;
import rahulshettyacademy.pageobject.ProductCatalogue;
import rahulshettyacademy.pageobject.landingpage;

public class ErrorValidations extends BaseTest{

@Test(groups = {"ErrorHandling"})
public void LoginErrorValidation() throws IOException {
		// TODO Auto-generated method stub
		//String productName="IPHONE 13 PRO";
       ProductCatalogue productCatalogue= lpage.loginApplication("purnenduchaturvedi1@gmail.com","Sachin10@");
     //div[@aria-label='Incorrect email or password.']
       //.ng-tns-c4-6.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
 AssertJUnit.assertEquals("Incorrect email or password.",lpage.getErrorMessage());
}
 @Test
 public void ProductErrorValidation() throws IOException {
 		// TODO Auto-generated method stub
 		String productName="IPHONE 13 PRO";
        ProductCatalogue productCatalogue= lpage.loginApplication("purnenduchaturvedi1@gmail.com","Sachin100@");
      //ProductCatalogue productCatalogue	= new  ProductCatalogue(driver);
      List<WebElement>products = productCatalogue.getProductList();
      productCatalogue.addProductToCart(productName);
      CartPage cartPage= productCatalogue.goToCartPage();
      Boolean match= cartPage.VerifyProductDisplay("IPHONE 33 PRO");
      Assert.assertFalse(match);
      
}
} 

