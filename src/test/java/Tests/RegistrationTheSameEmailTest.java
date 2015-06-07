package Tests;

import Pages.Header;
import Pages.LoginPage;
import Pages.RegistrationPage;
import helper.Helper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class RegistrationTheSameEmailTest {
    
    private WebDriver driver;
    private static final String url="http://allo.ua/";
    private static Header header;
    private static LoginPage loginPage;
    private static RegistrationPage registrationPage;
    private final String name="Вася";
    private final String email="test-emailadress@yandex.ru";
    private final String password="test_pass123";

    @Before
    public void setUp() {
        driver=Helper.Setup(url);
        header=new Header(driver);
        loginPage=new LoginPage(driver);
        registrationPage=new RegistrationPage(driver);
    }
    
    @Test
    public void RegistrationTheSameEmailTest() {        
        header.goToRegPage();
        registrationPage.registrate(name, email, password);       
        Assert.assertEquals(
                "Пользователь с таким e-mail уже зарегистрирован.\n" +
                "Пожалуйста, для входа введите пароль",
                loginPage.getErrMess());
    }
    
    @After
    public void tearDown() {
        driver.close();
    }
}
