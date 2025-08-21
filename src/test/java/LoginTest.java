import PageObjectModel.LoginPage;
import PageObjectModel.MainPage;
import PageObjectModel.RecoveryPasswordPage;
import PageObjectModel.RegistrationPage;
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

public class LoginTest {
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RecoveryPasswordPage recoveryPasswordPage;
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
        registrationPage = new RegistrationPage(driver);
        recoveryPasswordPage = new RecoveryPasswordPage(driver);
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
    @DisplayName("Вход в личный кабинет")
    @Description("Входв личный кабинет на главной странице")
    public void loginMainPage() {
        mainPage.clickOpenAccount();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("login"));
        loginPage.login(email, password);
        loginPage.clickEnterButton();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(PAGE_URL));
        assertTrue(driver.getCurrentUrl().contains(PAGE_URL));
        assertTrue(mainPage.getOrderButtonText().contains("Оформить заказ"));
    }

    @Test
    @DisplayName("Вход в личный кабинет")
    @Description("Вход через личный кабинет")
    public void loginPersonalAccount() {
        mainPage.clickPersonal();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("login"));
        loginPage.login(email, password);
        loginPage.clickEnterButton();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(PAGE_URL));
        assertTrue(driver.getCurrentUrl().contains(PAGE_URL));
        assertTrue(mainPage.getOrderButtonText().contains("Оформить заказ"));
    }

    @Test
    @DisplayName("Вход в личный кабинет")
    @Description("Вход в форме регистрации")
    public void loginRegistrationForm() {
        registrationPage.openRegistrationPage();
        registrationPage.clickEnterButton();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("login"));
        loginPage.login(email, password);
        loginPage.clickEnterButton();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(PAGE_URL));
        assertTrue(driver.getCurrentUrl().contains(PAGE_URL));
        assertTrue(mainPage.getOrderButtonText().contains("Оформить заказ"));
    }

    @Test
    @DisplayName("Вход в личный кабинет")
    @Description("Вход в форме восстановления")
    public void loginRecoveryForm() {
        mainPage.clickPersonal();
        loginPage.clickRecoveryPassword();
        recoveryPasswordPage.clickLoginButton();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("login"));
        loginPage.login(email, password);
        loginPage.clickEnterButton();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(PAGE_URL));
        assertTrue(driver.getCurrentUrl().contains(PAGE_URL));
        assertTrue(mainPage.getOrderButtonText().contains("Оформить заказ"));
    }
}
