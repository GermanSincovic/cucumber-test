package models;

import models.base.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainSearchPage extends BasePageObject {

    private final String URL = "https://www.google.com/";

    @FindBy(xpath = "//*[@name='q']")
    private WebElement searchField;

    @FindBy(xpath = "//*[@name='btnK']")
    private WebElement searchButton;

    public MainSearchPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.navigate().to(URL);
    }

    public void inputSearchQuery(String query) {
        waitUntilVisible(searchField, 1);
        searchField.sendKeys(query);
    }

    public void submitQuery() {
        waitUntilVisible(searchButton, 3);
        searchButton.click();
    }

}
