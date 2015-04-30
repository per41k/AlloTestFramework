package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    
    private final WebDriver driver;
    
    @FindBy(id="send2")
    protected WebElement login_button;
    
    @FindBy(id="email")
    protected WebElement email_field;
    
    @FindBy(id="pass")
    protected WebElement pass_field;
    
    public LoginPage(WebDriver drv) {
	PageFactory.initElements(this.driver=drv, this);
    }   
    
    public String getButtonText() {
        return login_button.getText();
    }
    
    public void setEmail(String email) {
        email_field.sendKeys(email);
    }
    
    public void setPass(String pass) {
        pass_field.sendKeys(pass);
    }
    
    public void clickLoginButton() {
        login_button.click();
    }
    
}
