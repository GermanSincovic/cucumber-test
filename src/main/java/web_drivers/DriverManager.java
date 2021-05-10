package web_drivers;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private final WebDriver driver;

    public DriverManager() {
        this.driver = WebDriverFactory.createWebDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void stop(){
        driver.close();
    }
}
