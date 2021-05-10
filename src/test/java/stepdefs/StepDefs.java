package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.CommonPage;
import models.MainSearchPage;
import models.Result;
import models.ResultsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import web_drivers.DriverManager;

import java.util.Locale;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class StepDefs {

    private final WebDriver driver = new DriverManager().getDriver();
    public MainSearchPage mainSearchPage = new MainSearchPage(driver);
    private String query;

    @Given("I am on MainSearchPage")
    public void openGoogleSearchPage() {
        mainSearchPage.open();
    }

    @When("I am searching {string}")
    public void makeSearchQuery(String query) {
        this.query = query;
        mainSearchPage.inputSearchQuery(query);
        mainSearchPage.submitQuery();
    }

    @And("I am clicking on first result")
    public void iAmClickingOnFirstResult() {
        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.getResult(0).getWebElement().findElement(By.tagName("A")).click();
    }

    @Then("I see title contains searched word")
    public void checkTitleOfFirstResultPage() {
        CommonPage commonPage = new CommonPage(driver);
        assertTrue(commonPage.getTitle().toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT)));
    }

    @Then("I see at least one result with domain contains searched word \\(first {int} pages)")
    public void iSeeAtLeastOneResultWithDomainContainsSearchedWordFirstPages(int pageCount) {
        Result matchedResult = null;
        int currentPageIndex = 1;
        while (currentPageIndex < pageCount) {
            ResultsPage resultsPage = new ResultsPage(driver);
            matchedResult = resultsPage.getResults().stream()
                    .filter(result -> result.getDomain().contains(query))
                    .findAny()
                    .orElse(null);
            if(matchedResult != null){
                break;
            } else {
                currentPageIndex++;
                resultsPage.clickNextPage();
            }
        }
        assertNotNull(matchedResult);
    }

    @After
    public void finish() {
        this.driver.quit();
    }
}
