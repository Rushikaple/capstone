package Tests;

import java.util.Scanner;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.*;
import org.testng.annotations.*;

public class MedPurchase {
	
	WebDriver driver;
	Scanner sc;
	
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
		driver =new ChromeDriver();
	}
	
	public void passLogin(String eid, String pwd) {
		driver.get("http://localhost:8090/medicare/");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("username")).sendKeys("eid");
		driver.findElement(By.id("password")).sendKeys("pwd");
		driver.findElement(By.className("btn btn-primary")).click();
	}
	
	public void medpurchase() {
		driver.findElement(By.id("a_Antipyretics")).click();
		driver.findElement(By.cssSelector("#productListTable > tbody > tr.odd > td:nth-child(6) > a.btn.btn-primary")).click();
		driver.findElement(By.className("btn btn-success btn-block")).click();
		driver.findElement(By.className("btn btn-primary")).click();
		driver.findElement(By.className("btn btn-success btn-lg btn-block")).click();
	}
	
	
	@Test
	public void cpwd(){
		String eid,pwd;
		sc = new Scanner(System.in);
		eid = sc.next();
		pwd = sc.next();
		passLogin(eid, pwd);
		//login validation
		String logres = driver.findElement(By.id("dropdownMenu1")).getText();
		if(logres == "asdf asdf") Assert.assertEquals(logres, "asdf asdf");
		else Assert.assertEquals(logres, "asdf asdf");
		medpurchase();
		String res = driver.findElement(By.className("btn btn-lg btn-warning")).getText();
		if(res == "Continue Shopping") {
			Assert.assertEquals(res, "Continue Shopping");
			driver.findElement(By.className("btn btn-lg btn-warning")).click();
		}
		else{
			Assert.assertEquals(res, "Continue Shopping");
		}
	}

}
