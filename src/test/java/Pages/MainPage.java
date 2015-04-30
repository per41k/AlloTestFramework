package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

public class MainPage {
    
    private final WebDriver driver;    
    
    @FindBy(id="buy-button-catalog-2060218240")
    protected WebElement buy_Lenovo_S850_Dark_Blue_button;
    
    public MainPage(WebDriver drv) {          
	PageFactory.initElements(this.driver=drv, this);
    }
    
    
}
