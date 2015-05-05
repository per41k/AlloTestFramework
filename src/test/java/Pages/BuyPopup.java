package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BuyPopup {
    
    private final WebDriver driver;
    
    @FindBy(xpath="//div[@class='lightbox cart-lightbox']")
    protected WebElement popup;
    
    @FindBy(id="qty")
    protected WebElement current_quan;    
    
    @FindBy(xpath="//span[@class='qty-link control-up']")
    protected WebElement quan_ikr_button;    
    
    @FindBy(xpath="//div[@class='lightbox cart-lightbox']//a[@class='lb-close icon']")
    protected WebElement close_button;
    
    public BuyPopup(WebDriver drv) {
        PageFactory.initElements(this.driver=drv, this);
    }
    
    public void closePopup() {
        close_button.click();
        //После закрытия попапа покупки не могу кликнуть по корзине, поэтому
        //перезагрузим страницу.
        driver.get(driver.getCurrentUrl());
    }
    
    public void setQuantily(int number) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(current_quan));
        int curr_quan=Integer.parseInt(current_quan.getAttribute("value"));        
        while (curr_quan!=number) {
            quan_ikr_button.click();
            wait.until(ExpectedConditions.visibilityOf(current_quan));
            curr_quan=Integer.parseInt(current_quan.getAttribute("value"));
        }        
        closePopup();
    }
}
