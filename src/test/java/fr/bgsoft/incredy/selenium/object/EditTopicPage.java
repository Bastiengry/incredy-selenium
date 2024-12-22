package fr.bgsoft.incredy.selenium.object;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import fr.bgsoft.incredy.selenium.Constants;

/**
 * Edit topic page.
 */
public class EditTopicPage extends BasePage {
	/** By for input for topic title. */
	private final By byInputTopicTitle = By.name("title");

	/** Input for topic title. */
	@FindBy(name = "title")
	private WebElement inputTopicTitle;

	/** Input for topic text. */
	@FindBy(className = "ql-editor")
	private WebElement inputTopicText;

	/** Validate button. */
	@FindBy(id = "btn-validate")
	private WebElement btnValidate;

	/**
	 * Constructor.
	 *
	 * @param driver web driver.
	 */
	public EditTopicPage(final WebDriver driver) {
		super(driver);

		// This call sets the WebElement fields.
		PageFactory.initElements(driver, this);
	}

	/**
	 * Loads the edit topic page.
	 *
	 * @param topicIdOrAdd topic ID or "add".
	 */
	public void load(final String topicIdOrAdd) {
		driver.get(Constants.APP_URL + "edittopic/" + topicIdOrAdd);

	}

	/**
	 * Waits for he component to be displayed.
	 */
	public void waitPageComponentsDisplayed() {
		// Waits for the page to be loaded (we wait for the input to fill the title of
		// the topic to be displayed).
		final Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(byInputTopicTitle));
	}

	/**
	 * Sets the topic title.
	 *
	 * @param title topic title.
	 */
	public void setTopicTitle(final String title) {
		inputTopicTitle.clear();
		inputTopicTitle.sendKeys(title);
	}

	/**
	 * Gets the topic title.
	 *
	 * @return topic title.
	 */
	public String getTopicTitle() {
		return inputTopicTitle.getDomAttribute("value");
	}

	/**
	 * Sets the topic text.
	 *
	 * @param title topic text.
	 */
	public void setTopicText(final String text) {
		inputTopicText.clear();
		inputTopicText.sendKeys(text);
	}

	/**
	 * Gets the topic text.
	 *
	 * @return topic text.
	 */
	public String getTopicText() {
		return inputTopicText.getText();
	}

	/**
	 * Clicks on validate button.
	 */
	public void clickBtnValidate() {
		btnValidate.click();
	}

	/**
	 * Checks if the validate button is displayed.
	 *
	 * @return true is the button is displayed, else false.
	 */
	public boolean isDisplayedBtnValidate() {
		try {
			return btnValidate.isDisplayed();
		} catch (final NoSuchElementException e) {
			return false;
		}
	}
}
