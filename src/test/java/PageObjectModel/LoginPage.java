package PageObjectModel;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    //Локатор для поля "Пароль"
    private By passwordField = By.xpath("//input[@name='Пароль' and @type='password']");
    //Локатор для поля "Email"
    private By emailField = By.xpath("//label[text()='Email']/following-sibling::input[@name='name']");
    //Локатор для поля "Восстановить пароль"
    private By resetPasswordButton = By.xpath("//a[contains(text(),'Восстановить пароль')]");
    //Локатор для кнопки "Вход"
    private By loginButton = By.xpath(".//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать Войти")
    public void clickEnterButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Нажать Восстановить пароль")
    public void clickRecoveryPassword() {
        driver.findElement(resetPasswordButton).click();
    }

    @Step("Логин-ввод данных")
    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
    }
}
