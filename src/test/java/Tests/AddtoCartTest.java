package Tests;

import Pages.CartPage;
import Pages.Header;
import Pages.User;
import helper.Helper;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
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
    public void testCatList() {
        String query="Lenovo S850 Dark Blue";
        int count=6;
        
        user.login();
        user.addToCart(6, query);        
        Assert.assertEquals(query, cartPage.getProdName());
        Assert.assertEquals(count, cartPage.getCountProd());
    }
}
