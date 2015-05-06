package Pages;

import helper.Helper;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class User{
    
    private final WebDriver driver;
    private static Header header;
    private static LoginPage loginPage;
    private static SearchResultsPage searchResultsPage;
    private static BuyPopup buyPopup;
    private static CabPage cabPage;
    private final String email="test-emailadress@yandex.ru";
    private final String password="test_pass123";
    private final String username="TestUser";
    
    public User(WebDriver drv) {  
        this.driver=drv;
        header = new Header(drv);
        loginPage = new LoginPage(drv);
        searchResultsPage=new SearchResultsPage(drv);
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
    
    public void addToCart(int quantily, String prod_name) throws InterruptedException {
        header.search(prod_name);
        searchResultsPage.clickBuy(prod_name);
        buyPopup.setQuantily(quantily);
    }
    
    public void goToCabMenu(String... cab_menu) {
        header.goToCab();
        if(cab_menu.length==1)
            cabPage.goTo(cab_menu[0]);
        else if(cab_menu.length==2) {
            cabPage.goTo(cab_menu[0]);
            cabPage.goTo(cab_menu[1]);
        }
    }
    
    public void makeOrder(String from_file) throws IOException, InterruptedException {        
        List<String> orders=Helper.getListFromFile(from_file);
        
        for(String order:orders) {            
            String query = order.substring(0, order.indexOf(':'));
            int count=Integer.parseInt(order.substring(order.indexOf(':')+1));
            
            addToCart(count, query);            
        }
    }
}
