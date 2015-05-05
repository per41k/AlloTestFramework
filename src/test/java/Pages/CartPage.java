package Pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    
    private final WebDriver driver;
    
    @FindBys(@FindBy(xpath="//table[@id='cartListTable']//td[2]//a"))
    protected List<WebElement> prod_pictures;
    
    @FindBys(@FindBy(xpath="//span[@class='count']"))
    protected List<WebElement> prod_count;       
    
    
    public CartPage(WebDriver drv) {
        PageFactory.initElements(this.driver=drv, this);
    }
    
    public String getLastProdName() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(prod_pictures.get(prod_pictures.size()-1)));
       return prod_pictures.get(prod_pictures.size()-1).getAttribute("title");
    }
    
    public int getLastCountProd() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(prod_count.get(prod_pictures.size()-1)));
        return Integer.parseInt(prod_count.get(prod_pictures.size()-1).getText());
    }
}
