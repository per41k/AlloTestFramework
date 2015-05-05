package Tests.search;

import Pages.Header;
import Pages.SearchResultsPage;
import helper.Helper;
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
    private static SearchResultsPage searchResultPage;
        
    @Before
    public void SetUp() {
        driver=Helper.Setup(url);
        header=new Header(driver);
        searchResultPage = new SearchResultsPage(driver);
    }
    
    @After
    public void tearDown() {
	driver.close();
    }
    
    @Test
    public void searchTest() throws InterruptedException {
        
        String request="Apple";
        header.search(request);
        
        searchResultPage.sortBy("по имени");
        List<String> searchResault=searchResultPage.getRequestResults();        
        for (int i=0;i<searchResault.size()-1;i++) {
            assertTrue(searchResault.get(i).compareTo(searchResault.get(i+1))<1);                           
        }
        
        searchResultPage.sortBy("от дорогих к дешевым");
        List<Integer> prices=searchResultPage.getRequestResultsPrice();        
        for (int i=0;i<prices.size()-1;i++) {
            assertTrue(prices.get(i)>=prices.get(i+1));                           
        }
        
        searchResultPage.sortBy("от дешевых к дорогим");
        prices=searchResultPage.getRequestResultsPrice();        
        for (int i=0;i<prices.size()-1;i++) {
            assertTrue(prices.get(i)<=prices.get(i+1));                           
        }       
    }
}
