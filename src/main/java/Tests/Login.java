package Tests;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Login {
	
	WebDriver driver;
	Scanner sc;
	@BeforeTest
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
		driver =new ChromeDriver();
	}
	
	public WebElement passLogin(String eid, String pwd) {
		driver.get("http://localhost:8090/medicare/");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("username")).sendKeys("eid");
		driver.findElement(By.id("password")).sendKeys("pwd");
		driver.findElement(By.className("btn btn-primary")).click();
		WebElement element =  driver.findElement(By.id("dropdownMenu1"));
		return element;
	}
	
	@Test
	public void logTest() {
		String eid,pwd;
		sc = new Scanner(System.in);
		eid = sc.next();
		pwd = sc.next();
		WebElement res = passLogin(eid, pwd);
		WebElement act = driver.findElement(By.id("dropdownMenu1"));
		if(res == act) Assert.assertEquals(res, act);
		else Assert.assertEquals(res, act);
	}

}
