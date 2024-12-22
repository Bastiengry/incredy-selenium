package fr.bgsoft.incredy.selenium.object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Login page.
 */
public class LoginPage extends BasePage {
	/** Input for username. */
	@FindBy(name = "username")
	private WebElement inputUserName;

	/** Input for password. */
	@FindBy(name = "password")
	private WebElement inputPassword;

	/** Button to connect. */
	@FindBy(name = "login")
	private WebElement btnSignIn;

	/**
	 * Constructor.
	 *
	 * @param driver web driver.
	 */
	public LoginPage(final WebDriver driver) {
		super(driver);

		// This call sets the WebElement fields.
		PageFactory.initElements(driver, this);
	}

	/**
	 * Sets the username.
	 *
	 * @param username username.
	 */
	public void setUsername(final String username) {
		inputUserName.clear();
		inputUserName.sendKeys(username);
	}

	/**
	 * Sets the password.
	 *
	 * @param password password.
	 */
	public void setPassword(final String password) {
		inputPassword.clear();
		inputPassword.sendKeys(password);
	}

	/**
	 * Clicks on "sign in" button.
	 */
	public void clickSignInButton() {
		btnSignIn.click();
	}
}
