package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    
    private final WebDriver driver;
    
    @FindBy(id="firstname")
    protected WebElement name_field;
    
    @FindBy(id="email_address")
    protected WebElement email_field;
    
    @FindBy(id="password")
    protected WebElement password_field;
    
    @FindBy(xpath = "//button[@class='button btn-yellow next']")
    protected WebElement continue_button;
     
    public RegistrationPage(WebDriver drv) {          
	PageFactory.initElements(this.driver=drv, this);
        
    }
    
    public void setName(String email) {
        name_field.sendKeys(email);
    }
    
    public void setEmail(String email) {
        email_field.sendKeys(email);
    }
    
    public void setPass(String pass) {
        password_field.sendKeys(pass);
    }
    
    public void clickContinueButton() {
        continue_button.click();
    }
    
    public void registrate(String name, String email, String pass) {
        setName(name);
        setEmail(email);
        setPass(pass);
        clickContinueButton();
    }
}
