import PageObjectModel.RegistrationPage;
import Utils.UserClients;
import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static PageObjectModel.RegistrationPage.LOGIN_PAGE_URL;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest {
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private String accessToken;
    private String email = RandomStringUtils.randomAlphabetic(8).toLowerCase() + "@ya.ru";
    private String password = RandomStringUtils.randomAlphabetic(10);
    private String name = RandomStringUtils.randomAlphabetic(10);
    private String newPassword = RandomStringUtils.randomAlphabetic(5);

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        registrationPage = new RegistrationPage(driver);
        registrationPage.openRegistrationPage();
    }

    @AfterEach
    public void tearDown() {
        if (accessToken != null) {
            UserClients.deleteUser(accessToken);
            System.out.println("Удаление пользователя с токеном: " + accessToken);
        } else {
            System.out.println("Токен отсутствует, удаление не выполнено");
        }
        driver.quit();
    }

    @Test
    @DisplayName("Регистрация")
    @Description("Успешная регистрация")
    public void regisrtationUser_success() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registration(email, password, name);
        registrationPage.clickRegistrationButton();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("login"));
        assertTrue(driver.getCurrentUrl().contentEquals(LOGIN_PAGE_URL));
        accessToken = UserClients.getAccessToken(email, password);
    }

    @Test
    @DisplayName("Регистрация")
    @Description("Ошибка регистрации для некорректного пароля")
    public void regisrtationUser_unsuccess() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registration(email, newPassword, name);
        registrationPage.clickRegistrationButton();
        new WebDriverWait(driver, Duration.ofSeconds(5));
        assertTrue(registrationPage.getErrorText().contains("Некорректный пароль"));
    }
}
