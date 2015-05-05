package helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Helper {    
       
    public static WebDriver Setup(String url) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Documents\\Projects\\AlloTestFramework\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.get(url);
        return driver;
    }
    
    public static boolean contains(String substr, String str) {
        String[] arrstr=substr.split(" ");
        for (String s:arrstr) {
            if(!str.toLowerCase().contains(s.toLowerCase())) return false;
        }
        return true;
    }
    
    public static List<String> getListFromFile(String filepath) throws IOException {        
        File data = new File("C:\\Users\\User\\Documents\\Projects\\AlloTestFramework\\src\\test\\java\\Tests\\data\\"+filepath+".csv");
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
}
