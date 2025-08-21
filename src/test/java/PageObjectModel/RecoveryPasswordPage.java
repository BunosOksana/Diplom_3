package PageObjectModel;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoveryPasswordPage {
    private WebDriver driver;

    //Локатор для "Войти"
    private By loginButton = By.xpath(".//a[text()='Войти']");

    public RecoveryPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажатие кнопки 'Войти' на странице восстановления пароля")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
