package Tests;

import org.junit.Test;
import Pages.*;
import helper.*;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class FitstTest {
    
    private WebDriver driver;
    private static final String url="http://allo.ua/";
    private static Header header;
    private static LoginPage loginPage;
    private static User user;    
    
    @Before
    public void SetUp() {
        driver=Helper.Setup(url);
        header=new Header(driver);
        loginPage=new LoginPage(driver);
        user=new User(driver);
    }
    
    @After
    public void tearDown() {
	driver.close();
    }
    
    @Test
    public void testLogin() {        
        user.login();
        assertEquals("TestUser", header.getUsername());
        user.unlogin();
        assertEquals("Войти", header.getUsername());
    }    
}
