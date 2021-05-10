package web_drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {

    public static WebDriver createWebDriver() {
        WebDriver driver;
        String webdriver = System.getProperty("browser", "chrome");
        switch(webdriver) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                return driver;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(new ChromeOptions());
                driver.manage().window().maximize();
                return driver;
            default:
                throw new RuntimeException("Unsupported webdriver: " + webdriver);
        }

    }

}
