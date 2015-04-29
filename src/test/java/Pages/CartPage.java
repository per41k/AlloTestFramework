package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    
    private final WebDriver driver;
    
    @FindBy(xpath="//table[@id='cartListTable']//p[@class='last'][1]/a")
    protected WebElement prod_name;
    
    @FindBy(xpath="//table[@id='cartListTable']//span[@class='count']")
    protected WebElement prod_count;  
     
     //table[@id='cartListTable']//span[@class='count']
    
    public CartPage(WebDriver drv) {
        PageFactory.initElements(this.driver=drv, this);
    }
    
    public String getProdName() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(prod_name));
       return prod_name.getText();
    }
    
    public int getCountProd() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(prod_count));
        return Integer.parseInt(prod_count.getText());
    }
}
