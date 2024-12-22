package fr.bgsoft.incredy.selenium.object;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import fr.bgsoft.incredy.selenium.Constants;

/**
 * Home page object.
 */
public class HomePage extends BasePage {
	/** List of topics. */
	private final By byListTopics = By.id("list-topics");

	/** List of topics. */
	@FindBy(id = "list-topics")
	private WebElement listTopicsElement;

	/** Confirm delete dialog. */
	@FindBy(id = "delete-dialog")
	private WebElement confirmDeleteDialog;

	/** Yes button. */
	private final By byBtnYes = By.name("yes-button");

	/** No button. */
	private final By byBtnNo = By.name("no-button");

	/**
	 * Constructor.
	 *
	 * @param driver web driver.
	 */
	public HomePage(final WebDriver driver) {
		super(driver);

		// This call sets the WebElement fields.
		PageFactory.initElements(driver, this);
	}

	/**
	 * Loads the home page.
	 */
	public void load() {
		driver.get(Constants.APP_URL);
	}

	/**
	 * Waits for he component to be displayed.
	 */
	public void waitPageComponentsDisplayed() {
		// Waits for the page to be loaded (we wait for the list of topics to be
		// displayed).
		final Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(byListTopics));
	}

	/**
	 * Loads the home page (and waits for the components of the page to be loaded).
	 */
	public void loadAndWaitComponentsLoaded() {
		// Loads the page.
		load();

		// Waits for the components to be loaded on the page.
		waitPageComponentsDisplayed();
	}

	/**
	 * Gets the list of topics.
	 *
	 * @return list of topics.
	 */
	public ListTopics getListTopics() {
		return new ListTopics(listTopicsElement);
	}

	/**
	 * Click on the confirmation button in the delete dialog.
	 */
	public void confirmDeleteDialog() {
		confirmDeleteDialog.findElement(byBtnYes).click();
	}

	/**
	 * Click on the cancel button in the delete dialog.
	 */
	public void cancelDeleteDialog() {
		confirmDeleteDialog.findElement(byBtnNo).click();
	}
}
