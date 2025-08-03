package rahulshettyacademy.Testcomponents;


import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobject.landingpage;

public class BaseTest {
	public WebDriver driver;
	public landingpage lpage;
	public WebDriver InitializeDriver() throws IOException
	{
		
		
		Properties prop =new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\PK\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
	 //prop.getProperty("browser");
	if(browserName.contains("chrome"))
	{
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		if(browserName.contains("headless")) {
		 options.addArguments("headless");
		}
	       driver =new ChromeDriver(options);
	       driver.manage().window().setSize(new Dimension(1440,900));
	}
	
	else if(browserName.equalsIgnoreCase("firefox"))
	{
		System.setProperty("webdriver.gecko.driver","C:\\Users\\PK\\Downloads\\geckodriver-v0.34.0-win64\\geckodriver.exe");
		driver= new FirefoxDriver();//Firefox
	}
	else if(browserName.equalsIgnoreCase("edge"))
	{
		//edge
	}
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       driver.manage().window().maximize();
       return driver;
	}
	@BeforeMethod(alwaysRun=true)
	
	public landingpage launchApplication() throws IOException
	{
		driver= InitializeDriver();
		lpage= new landingpage(driver);
	       lpage.Goto();
	       return lpage;
	}
	
	@AfterMethod(alwaysRun=true)
	
	public void tearDown()
	{
		driver.close();
		
	}
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException

	{
		TakesScreenshot ts =  (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
			File file =new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png" );
		FileUtils.copyFile(source,file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png" ;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//readJSon to string
	String jsonContent= FileUtils.readFileToString(new File(filePath),
			StandardCharsets.UTF_8);
	//String to hashmap jackson databind
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
	});
	
	return data;
	}
	
	}
	
	

