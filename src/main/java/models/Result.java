package models;

import lombok.Data;
import lombok.experimental.Accessors;
import org.openqa.selenium.WebElement;

@Data
@Accessors(chain = true)
public class Result {

    private WebElement webElement;
    private String titleShort;
    private String domain;
    private String link;

}
