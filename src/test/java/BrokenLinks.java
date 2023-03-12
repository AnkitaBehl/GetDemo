import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLinks {

	public static void main(String[] args) throws MalformedURLException, IOException {
		// TODO Auto-generated method stub
		WebDriver driver;
		 WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		
		 driver.manage().window().maximize();
		 driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		 List<WebElement> links = driver.findElements(By.cssSelector("li[class='gf-li'] a"));
		 // using SoftAssert
		 SoftAssert a = new SoftAssert();
		 //enhancement loop
		 for(WebElement link : links)
		 {
			 String url =  link.getAttribute("href");
			 
			 HttpURLConnection conn = (HttpURLConnection)new URL(url).openConnection();
			 conn.setRequestMethod("HEAD");
			 conn.connect();
			 int respCode = conn.getResponseCode();
			 System.out.println(respCode); 
			 //using soft assert
			 a.assertTrue(respCode<400, "The link with Text"+link.getText()+" is broken with code"+respCode);
		 }
		 a.assertAll();
		
	}

}
