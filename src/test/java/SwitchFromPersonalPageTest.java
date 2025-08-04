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

public class SwitchFromPersonalPageTest {
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
    @DisplayName("Переход в личный кабинет")
    @Description("Переход в личный кабинет")
    public void clickPersonalPage() {
        mainPage.clickOpenAccount();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("login"));
    }

    @Test
    @DisplayName("Переход из личного кабинета в Конструктор")
    @Description("Переход из личного кабинета в Конструктор")
    public void clickConstructerFromPersonalPage() {
        mainPage.clickOpenAccount();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("login"));
        loginPage.login(email, password);
        loginPage.clickEnterButton();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(PAGE_URL));
        assertTrue(driver.getCurrentUrl().contains(PAGE_URL));
        assertTrue(mainPage.getOrderButtonText().contains("Оформить заказ"));
        mainPage.clickPersonal();
        personalPage.clickConstructor();
        assertTrue(driver.getCurrentUrl().contentEquals(PAGE_URL));
    }

    @Test
    @DisplayName("Переход из личного кабинета на Логотип")
    @Description("Переход из личного кабинета на Логотип")
    public void clickLogoFromPersonalPage() {
        mainPage.clickOpenAccount();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("login"));
        loginPage.login(email, password);
        loginPage.clickEnterButton();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(PAGE_URL));
        assertTrue(driver.getCurrentUrl().contains(PAGE_URL));
        assertTrue(mainPage.getOrderButtonText().contains("Оформить заказ"));
        mainPage.clickPersonal();
        personalPage.clickLogo();
        assertTrue(driver.getCurrentUrl().contentEquals(PAGE_URL));
    }
}
