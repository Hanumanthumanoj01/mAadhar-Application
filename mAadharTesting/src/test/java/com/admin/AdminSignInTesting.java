package com.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AdminSignInTesting {
	String chromeDriverPath;
	WebDriver chromeDriver;
	
	@BeforeTest
	public void beforeTest() {
		chromeDriverPath = "/Users/abhinavbhardwaj/Downloads/chromedriver";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		chromeDriver = new ChromeDriver();
		
		System.out.println("Driver initialized...");
		
		chromeDriver.get("http://localhost:4200/AadharApp/admin/logIn");
		chromeDriver.manage().window().fullscreen();
	}
	
  @Test
  public void testAdminSignIn() {
	  chromeDriver.findElement(By.id("adminLogin")).sendKeys("987123987123");
	  chromeDriver.findElement(By.id("adminPassword")).sendKeys("Abcd@1234");
	  chromeDriver.findElement(By.id("adminLoginSubmit")).click();
	  
	  String currentURL = chromeDriver.getCurrentUrl();
	  
	  if(currentURL.equals("http://localhost:4200/AadharApp/admin/dashboard")) {
		  System.out.println("Admin Logged in successfully");
		  chromeDriver.findElement(By.id("adminSignout")).click();
	  }
	  else {
		  System.out.println(currentURL);
	  }
	  
  }
  
  @AfterTest
  public void afterTest() {
	  chromeDriver.close();
	  System.out.println("Test completed and drivers are closed.");
  }
}
