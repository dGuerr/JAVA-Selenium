package Selenium;

import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

@SuppressWarnings("unused")
public class SeleniumTestOC extends Functions {

	private static WebDriver driver = Choix_Browser("Firefox");
	private WebElement element;
	private List<String> config = readFile("C://login.txt");
	private User user = new User(config.get(0), config.get(1), config.get(2));

	public User getUser() {
		return user;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		SeleniumTestOC.driver = driver;
	}

	public WebElement getElement() {
		return element;
	}

	public void setElement(WebElement element) {
		this.element = element;
	}

	@BeforeClass
	public static void openBrowser() {
		 SeleniumTestOC.driver = new FirefoxDriver();
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws InterruptedException {
		User david = this.user;

		openOC();
		connect(david);
		openMenu();
	}

	public static void openOC() {
		System.out.println("Starting test " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		driver.get("https://openclassrooms.com/fr/");

		System.out.println("Ending test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
	}

	public static void connect(User user) throws InterruptedException {
		System.out.println("Starting test " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		clickOnItem(driver, "/html/body/header/div[2]/div/div/div[1]/div[2]/div/div[3]/a[2]");

		TimeUnit.SECONDS.sleep(5);
		JavascriptExecutor js = null;
		if (driver instanceof JavascriptExecutor) {
			js = (JavascriptExecutor) driver;
		}

		js.executeScript("document.getElementById('_username').value = '" + user.getId() + "';");
		js.executeScript("document.getElementById('_password').value = '" + user.getPassword() + "';");
		js.executeScript("document.getElementById(\"login\").click();");

		System.out.println("Ending test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
	}

	public static void openMenu() {
		System.out.println("Starting test " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		clickOnItem(driver, "/html/body/div[3]/main/section[1]/div/div/div[1]");
	}

	@AfterClass
	public static void closeBrowser() {
		 driver.quit();
	}
}