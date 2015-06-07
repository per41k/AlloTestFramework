package Pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class CabPage {
    
    @FindBys(@FindBy(xpath="//div[@class='block-content']/ul//a"))
    private List<WebElement> cat_names;
    
    private final WebDriver driver;
     
    public CabPage(WebDriver drv) {
        PageFactory.initElements(this.driver=drv, this);
    }
     
    public void goTo(String cab_menu) {
        driver.findElement(By.xpath("//div[@class='block-content']/ul//a[text()='"+cab_menu+"']")).click();
    }
}
