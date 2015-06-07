package Tests;

import Pages.Header;
import Pages.YmapPage;
import helper.Helper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.openqa.selenium.WebDriver;


public class YmapTest {
    
    private WebDriver driver;
    private static final String url="http://allo.ua/";
    private static Header header;
    private static YmapPage ymapPage;
    
    @Before
    public void setUp() {        
        driver=Helper.Setup(url);
        header=new Header(driver);
        ymapPage=new YmapPage(driver);
        
    }
    
    @After
    public void tearDown() {
        driver.close();
    }
    
    @Test
    @Ignore
    public void mapTest() {
        header.clickHeaderMenu("Магазины");
        ymapPage.setRegion("Львовская область");
        ymapPage.setCity("Львов");
        ymapPage.clickFind();
        ymapPage.clickPoint();
        assertTrue(ymapPage.isPointInfoDisplayed());
    }
}
