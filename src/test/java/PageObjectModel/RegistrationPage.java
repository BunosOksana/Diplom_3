package PageObjectModel;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class RegistrationPage {
    private WebDriver driver;
    public static final Duration longWait = Duration.ofSeconds(3);
    public static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";
    public static final String REGISTRATUON_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";

    //Локатор для поля "Имя"
    private By nameField = By.xpath("//label[text()='Имя']/following-sibling::input[@name='name']");
    //Локатор для поля "Email"
    private By emailField = By.xpath("//label[text()='Email']/following-sibling::input[@name='name']");
    //Локатор для поля "Пароль"
    private By passwordField = By.xpath("//input[@name='Пароль' and @type='password']");
    //Локатор для "Зарегистрироваться"
    private By registrationButton = By.xpath("//button[text()='Зарегистрироваться']");
    //Локатор для "Некорректный пароль"
    private By registrationError = By.xpath("//p[contains(text(),'Некорректный пароль')]");
    //Локатор для "Войти"
    private By enterButton = By.xpath("//a[contains(text(),'Войти')]");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Переход на сайт на страницу регистрации")
    public void openRegistrationPage() {
        driver.manage().timeouts().implicitlyWait(longWait);
        driver.manage().window().maximize();
        driver.get(REGISTRATUON_PAGE_URL);
    }

    @Step("Нажать Зарегистрироваться")
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    @Step("Нажать Войти")
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }

    @Step("Проверка отображения сообщения 'Некорректный пароль'")
    public String getErrorText() {
        return driver.findElement(registrationError).getText();
    }

    @Step("Регистрация-ввод данных")
    public void registration(String email, String password, String name) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(nameField).sendKeys(name);
    }
}
