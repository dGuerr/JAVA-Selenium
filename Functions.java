package Selenium;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

abstract class Functions {

	/**
	 * Get a random value from a dropdown
	 * 
	 * @param driver
	 * @param xPath
	 * @return
	 */
	public static String getValueFromDropDown(WebDriver driver, String xPath) {
		List<WebElement> se = new Select(driver.findElement(By.xpath(xPath))).getOptions();
		ArrayList<String> al = new ArrayList<String>();

		for (int i = 0; i < se.size(); i++) {
			String dvalues = se.get(i).getText();
			al.add(dvalues);
		}

		Random foo = new Random();

		int randomNumber = foo.nextInt(al.size() - 0) + 0;
		String random = al.get(randomNumber);

		return random;
	}

	/**
	 * Delet a div from a webpage using javascript
	 * 
	 * @param driver
	 * @param div
	 */
	public static void deletDiv(WebDriver driver, String div) {
		JavascriptExecutor js = null;
		if (driver instanceof JavascriptExecutor) {
			js = (JavascriptExecutor) driver;
		}

		js.executeScript("var element = document.querySelector(" + div + "); element.parentNode.removeChild(element);");
	}

	/**
	 * 
	 * @param browser
	 * @return
	 */
	public static WebDriver Choix_Browser(String browser) {
		WebDriver Browser_choisi = null;
		if (browser == "Firefox") {
			Browser_choisi = Browser_firefox();
		}
		if (browser == "InternetExplorer") {
			Browser_IE();
		}
		if (browser == "Chrome") {
			Browser_chrome();
		}
		return Browser_choisi;
	}

	/**
	 * 
	 * @return driver
	 */
	public static WebDriver Browser_firefox() {
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	/**
	 * 
	 * @return driver
	 */
	@SuppressWarnings("deprecation")
	public static WebDriver Browser_IE() {
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability("ignoreProtectedModeSettings", true);
		capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		System.setProperty("webdriver.ie.driver", "C:\\Python37\\Scripts\\IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver(capabilities);
		return driver;
	}

	/**
	 * 
	 * @return driver
	 */
	public static WebDriver Browser_chrome() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		ChromeOptions chromeOpt = new ChromeOptions();
		chromeOpt.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		WebDriver driver = new ChromeDriver(chromeOpt);
		return driver;
	}

	public static void clickOnItem(WebDriver driver, String xPath) {
		driver.findElement(By.xpath("" + xPath + "")).click();
	}
	
	/**
	 * Open and read a file, and return the lines in the file as a list of Strings.
	 */
	public static List<String> readFile(String filename) {
		List<String> records = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = reader.readLine()) != null) {
				records.add(line);
			}
			reader.close();
			return records;
		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", filename);
			e.printStackTrace();
			return null;
		}
	}
}
