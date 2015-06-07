package Tests;

import Pages.Header;
import helper.Helper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class ChekCatMenuTest {
    private WebDriver driver;
    private static final String url="http://allo.ua/";
    private static Header header;
    
    @Before
    public void SetUp() {
        driver=Helper.Setup(url);
        header=new Header(driver);
    }
    
    @After
    public void tearDown() {
	driver.close();
    }
    
    @Test
    public void testCatList() throws IOException {  
        
        List<String> item_menu_names=new ArrayList<>();
        String[] main_menu= {
            "Телефоны",
            "Планшеты",
            "Ноутбуки",
            "Телевизоры",
            "Фото",
            "Бытовая техника",
            "Спорт и туризм",
            "Дом, сад, ремонт",
            "Акции и скидки"
        };
                
        for(String s: main_menu)
            item_menu_names.addAll(header.getAllItemsFromMenu(s));
        assertThat(item_menu_names, is(Helper.getListFromFile("categories")));
    }
}
