package models;

import models.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;
import java.util.stream.Collectors;

public class ResultsPage extends BasePageObject {

    @FindBy(how = How.ID, id = "rso")
    private WebElement resultsContainer;

    private List<Result> results;

    @FindBy(how = How.ID, id = "pnnext")
    private WebElement buttonNextPage;

    @FindBy(how = How.ID, id = "pnprev")
    private WebElement buttonPrevPage;

    public ResultsPage(WebDriver driver) {
        super(driver);
        parseResults();
    }

    private void parseResults() {
        List<WebElement> webResults = resultsContainer.findElements(By.className("g"));
        results = webResults.stream()
                .map(we -> new Result()
                        .setWebElement(we)
                        .setTitleShort(we.findElement(By.tagName("h3")).getText())
                        .setDomain(we.findElement(By.tagName("cite")).getText().split("\\s")[0])
                        .setLink(we.findElement(By.tagName("a")).getAttribute("href"))
                )
                .collect(Collectors.toList());
    }

    public List<Result> getResults() {
        return results;
    }

    public Result getResult(int index) {
        return results.get(index);
    }

    public void clickNextPage(){
        buttonNextPage.click();
    }

    public void clickPrevPage(){
        buttonPrevPage.click();
    }
}
