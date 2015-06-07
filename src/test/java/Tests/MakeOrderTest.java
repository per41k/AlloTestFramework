package Tests;

import Pages.CartPage;
import Pages.Header;
import Pages.OrderPage;
import Pages.User;
import helper.Helper;
import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;

public class MakeOrderTest {
    
    private WebDriver driver;
    private static final String url="http://allo.ua/";
    private static Header header;
    private static User user;
    private static CartPage cartPage;
    private static OrderPage orderPage;
    
    @Before
    public void setUp() {    
        driver=Helper.Setup(url);
        header=new Header(driver);
        user=new User(driver);
        cartPage=new CartPage(driver);
        orderPage=new OrderPage(driver);
    }
    
    @After
    public void tearDown() {
        driver.close();
    }
    
    @Test
    public void makeOrder() throws InterruptedException, IOException {
       List<String> orders=Helper.getListFromFile("orders");
       List<String> act_orders;
       
       for(String order:orders) {
            String query=order.substring(0, order.indexOf(':'));
            int count=Integer.parseInt(order.substring(order.indexOf(':')+1));
            user.addToCart(count, query);
       }
       
       header.goToCart();
       act_orders=cartPage.getBuyList();
       header.goToOrderPage();       
       assertEquals(act_orders, orderPage.getBuyList());
    }
}
