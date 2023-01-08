package com.User;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UserSignUpTesting {
	String chromeDriverPath;
	WebDriver chromeDriver;
	
	@BeforeTest
	public void beforeTest() {
		chromeDriverPath = "/Users/abhinavbhardwaj/Downloads/chromedriver";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		chromeDriver = new ChromeDriver();
		
		System.out.println("Driver initialized...");
		
		chromeDriver.get("http://localhost:4200/AadharApp/citizens/signUp");
		chromeDriver.manage().window().fullscreen();
	}
	
  @Test(groups="FirstUser")
  public void testFirstUserSignUp() {
	  chromeDriver.findElement(By.id("userLoginFullname")).sendKeys("First");
	  chromeDriver.findElement(By.id("userLoginEmail")).sendKeys("first@email.com");
	  chromeDriver.findElement(By.id("userLoginMobile")).sendKeys("1111111111");
	  chromeDriver.findElement(By.id("userLoginGender")).sendKeys("Male");
	  chromeDriver.findElement(By.id("userLoginAddress")).sendKeys("Address1Address1");
	  chromeDriver.findElement(By.id("userLoginSubmit")).click();
	  
	  String currentURL = chromeDriver.getCurrentUrl();
	  
	  if(currentURL.equals("http://localhost:4200/AadharApp/citizens/dashboard")) {
		  System.out.println("User signed up successfully");
	  }
	  else {
		  System.out.println(currentURL);
	  }
  }
  
//  @Test(groups="SecondUser")
//  public void testSecondUserSignUp() {
//	  chromeDriver.findElement(By.id("userLoginFullname")).sendKeys("Second");
//	  chromeDriver.findElement(By.id("userLoginEmail")).sendKeys("second@email.com");
//	  chromeDriver.findElement(By.id("userLoginMobile")).sendKeys("2222222222");
//	  chromeDriver.findElement(By.id("userLoginGender")).sendKeys("Female");
//	  chromeDriver.findElement(By.id("userLoginAddress")).sendKeys("Address2Address2");
//	  chromeDriver.findElement(By.id("userLoginSubmit")).click();
//	  
//	  String currentURL = chromeDriver.getCurrentUrl();
//	  
//	  if(currentURL.equals("http://localhost:4200/AadharApp/citizens/dashboard")) {
//		  System.out.println("User signed up successfully");
//	  }
//	  else {
//		  System.out.println(currentURL);
//	  }
//  }
  
  
  
  @AfterTest
  public void afterTest() {
	  chromeDriver.close();
	  System.out.println("Test completed and drivers are closed.");
  }
}
