package PageObjectModel;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalPage {
    private WebDriver driver;

    //Локатор для "Выход"
    private By logoutButton = By.xpath("//button[text()='Выход']");
    //Локатор для "Конструктор"
    private By сonstructorButton = By.xpath("//p[contains(text(),'Конструктор')]");
    //Локатор для Логотипа
    private By logoName = By.xpath(".//div[contains(@class,'logo')]/a");

    public PersonalPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажатие кнопки 'Выход'")
    public void clickLogout() {
        driver.findElement(logoutButton).click();
    }

    @Step("Нажатие на Конструктор")
    public void clickConstructor() {
        driver.findElement(сonstructorButton).click();
    }

    @Step("Нажатие на Логотип")
    public void clickLogo() {
        driver.findElement(logoName).click();
    }
}
