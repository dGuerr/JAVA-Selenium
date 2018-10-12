package Selenium;

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
public class SeleniumTestTati extends Functions {

	private static WebDriver driver = Choix_Browser("Firefox");
	private WebElement element;

	public SeleniumTestTati() {

	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		SeleniumTestTati.driver = driver;
	}


	public WebElement getElement() {
		return element;
	}


	public void setElement(WebElement element) {
		this.element = element;
	}

	@BeforeClass
	public static void openBrowser() {
		SeleniumTestTati.driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws InterruptedException {
		openTati();
		openNewsletter();
		fillingNewletter();
	}

	public static void openTati() {
		System.out.println("Starting test " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		driver.get("http://www.tati.fr");

		System.out.println("Ending test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
	}

	public static void openNewsletter() {
		System.out.println("Starting test " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		deletDiv(driver, "'#cookies_block_footer'");

		driver.findElement(By.xpath("//html/body/div[3]/div[5]/a[2]")).click();

		System.out.println("Ending test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
	}

	public static void fillingNewletter() throws InterruptedException {
		System.out.println("Starting test " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		// civilite
		int number = (int) (Math.random() * 3);

		if (number == 1) {
			driver.findElement(By.xpath("//*[@id='inputnewsletterSexeM']")).click();
		} else if (number == 2) {
			driver.findElement(By.xpath("//*[@id='inputnewsletterSexeMme']")).click();
		} else {
			driver.findElement(By.xpath("//*[@id='inputnewsletterSexeMlle']")).click();
		}

		// nom
		String nom = new NameGenerator().getName();
		driver.findElement(By.xpath("//*[@id=\"newsletterNom\"]")).sendKeys(nom);

		// prenom
		String prenom = new NameGenerator().getName();
		driver.findElement(By.xpath("//*[@id=\"newsletterPrenom\"]")).sendKeys(prenom);

		// jour
		driver.findElement(By.xpath("//*[@id=\"newsletterDatenaisJ\"]"))
				.sendKeys(getValueFromDropDown(driver, "//*[@id=\"newsletterDatenaisJ\"]"));

		// mois
		driver.findElement(By.xpath("//*[@id=\"newsletterDatenaisM\"]"))
				.sendKeys(getValueFromDropDown(driver, "//*[@id=\"newsletterDatenaisM\"]"));

		// annee
		driver.findElement(By.xpath("//*[@id=\"newsletterDatenaisA\"]"))
				.sendKeys(getValueFromDropDown(driver, "//*[@id=\"newsletterDatenaisA\"]"));

		// e-mail
		driver.findElement(By.xpath("//*[@id=\"newsletterMail\"]")).sendKeys(nom + "." + prenom + "@gmail.com");

		// e-mail verif
		driver.findElement(By.xpath("//*[@id=\"newsletterMailv\"]")).sendKeys(nom + "." + prenom + "@gmail.com");

		deletDiv(driver, "'#barre_bas_fixe'");

		// valid
		driver.findElement(By.xpath("//*[@id=\"btn_news\"]")).click();

		System.out.println("Ending test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
	}

	@AfterClass
	public static void closeBrowser() {
		driver.quit();
	}
}