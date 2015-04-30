package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class User{
    
    private final WebDriver driver;
    private static Header header;
    private static LoginPage loginPage;
    private static SearchResaultsPage searchResaultsPage;
    private static BuyPopup buyPopup;
    private final String email="test-emailadress@yandex.ru";
    private final String password="test_pass123";
    private final String username="TestUser";
    
    public User(WebDriver drv) {  
        this.driver=drv;
        header = new Header(drv);
        loginPage = new LoginPage(drv);
        searchResaultsPage=new SearchResaultsPage(drv);
        buyPopup=new BuyPopup(drv);
    }
    
    public void login() {        
        header.goToLoginPage();
        loginPage.setEmail(email);
        loginPage.setPass(password);
        loginPage.clickLoginButton();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.
                textToBePresentInElement(header.login_pers_cab_button, username));
    }
    
    public void unlogin() {
        if("Выход".equals(header.reg_unlogin_button.getText()))
            header.reg_unlogin_button.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.
                textToBePresentInElement(header.login_pers_cab_button, "Войти"));
    }
    
    public void addToCart(int quantily, String prod_name) {
        header.search(prod_name);
        searchResaultsPage.clickBuy(prod_name);
        buyPopup.setQuantily(quantily);
        header.goToCart();        
    }
}
