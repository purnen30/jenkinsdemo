package rahulshettyacademy.tests;

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

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobject.landingpage;

public class standalonetest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productName="IPHONE 13 PRO";
       WebDriverManager.chromedriver().setup();
       WebDriver driver =new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       driver.manage().window().maximize();
       driver.get("https://rahulshettyacademy.com/client");
       landingpage lpage= new landingpage(driver);
       driver.findElement(By.id("userEmail")).sendKeys("purnenduchaturvedi@gmail.com");
       driver.findElement(By.id("userPassword")).sendKeys("Sachin100@");
       driver.findElement(By.id("login")).click();
       WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(4));
       w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products =driver.findElements(By.cssSelector(".mb-3"));
        //My own code without java streams
/*for(int i=0;i<products.size();i++)
{

String name=products.get(i).getText();
if(name.contains("IPHONE 13 PRO"))
{
	driver.findElements(By.xpath("//button[@class='btn w-10 rounded']")).get(i).click();
}
}*/
        
        //Using java streams by rahul shetty
        WebElement prod =products.stream().filter(product-> 
        product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        
prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();


w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));//products added confirmation message
w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
Assert.assertTrue(match);
driver.findElement(By.cssSelector(".totalRow button")).click();

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


Actions a = new Actions(driver);
a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"India").build().perform();
w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//js.executeScript("window.scrollBy(0,600)");

driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
JavascriptExecutor js =(JavascriptExecutor)driver;

js.executeScript("window.scrollBy(0,10000)");
driver.findElement(By.cssSelector(".action__submit")).click();
String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));


}
} 

