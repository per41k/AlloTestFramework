package Tests.search;

import Pages.Header;
import Pages.SearchResaultsPage;
import Pages.User;
import helper.Helper;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class SearchTest {
    
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
        String request="Apple Plus 128GB";
        header.search(request);
        searchResaultPage.sortBy("от дорогих к дешевым");        
        List<String> searchResault=searchResaultPage.getRequestResaults();        
        for (String res : searchResault) {        
            assertTrue(searchResaultPage.contains(request, res));
        }
    }
}
