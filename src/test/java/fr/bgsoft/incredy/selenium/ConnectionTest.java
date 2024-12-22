package fr.bgsoft.incredy.selenium;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import fr.bgsoft.incredy.selenium.object.Header;
import fr.bgsoft.incredy.selenium.object.HomePage;
import fr.bgsoft.incredy.selenium.object.LoginPage;

public class ConnectionTest {
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
	public void shouldSucceedToConnect() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		final Header header = new Header(driver);
		final HomePage homePage = new HomePage(driver);
		final LoginPage loginPage = new LoginPage(driver);

		// Open home page
		homePage.loadAndWaitComponentsLoaded();

		// Home page: click on Login button to access connection page.
		header.clickLoginButton();

		// Connection page: fill username
		loginPage.setUsername(Constants.USERNAME);

		// Connection page: fill password
		loginPage.setPassword(Constants.PASSWORD);

		// Connection page : click on Sign In button to connect and to be redirected to
		// home page.
		loginPage.clickSignInButton();

		// Home page: check user connected
		assertTrue(header.getUserName().contains(Constants.USERNAME));
	}
}
