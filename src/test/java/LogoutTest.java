import PageObjectModel.LoginPage;
import PageObjectModel.MainPage;
import PageObjectModel.PersonalPage;
import Utils.UserClients;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static PageObjectModel.MainPage.PAGE_URL;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutTest {
    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private PersonalPage personalPage;
    private String accessToken;
    public static final String email = "Utybq11@ya.ru";
    public static final String password = "Utybq11@ya.ru";
    public static final String name = "Utybq11";

    @BeforeEach
    public void setUp() {
        UserClients.createUser(email, password, name);
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        personalPage = new PersonalPage(driver);
        mainPage.openMainPage();
        mainPage.clickPersonal();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("login"));
        loginPage.login(email, password);
        loginPage.clickEnterButton();
    }

    @AfterEach
    public void tearDown() {
        accessToken = UserClients.getAccessToken(email, password);
        if (accessToken != null) {
            UserClients.deleteUser(accessToken);
            System.out.println("Удаление пользователя с токеном: " + accessToken);
        } else {
            System.out.println("Токен отсутствует, удаление не выполнено");
        }
        driver.quit();
    }

    @Test
    @DisplayName("Выход из личного кабинета")
    @Description("Выход из личного кабинета")
    public void logoutPersonalAccount() {
        mainPage.clickPersonal();
        personalPage.clickLogout();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("login"));
        assertTrue(driver.getCurrentUrl().contentEquals(PAGE_URL + "login"));
    }
}
