package Pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    
    private final WebDriver driver;
    
    @FindBys(@FindBy(xpath="//ul[@class='products-list']//a[2]"))
    protected List<WebElement> prod_pictures;
    
    @FindBys(@FindBy(xpath="//input[@class='input-text qty']"))
    protected List<WebElement> prod_count;       
    
    
    public OrderPage(WebDriver drv) {
        PageFactory.initElements(this.driver=drv, this);
    }
    
    public List<String> getBuyList() {
        List<String> buyList = new ArrayList<>();
        
        for(int i=0;i<prod_count.size();i++) {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(prod_count.get(prod_pictures.size()-1)));
            buyList.add(prod_pictures.get(i).getAttribute("title")+":"+prod_count.get(i).getAttribute("value"));
        }
        return buyList;
    }
}
