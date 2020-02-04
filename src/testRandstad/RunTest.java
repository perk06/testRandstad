package testRandstad;

import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.After;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;
import testRandstad.WriteResults;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class RunTest {

	JavascriptExecutor js;
	private String strReq = "";
	private String strScenario = "";
	private WriteResults result = new WriteResults();
	private String strExpected = "";
	private String strActual = "";
	private static boolean VPOINT_YES = true;
	private static boolean VPOINT_NO = false;

	public WebDriver driver = new ChromeDriver();
	public WebElement element;

	@Before
	public void setUp() {

		js = (JavascriptExecutor) driver;
		driver.manage().window().setSize(new Dimension(1426, 805));
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void TC001_viewHomepage() throws Throwable {

		String strTestName = new Throwable().getStackTrace()[0].getMethodName();

		strScenario = "Scenario 001: Able to access the Randstad internal job position via main page";

		strReq = "GIVEN access to randstad.com.au website";
		driver.get("https://www.randstad.com.au/");
		strExpected = "";
		strActual = "";
		result.asertThis(strScenario, strReq, strTestName, strExpected, strActual, VPOINT_NO);

		strReq = "WHEN I scroll down the page";
		strExpected = "";
		strActual = "";
		result.asertThis(strScenario, strReq, strTestName, strExpected, strActual, VPOINT_NO);

		// Verification Point
		strReq = "THEN I should be able to see the text ‘join our team.";
		element = driver.findElement(By.xpath("//h2[contains(text(), 'join our team.')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(1000);
		strExpected = "join our team.";
		strActual = element.getText();
		result.asertThis(strScenario, strReq, strTestName, strExpected, strActual, VPOINT_YES);

		strReq = "WHEN I click on the ‘apply with us’ button";
		element = driver.findElement(By.linkText("apply with us"));
		element.click();
		Thread.sleep(1000);
		strExpected = "";
		strActual = "";
		result.asertThis(strScenario, strReq, strTestName, strExpected, strActual, VPOINT_NO);

		// Verification Point
		strReq = "THEN I should be able to see the text 'search our jobs online' ";
		boolean isFoundText = driver.getPageSource().contains("search our jobs online");
		strExpected = "search our jobs online";
		if (isFoundText) {
			strActual = "search our jobs online";
		} else {
			strActual = "";
		}
		result.asertThis(strScenario, strReq, strTestName, strExpected, strActual, VPOINT_YES);

	}

	@Test
	public void TC002_searchJobs() throws Throwable {
		String strTestName = new Throwable().getStackTrace()[0].getMethodName();

		strScenario = "Scenario 002: Searching for job roles";

		strReq = "GIVEN completion of Scenario 1";
		driver.get("https://www.randstad.com.au/join-our-team/job-search/");
		strExpected = "";
		strActual = "";
		result.asertThis(strScenario, strReq, strTestName, strExpected, strActual, VPOINT_NO);

		strExpected = "";
		strActual = "";
		result.asertThis(strScenario, strReq, strTestName, strExpected, strActual, VPOINT_NO);

		strReq = "AND webpage is unchanged";
		strExpected = "";
		strActual = "";
		result.asertThis(strScenario, strReq, strTestName, strExpected, strActual, VPOINT_NO);

		strReq = "WHEN I enter 'consultant' as job title in the 'what?' field";
		element = driver.findElement(By.xpath("//*[@id=\"ctl08_ctl05_WhatWhereWhatTextBox\"]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(1000);
		element.sendKeys("consultant");
		result.asertThis(strScenario, strReq, strTestName, strExpected, strActual, VPOINT_NO);

		strReq = "AND click on ‘search jobs";
		element = driver.findElement(By.id("ctl08_ctl05_WhatWhereSubmitLinkButton"));
		element.click();
		Thread.sleep(3000);
		strExpected = "";
		strActual = "";
		result.asertThis(strScenario, strReq, strTestName, strExpected, strActual, VPOINT_NO);

		// Verification Point
		strReq = "THEN I should be able to see all search results related to consultant job title ";
		String strTitle = driver.getTitle().toString();	
		strExpected = "consultant jobs";
		if (strTitle.contains(strExpected)) {
			strActual = "consultant jobs";
		}
		
		result.asertThis(strScenario, strReq, strTestName, strExpected, strActual, VPOINT_YES);

		
	}

}
