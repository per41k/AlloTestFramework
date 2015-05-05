package Tests;

import Pages.CartPage;
import Pages.Header;
import Pages.User;
import helper.Helper;
import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class AddtoCartTest {
    private WebDriver driver;
    private static final String url="http://allo.ua/";
    private static Header header;
    private static User user;
    private static CartPage cartPage;
    private static final String filepath="C:\\Users\\User\\Documents\\Projects\\AlloTestFramework\\src\\test\\java\\Tests\\data\\categories.csv";
    
    @Before
    public void SetUp() {
        driver=Helper.Setup(url);
        header=new Header(driver);
        user=new User(driver);
        cartPage=new CartPage(driver);
    }
    
    @After
    public void tearDown() {        
	driver.close();
    }
    
    @Test
    public void testCatList() throws IOException, InterruptedException {
        int sum=0;
        List<String> orders=Helper.getListFromFile("orders");
        
        //user.login();
        for(String order:orders) {
            String query = order.substring(0, order.indexOf(':'));
            int count=Integer.parseInt(order.substring(order.indexOf(':')+1));        
            
            user.addToCart(count, query);        
            //sum=+count;
            Assert.assertEquals(query, cartPage.getLastProdName());
            Assert.assertEquals(count, cartPage.getLastCountProd());            
            driver.get(url);
        }
        //Assert.assertEquals(sum, header.getCartCount());
        
    }
}
