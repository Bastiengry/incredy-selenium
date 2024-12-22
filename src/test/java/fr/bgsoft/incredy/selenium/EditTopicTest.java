package fr.bgsoft.incredy.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import fr.bgsoft.incredy.selenium.object.EditTopicPage;
import fr.bgsoft.incredy.selenium.object.Header;
import fr.bgsoft.incredy.selenium.object.HomePage;
import fr.bgsoft.incredy.selenium.object.LoginPage;

public class EditTopicTest {
	WebDriver driver;

	@BeforeEach
	public void setup() {
		driver = new ChromeDriver();
	}

	@AfterEach
	public void teardown() {
		driver.quit();
	}

	@Test
	public void shouldSucceedToCreateTopicWhenConnected() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		final Header header = new Header(driver);
		final HomePage homePage = new HomePage(driver);
		final LoginPage loginPage = new LoginPage(driver);
		final EditTopicPage editTopicPage = new EditTopicPage(driver);

		// Open home page
		homePage.loadAndWaitComponentsLoaded();

		// Home page: click on Login button to access connection page.
		header.clickLoginButton();

		// At that moment, there is a redirection to login page.
		// Connection page: fill username
		loginPage.setUsername(Constants.USERNAME);

		// Connection page: fill password
		loginPage.setPassword(Constants.PASSWORD);

		// Connection page: click on Sign In button to connect and to be redirected to
		// home page.
		loginPage.clickSignInButton();

		// Home page: check user connected
		assertTrue(header.getUserName().contains(Constants.USERNAME));

		// Home page: click on "Add topic" button
		header.clickAddTopicButton();

		// Edit topic page: fill topic title
		final String topicTitle = "Topic title Selenium " + UUID.randomUUID();
		editTopicPage.setTopicTitle(topicTitle);

		// Edit topic page: fill topic text
		editTopicPage.setTopicText("Topic Text Selenium");

		// Edit topic page: click on "Validate" button
		editTopicPage.clickBtnValidate();

