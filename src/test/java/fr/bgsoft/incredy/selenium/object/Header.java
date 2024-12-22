package fr.bgsoft.incredy.selenium.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Header.
 */
public class Header {
	/** Web driver. */
	private final WebDriver driver;

	/** Text for user info. */
	private final By byUserInfo = By.id("user-info");

	/** Button to log in. */
	private final By byBtnLogin = By.id("btn-login");

	/** Button to add topic. */
	private final By byAddTopic = By.id("add-topic");

	/** Button to expand menu on user info. */
	private final By byBtnUserInfoExpandMenu = By.id("user-info-expand-menu");

	/** Button to log out. */
	private final By byBtnLogout = By.id("user-info_overlay_0");

	/**
	 * Constructor.
	 *
	 * @param driver web driver.
	 */
	public Header(final WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Clicks on "login" button.
	 */
	public void clickLoginButton() {
		final WebElement btnLogin = driver.findElement(byBtnLogin);
		btnLogin.click();
	}

	/**
	 * Gets the user name.
	 *
	 * @return user name.
	 */
	public String getUserName() {
		final WebElement txtUserInfo = driver.findElement(byUserInfo);
		return txtUserInfo.getText();
	}

	/**
	 * Clicks on "Add Topic" button.
	 */
	public void clickAddTopicButton() {
		final WebElement btnLogin = driver.findElement(byAddTopic);
		btnLogin.click();
	}

	public boolean isDisplayedAddTopicButton() {
		try {
			final WebElement btnAddTopicElement = driver.findElement(byAddTopic);
			return btnAddTopicElement.isDisplayed();
		} catch (final Exception e) {
			return false;
		}
	}

	/**
	 * Clicks on "User info" button.
	 */
	public void clickBtnUserInfo() {
		final WebElement btnUserInfo = driver.findElement(byUserInfo);
		btnUserInfo.click();
	}

	/**
	 * Clicks on "User info expand menu" button.
	 */
	public void clickBtnUserInfoExpandMenu() {
		final WebElement btnUserInfoExpandMenu = driver.findElement(byBtnUserInfoExpandMenu);
		btnUserInfoExpandMenu.click();
	}

	/**
	 * Clicks on "Disconnect" button.
	 */
	public void clickBtnDisconnect() {
		final WebElement btnLogout = driver.findElement(byBtnLogout);
		btnLogout.click();
	}
}
