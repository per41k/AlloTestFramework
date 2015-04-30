package Pages;

import java.util.List;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResaultsPage {
    
    @FindBys(@FindBy(xpath="//ul[@class='products-grid']//p[@class='product-name-container']/a"))
    private List<WebElement> productNames;
    
    @FindBy(xpath="//div[@class='toolbar-top']//span[@class='icon']")
    protected WebElement show_sorts;
    
    @FindBys(@FindBy(xpath="//span[@class='sum']"))
    public List<WebElement> productPrices;
    
    private final WebDriver driver;
    
    public SearchResaultsPage(WebDriver drv) {
	PageFactory.initElements(this.driver=drv, this);       
    }
    
    public void setCategories(String... cat) {        
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        
        for (String c : cat) {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='category-filter']/a[text()='"+c+"']")));
            driver.findElement(By.xpath("//li[@class='category-filter']/a[text()='"+c+"']")).click();
        }        
    }
    
    public void sortBy(String by) throws InterruptedException {
       Actions builder = new Actions(driver);
       builder.moveToElement(show_sorts).build().perform();
       driver.findElement(By.xpath("//div[@class='toolbar-top']//ul[@class='sort-list']//a[contains(text(),'"+by+"')]")).click();
       Thread.sleep(5000);
    }
    
    public List<String> getRequestResaults() {
        List<String> products = new ArrayList<>();                
        for(WebElement item : productNames)
            products.add(item.getAttribute("title"));
        return products;
    }
    
    public List<Integer> getRequestResaultsPrice() {        
        List<Integer> prices = new ArrayList<>();        
        for(WebElement item : productPrices) {            
            prices.add(Integer.parseInt(item.getText().replace(" ", "")));
        }            
        return prices;
    }
    
    public boolean contains(String substr, String str) {
        String[] arrstr=substr.split(" ");
        for (String s:arrstr) {
            if(!str.contains(s)) return false;
        }
        return true;
    }
    
    public void clickBuy(String title) {        
        WebElement button=driver.findElement(By.xpath("//a[@class='product-name' and @title='"+title+"']/../../..//button/span"));              
        button.click();        
    }
}
