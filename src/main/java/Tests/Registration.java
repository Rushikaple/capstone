package Tests;

import java.util.Scanner;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Registration {
	
	WebDriver driver;
	Scanner sc;
	
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
		driver =new ChromeDriver();
	}
	
	public void regPage1(String fname, String lname, String eid, String cno, String pwd, String pwd2) {
		driver.get("http://localhost:8090/medicare/");
		driver.findElement(By.id("signup")).click();
		System.out.println(driver.getTitle());
		driver.findElement(By.id("firstName")).sendKeys(fname);
		driver.findElement(By.id("lastName")).sendKeys(lname);
		driver.findElement(By.id("email")).sendKeys(eid);
		driver.findElement(By.id("contactNumber")).sendKeys(cno);
		driver.findElement(By.id("password")).sendKeys(pwd);
		driver.findElement(By.id("confirmPassword")).sendKeys(pwd2);
		driver.findElement(By.name("_eventId_billing")).click();		
	}
	
	public String regPage2(String add1, String add2, String city, String pincode, String state, String country) {
		System.out.println(driver.getTitle());
		driver.findElement(By.id("addressLineOne")).sendKeys(add1);
		driver.findElement(By.id("addressLineTwo")).sendKeys(add2);
		driver.findElement(By.id("city")).sendKeys(city);
		driver.findElement(By.id("postalCode")).sendKeys(pincode);
		driver.findElement(By.id("state")).sendKeys(state);
		driver.findElement(By.id("country")).sendKeys(country);
		driver.findElement(By.name("_eventId_confirm")).click();
		String element = driver.findElement(By.className("btn btn-lg btn-primary")).getText();
		return element;
	}
	
	@Test
	public void regTest1() {
		String fname, lname,eid, cno, pwd, pwd2;
		sc = new Scanner(System.in);
		fname = sc.next();
		lname = sc.next();
		eid = sc.next();
		cno = sc.next();
		pwd = sc.next();
		pwd2 = sc.next();
		regPage1(fname, lname, eid, cno, pwd, pwd2);
		String webe = driver.findElement(By.name("_eventId_confirm")).getText();
		if(webe == "Next - Confirm") {
			Assert.assertEquals(webe, "Next - Confirm");
		}
		else Assert.assertEquals(webe, "Next - Confirm");
	}
	
	@Test (dependsOnMethods = {"regTest1"})
	public void regTest2() {
		String add1, add2, city, pincode, state, country;
		sc = new Scanner(System.in);
		add1 = sc.next();
		add2 = sc.next();
		city = sc.next();
		pincode = sc.next();
		state = sc.next();
		country = sc.next();
		String res = regPage2(add1, add2, city, pincode, state, country);
		if(res == "Confirm") {
			Assert.assertEquals(res, "Confirm");
			driver.findElement(By.className("btn btn-lg btn-primary")).click();
		}
		else Assert.assertEquals(res, "Confirm");
		
	}
}
