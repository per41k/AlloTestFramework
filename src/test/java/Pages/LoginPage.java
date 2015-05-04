package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    
    private final WebDriver driver;
    
    @FindBy(id="send2")
    protected WebElement login_button;   
    
    @FindBy(id="email")
    protected WebElement email_field;
    
    @FindBy(xpath="//span[@id='error-message-login']//span")
    protected WebElement err_email_text;
    
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
    
    public void clickForm(String form) {
        driver.findElement(By.xpath("//span[text()='"+form+"']")).click();
    }
    
    public String getErrMess() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(err_email_text));
        return err_email_text.getText();
    }
}