		// At that moment, there is a redirection to home page.
		// Checks that the topic is in the list of topics.
		assertTrue(homePage.getListTopics().isTopicTitleInDataTable(topicTitle));
	}

	@Test
	public void shouldBeNotPossibleToCreateTopicWhenNotConnected() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		final Header header = new Header(driver);
		final HomePage homePage = new HomePage(driver);

		// Open home page
		homePage.loadAndWaitComponentsLoaded();

		// Home page: click on "Add topic" button
		assertFalse(header.isDisplayedAddTopicButton());
	}

	@Test
	public void shouldFailToCreateTopicWhenNotConnected() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		final EditTopicPage editTopicPage = new EditTopicPage(driver);

		// Open edit topic page in ADD mode
		editTopicPage.load("add");

		// Waits a few seconds.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		assertFalse(editTopicPage.isDisplayedBtnValidate());
	}

	@Test
	public void shouldSucceedToEditTopicWhenConnected() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		final Header header = new Header(driver);
		final HomePage homePage = new HomePage(driver);
		final LoginPage loginPage = new LoginPage(driver);
		final EditTopicPage editTopicPage = new EditTopicPage(driver);

		// Open home page
		homePage.loadAndWaitComponentsLoaded();

		// Home page: click on Login button to access connection page.
		header.clickLoginButton();

		// At that moment, there is a redirection to login page.
		// Connection page: fill username
		loginPage.setUsername(Constants.USERNAME);

		// Connection page: fill password
		loginPage.setPassword(Constants.PASSWORD);

		// Connection page: click on Sign In button to connect and to be redirected to
		// home page.
		loginPage.clickSignInButton();

		// Home page: check user connected
		assertTrue(header.getUserName().contains(Constants.USERNAME));

		// Home page: click on "Add topic" button
		header.clickAddTopicButton();

		// Edit topic page: fill topic title
		final String topicTitle = "Topic title Selenium " + UUID.randomUUID();
		editTopicPage.setTopicTitle(topicTitle);

		// Edit topic page: fill topic text
		final String topicText = "Topic Text Selenium " + UUID.randomUUID();
		editTopicPage.setTopicText(topicText);

		// Edit topic page: click on "Validate" button
		editTopicPage.clickBtnValidate();

		// At that moment, there is a redirection to home page.
		// Home page: checks that the topic is in the list of topics.
		assertTrue(homePage.getListTopics().isTopicTitleInDataTable(topicTitle));

		// Home page: try to edit the topic.
		homePage.getListTopics().clickEditTopicButtonInListTopics(topicTitle);

		// Edit topic page: check that we opened the good page.
		assertEquals(topicTitle, editTopicPage.getTopicTitle());
		assertEquals(topicText, editTopicPage.getTopicText());

		// Edit topic page: change topic title
		final String changedTopicTitle = "Changed topic title Selenium " + UUID.randomUUID();
		editTopicPage.setTopicTitle(changedTopicTitle);

		// Edit topic page: change topic text
		final String changedTopicText = "Changed topic Text Selenium " + UUID.randomUUID();
		editTopicPage.setTopicText(changedTopicText);

		// Edit topic page: click on "Validate" button
		editTopicPage.clickBtnValidate();

		// Home page: go back to home page.
		homePage.loadAndWaitComponentsLoaded();

		// Waits for the topic to be updated.
		boolean result = false;
		for (int i = 0; (i < 5) && !result; i++) {
			try {
				Thread.sleep(Duration.ofSeconds(1));
			} catch (final InterruptedException e) {
			}
			result = homePage.getListTopics().isTopicTitleInDataTable(changedTopicTitle);
		}

		// Home page: checks that the old topic title does not appear in the datatable
		// anymore.
		assertFalse(homePage.getListTopics().isTopicTitleInDataTable(topicTitle));

		// Home page: checks that the changed topic is in the list of topics.
		assertTrue(homePage.getListTopics().isTopicTitleInDataTable(changedTopicTitle));
	}

	@Test
	public void shouldFailToEditTopicWhenNotConnected() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		final Header header = new Header(driver);
		final HomePage homePage = new HomePage(driver);
		final LoginPage loginPage = new LoginPage(driver);
		final EditTopicPage editTopicPage = new EditTopicPage(driver);

		// Open home page
		homePage.loadAndWaitComponentsLoaded();

		// Home page: click on Login button to access connection page.
		header.clickLoginButton();

		// At that moment, there is a redirection to login page.
		// Connection page: fill username
		loginPage.setUsername(Constants.USERNAME);

		// Connection page: fill password
		loginPage.setPassword(Constants.PASSWORD);

		// Connection page: click on Sign In button to connect and to be redirected to
		// home page.
		loginPage.clickSignInButton();

		// Home page: check user connected
		assertTrue(header.getUserName().contains(Constants.USERNAME));

		// Home page: click on "Add topic" button
		header.clickAddTopicButton();

		// Edit topic page: fill topic title
		final String topicTitle = "Topic title Selenium " + UUID.randomUUID();
		editTopicPage.setTopicTitle(topicTitle);

		// Edit topic page: fill topic text
		final String topicText = "Topic Text Selenium " + UUID.randomUUID();
		editTopicPage.setTopicText(topicText);

		// Edit topic page: click on "Validate" button
		editTopicPage.clickBtnValidate();

		// At that moment, there is a redirection to home page.
		// Home page: checks that the topic is in the list of topics.
		assertTrue(homePage.getListTopics().isTopicTitleInDataTable(topicTitle));

		// Home page: try to edit the topic.
		homePage.getListTopics().clickEditTopicButtonInListTopics(topicTitle);

		// Disconnects the user.
		header.clickBtnUserInfoExpandMenu();
		header.clickBtnDisconnect();

		assertFalse(editTopicPage.isDisplayedBtnValidate());
	}

	@Test
	public void shouldSucceedToDeleteTopicWhenConnectedAndConfirmDeletion() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		final Header header = new Header(driver);
		final HomePage homePage = new HomePage(driver);
		final LoginPage loginPage = new LoginPage(driver);
		final EditTopicPage editTopicPage = new EditTopicPage(driver);

		// Open home page
		homePage.loadAndWaitComponentsLoaded();

		// Home page: click on Login button to access connection page.
		header.clickLoginButton();

		// At that moment, there is a redirection to login page.
		// Connection page: fill username
		loginPage.setUsername(Constants.USERNAME);

		// Connection page: fill password
		loginPage.setPassword(Constants.PASSWORD);

		// Connection page: click on Sign In button to connect and to be redirected to
		// home page.
		loginPage.clickSignInButton();

		// Home page: check user connected
		assertTrue(header.getUserName().contains(Constants.USERNAME));

		// Home page: click on "Add topic" button
		header.clickAddTopicButton();

		// Edit topic page: fill topic title
		final String topicTitle = "Topic title Selenium " + UUID.randomUUID();
		editTopicPage.setTopicTitle(topicTitle);

		// Edit topic page: fill topic text
		final String topicText = "Topic Text Selenium " + UUID.randomUUID();
		editTopicPage.setTopicText(topicText);

		// Edit topic page: click on "Validate" button
		editTopicPage.clickBtnValidate();

		// At that moment, there is a redirection to home page.
		// Home page: checks that the topic is in the list of topics.
		assertTrue(homePage.getListTopics().isTopicTitleInDataTable(topicTitle));

		// Home page: try to delete the topic.
		homePage.getListTopics().clickDeleteTopicButtonInListTopics(topicTitle);

		// Confirms the deletion in confirmation dialog.
		homePage.confirmDeleteDialog();

		// Waits for the topic to be deleted.
		boolean result = true;
		for (int i = 0; (i < 5) && result; i++) {
			try {
				Thread.sleep(Duration.ofSeconds(1));
			} catch (final InterruptedException e) {
			}
			result = homePage.getListTopics().isTopicTitleInDataTable(topicTitle);
		}
		assertFalse(result);
	}

	@Test
	public void shouldFailToDeleteTopicWhenConnectedAndCancelDeletion() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		final Header header = new Header(driver);
		final HomePage homePage = new HomePage(driver);
		final LoginPage loginPage = new LoginPage(driver);
		final EditTopicPage editTopicPage = new EditTopicPage(driver);

		// Open home page
		homePage.loadAndWaitComponentsLoaded();

		// Home page: click on Login button to access connection page.
		header.clickLoginButton();

		// At that moment, there is a redirection to login page.
		// Connection page: fill username
		loginPage.setUsername(Constants.USERNAME);

		// Connection page: fill password
		loginPage.setPassword(Constants.PASSWORD);

		// Connection page: click on Sign In button to connect and to be redirected to
		// home page.
		loginPage.clickSignInButton();

		// Home page: check user connected
		assertTrue(header.getUserName().contains(Constants.USERNAME));

		// Home page: click on "Add topic" button
		header.clickAddTopicButton();

		// Edit topic page: fill topic title
		final String topicTitle = "Topic title Selenium " + UUID.randomUUID();
		editTopicPage.setTopicTitle(topicTitle);

		// Edit topic page: fill topic text
		final String topicText = "Topic Text Selenium " + UUID.randomUUID();
		editTopicPage.setTopicText(topicText);

		// Edit topic page: click on "Validate" button
		editTopicPage.clickBtnValidate();

		// At that moment, there is a redirection to home page.
		// Home page: checks that the topic is in the list of topics.
		assertTrue(homePage.getListTopics().isTopicTitleInDataTable(topicTitle));

		// Home page: try to delete the topic.
		homePage.getListTopics().clickDeleteTopicButtonInListTopics(topicTitle);

		// Cancels the deletion in confirmation dialog.
		homePage.cancelDeleteDialog();

		// Waits some seconds.
		try {
			Thread.sleep(Duration.ofSeconds(3));
		} catch (final InterruptedException e) {
		}

		// The element is still in the list of topic (because the deletion was
		// cancelled).
		assertTrue(homePage.getListTopics().isTopicTitleInDataTable(topicTitle));
	}
}
