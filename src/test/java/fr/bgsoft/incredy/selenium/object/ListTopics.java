package fr.bgsoft.incredy.selenium.object;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * List of topics.
 */
public class ListTopics {
	/** Root element. */
	private final WebElement rootElement;

	/** Element for tbody. */
	private final By byElemTBody = By.tagName("tbody");

	/** Element for tr. */
	private final By byElemTr = By.tagName("tr");

	/** Element for td. */
	private final By byElemTd = By.tagName("td");

	/** Button to edit topic */
	private final By byBtnEditTopic = By.name("edit-topic");

	/** Button to delete topic */
	private final By byBtnDeleteTopic = By.name("delete-topic");

	/** Button for next page. */
	private final By byButtonNextPage = By.className("p-paginator-next");

	/**
	 * Constructor.
	 *
	 * @param rootElement root element.
	 */
	public ListTopics(final WebElement rootElement) {
		this.rootElement = rootElement;
	}

	/**
	 * Gets the tbody element.
	 *
	 * @return tbody element.
	 */
	public WebElement getTBodyElement() {
		return rootElement.findElement(byElemTBody);
	}

	/**
	 * Gets the next page button.
	 *
	 * @return next page button.
	 */
	public WebElement getNextPageButton() {
		return rootElement.findElement(byButtonNextPage);
	}

	/**
	 * Gets the data row elements.
	 *
	 * @return data row elements.
	 */
	public List<WebElement> getDataRowElements() {
		final WebElement tbodyListTopics = getTBodyElement();
		return tbodyListTopics.findElements(byElemTr);
	}

	/**
	 * Checks if the topic title is in the data row.
	 *
	 * @param dataRowElement data row element.
	 * @param topicTitle     topic title to search.
	 * @return true if topic title found, else false.
	 */
	private boolean isTopicTitleInDataRow(final WebElement dataRowElement, final String topicTitle) {
		boolean found = false;
		final List<WebElement> tdElements = dataRowElement.findElements(byElemTd);
		if (tdElements.getFirst().getText().contains(topicTitle)) {
			found = true;
		}
		return found;
	}

	/**
	 * Gets the row containing a topic, or null if not found.
	 *
	 * @param topicTitle topic title to find.
	 * @return row containing the topic, or null if not found.
	 */
	private WebElement getRowContainingTopic(final String topicTitle) {
		WebElement rowWithTopic = null;

		// Will browse the pages of the table
		boolean hasNext = true;
		while (hasNext && (rowWithTopic == null)) {
			final List<WebElement> dataRows = getDataRowElements();
			for (final WebElement dataRow : dataRows) {
				if (isTopicTitleInDataRow(dataRow, topicTitle)) {
					rowWithTopic = dataRow;
					break;
				}
			}

			if (rowWithTopic == null) {
				final WebElement btnNextPage = getNextPageButton();
				if (btnNextPage.isDisplayed() && btnNextPage.isEnabled()) {
					btnNextPage.click();
				} else {
					hasNext = false;
				}
			}
		}
		return rowWithTopic;
	}

	/**
	 * Gets the edit button in a row of the topic list.
	 *
	 * @param topicRow row in the topic list.
	 * @return edit button if found.
	 */
	private WebElement getEditButtonInListTopicRow(final WebElement topicRow) {
		return topicRow.findElement(byBtnEditTopic);
	}

	/**
	 * Gets the delete button in a row of the topic list.
	 *
	 * @param topicRow row in the topic list.
	 * @return delete button if found.
	 */
	private WebElement getDeleteButtonInListTopicRow(final WebElement topicRow) {
		return topicRow.findElement(byBtnDeleteTopic);
	}

	/**
	 * Checks if the topic title can be found in the list of topics.
	 *
	 * @param topicTitle topic title to search.
	 * @return true if topic title found, else false.
	 */
	public boolean isTopicTitleInDataTable(final String topicTitle) {
		final WebElement topicRow = getRowContainingTopic(topicTitle);
		return topicRow != null;
	}

	/**
	 * Click on the row for a given topic title.
	 *
	 * @param topicTitle topic title.
	 */
	public void clickListTopicRow(final String topicTitle) {
		final WebElement topicRow = getRowContainingTopic(topicTitle);
		topicRow.click();
	}

	/**
	 * Click on the edit button on the given topic row.
	 *
	 * @param topicTitle topic title to edit.
	 */
	public void clickEditTopicButtonInListTopics(final String topicTitle) {
		final WebElement topicRow = getRowContainingTopic(topicTitle);
		final WebElement btnEditTopic = getEditButtonInListTopicRow(topicRow);
		btnEditTopic.click();
	}

	/**
	 * Click on the delete button on the given topic row.
	 *
	 * @param topicTitle topic title to edit.
	 */
	public void clickDeleteTopicButtonInListTopics(final String topicTitle) {
		final WebElement topicRow = getRowContainingTopic(topicTitle);
		final WebElement btnDeleteTopic = getDeleteButtonInListTopicRow(topicRow);
		btnDeleteTopic.click();
	}

}
