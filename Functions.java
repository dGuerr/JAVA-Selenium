package Selenium;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

abstract class Functions {

	/**
	 * Get a random value from a dropdonw
	 * 
	 * @param driver
	 * @param xPath
	 * @return
	 */
	public String getValueFromDropDown(FirefoxDriver driver, String xPath)
	{
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
	public void deletDiv(FirefoxDriver driver, String div)
	{
		JavascriptExecutor js = null;
		if (driver instanceof JavascriptExecutor) {
			js = (JavascriptExecutor) driver;
		}

		js.executeScript(
				"var element = document.querySelector("+ div +"); element.parentNode.removeChild(element);");
	}
}
