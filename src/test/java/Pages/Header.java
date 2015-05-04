package Pages;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Header {    
    
    private final WebDriver driver;
    
    @FindBys(@FindBy(xpath="//div[@id='menuBlocks']//td[@class='sales' or @class='links']//a"))
    private List<WebElement> cat_names;
    
    @FindBy(id="search")
    protected WebElement search_field;
    
    @FindBy(xpath="//button[@class='button']")
    protected WebElement search_button;
    
    @FindBy(xpath="//div[@class='icon_cart_big']")
    protected WebElement cart_pic;
    
    @FindBy(id="first")
    protected WebElement login_pers_cab_button;
   
    @FindBy(id="last")
    protected WebElement reg_button;
    
    @FindBy(id="last")
    protected WebElement reg_unlogin_button;
    
    private String filepath="C:\\Users\\User\\Documents\\Projects\\AlloTestFramework\\src\\test\\java\\Tests\\data\\categories.csv";
    
    public Header(WebDriver drv) {          
	PageFactory.initElements(this.driver=drv, this);
    }    
    
    public void goToLoginPage() {
        if(login_pers_cab_button.getText().equals("Войти"))
            login_pers_cab_button.click();        
    }
    
    public void goToRegPage() {
        if(reg_button.getText().equals("Регистрация"))
            reg_button.click();        
    } 
    
    public String getUsername() {
        return login_pers_cab_button.getText();
    }
    
    public void goTo(String mainMenu) {
        driver.findElement(By.xpath("//ul[@class='header-links']"
                + "//a[text()='"+mainMenu+"']")).click();
    }
    
    public void search(String request) {
        search_field.sendKeys(request);
        search_button.click();
    }
    
    public List<String> getRequestResaults() {
        List<String> products = new ArrayList<>();        
        for (WebElement cat_name : cat_names) {                       
            products.add(cat_name.getText());
        }        
        return products;
    }    
    
    public File createDataCats(String[] menus) throws IOException {        
        File data = new File(filepath);        
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(data), "UTF8"));
        PrintWriter pw = new PrintWriter(out);
        
        List<String> all_items=new ArrayList<>();
        for(String s:menus) {           
            all_items.addAll(getAllItemsFromMenu(s));
        }
        
        for (int i=0;i<all_items.size();i++) {            
            if(i<all_items.size()-1) pw.println(all_items.get(i));
            else pw.print(all_items.get(i));
        }
        pw.close();
        return data;
    }
    
    public boolean compareCats(List<String> cats, String filepath) throws IOException {        
        return cats.equals(getListFromFile(filepath));        
    }

    public List<String> getListFromFile(String filepath) throws IOException {        
        File data = new File(filepath);
        List<String> records = new ArrayList<>();
        String record;
        BufferedReader file = new BufferedReader(new InputStreamReader(
                new FileInputStream(data), "UTF8"));
        while ((record=file.readLine())!=null) {            
            records.add(record);
        }
        file.close();
        return records;            
    }   
    
    public List<String> getAllItemsFromMenu(String name_menu) {   
        
        WebElement menu=driver.findElement(By.xpath("//table[@id='nav']//a/span[text()='"+name_menu+"']"));
        Actions builder = new Actions(driver);
        builder.moveToElement(menu).build().perform();
        
        switch (name_menu) {
        case "Телефоны" : name_menu="menu_mobile_phone";
                break;
            case "Планшеты" : name_menu="menu_planshety";
                break;
            case "Ноутбуки" : name_menu="menu_computers";
                break;
            case "Телевизоры" : name_menu="menu_tv";
                break;
            case "Фото" : name_menu="menu_photo";
                break;
            case "Бытовая техника" : name_menu="menu_home";
                break;
            case "Спорт и туризм" : name_menu="sport_turizm";
                break;
            case "Дом, сад, ремонт" : name_menu="menu_dom_sad_remont";
                break;
            case "Акции и скидки" : name_menu="menu_sale";
                break;
        }         
        
        List<WebElement> items = driver.findElements(By.xpath("//div[@id='"+name_menu+"']//td[@class='sales' or @class='links']//a"));
        List<String> item_names=new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(driver, 10);        
        for(WebElement item:items) {
            wait.until(ExpectedConditions.visibilityOf(item));            
            if(item.isEnabled())
                item_names.add(item.getText());
        }       
        return item_names;
    }
    
    public void goToCart() throws WebDriverException {
        driver.get("http://allo.ua/checkout/cart/");
          
    }
    
    public void goToCab () {        
        login_pers_cab_button.click();
    }
}
