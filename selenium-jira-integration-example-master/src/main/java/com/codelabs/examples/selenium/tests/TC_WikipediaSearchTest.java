package com.codelabs.examples.selenium.tests;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.codelabs.examples.annotations.ScriptMetaData;

public class TC_WikipediaSearchTest {
	private WebDriver driver;
	private String baseUrl;

	
	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		
		File chromedriverExecutable = new File("drivers"+File.separator+"chromedriver");
		
		System.setProperty("webdriver.chrome.driver", chromedriverExecutable.getAbsolutePath());
		driver = new ChromeDriver();
		baseUrl = "https://www.wikipedia.org/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@ScriptMetaData(productionReady=true)
	@Test
	public void testCase() throws Exception {
		driver.get(baseUrl);

		String searchText = "Selenium";

		driver.findElement(By.id("searchInput")).clear();
		driver.findElement(By.id("searchInput")).sendKeys(searchText);
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='" + searchText + "'])[6]/following::i[1]"))
				.click();

		// Injecting a failure here to start the Jira issue creation. 
		assertEquals(driver.findElement(By.id("firstHeading")).getText(), "HP ALM");
		
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
	}


	
}
