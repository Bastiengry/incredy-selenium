package fr.bgsoft.incredy.selenium.object;

import org.openqa.selenium.WebDriver;

/**
 * Base for page object.
 */
public abstract class BasePage {
	/**
	 * Web driver.
	 */
	protected WebDriver driver;

	/**
	 * Constructor.
	 *
	 * @param driver web driver.
	 */
	public BasePage(final WebDriver driver) {
		this.driver = driver;
	}
}
