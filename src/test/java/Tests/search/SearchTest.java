package Tests.search;

import Pages.Header;
import Pages.SearchResultsPage;
import helper.Helper;
import java.io.IOException;
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
    public void searchTest() throws InterruptedException, IOException {
        List<String> searchResault;
        List<String> queries=Helper.getListFromFile("manufacturers");
                
        for(String query:queries) {
            header.writeQuery(query);
            searchResault=header.getRequestResultsFromList();
            for (String res : searchResault) {        
                assertTrue(Helper.contains(query, res));
            }
            header.clickSearch();                
            searchResault=searchResultPage.getRequestResults();        
                for (String res : searchResault) {        
            assertTrue(Helper.contains(query, res));
            }
        }        
    }
}
