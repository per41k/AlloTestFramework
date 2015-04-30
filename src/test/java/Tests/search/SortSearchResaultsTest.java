package Tests.search;

import Pages.Header;
import Pages.SearchResaultsPage;
import Pages.User;
import com.google.common.collect.Ordering;
import helper.Helper;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class SortSearchResaultsTest {
    
    private WebDriver driver;
    private static final String url="http://allo.ua/";
    private static Header header;
    private static SearchResaultsPage searchResaultPage;
        
    @Before
    public void SetUp() {
        driver=Helper.Setup(url);
        header=new Header(driver);
        searchResaultPage = new SearchResaultsPage(driver);
    }
    
    @After
    public void tearDown() {
	driver.close();
    }
    
    @Test
    public void searchTest() throws InterruptedException {
        String request="Apple";
        header.search(request);
        searchResaultPage.sortBy("от дорогих к дешевым");
        List<Integer> searchResault=searchResaultPage.getRequestResaultsPrice();
        
        for (int i=0;i<searchResault.size()-1;i++) {
            assertTrue(searchResault.get(i)>=searchResault.get(i+1));                           
        }        
    }
}
