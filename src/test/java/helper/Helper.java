package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Helper {    
       
    public static WebDriver Setup(String url) {
        System.setProperty("webdriver.chrome.driver", "c:\\Users\\User\\IdeaProjects\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.get(url);
        return driver;
    }    
}
