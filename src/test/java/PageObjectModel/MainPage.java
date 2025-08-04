package PageObjectModel;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    public static final Duration longWait = Duration.ofSeconds(3);
    public static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    //Локатор для "Личный кабинет" в хедере
    private By personalAccountButton = By.xpath("//p[contains(text(),'Личный Кабинет')]");
    //Локатор для "Войти в аккаунт"
    private By openAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    //Локатор для "Оформить заказ"
    private By createdOrderButton = By.xpath("//*[contains(text(), 'Оформить заказ')]");
    //Локатор для "Булки"
    private By bunsButton = By.xpath(".//span[text()='Булки']");
    //Локатор для имени "Булки"
    private By bunsName = By.xpath(".//h2[text()='Булки']");
    //Локатор для "Соусы"
    private By saucesButton = By.xpath("//span[contains(text(), 'Соусы')]");
    //Локатор для имени "Соус"
    private By saucesName = By.xpath("//h2[text()='Соусы']");
    //Локатор для "Начинки"
    private By fillingButton = By.xpath("//span[contains(text(), 'Начинки')]");
    //Локатор для имени "Начинки"
    private By fillingName = By.xpath("//h2[text()='Начинки']");

    public MainPage(WebDriver driver){
        this.driver=driver;
    }

    @Step("Переход на сайт на главную страницу")
    public void openMainPage() {
        driver.manage().timeouts().implicitlyWait(longWait);
        driver.manage().window().maximize();
        driver.get(PAGE_URL);
    }

     @Step("Нажимаем на кнопку Войти в аккаунт")
    public void clickOpenAccount() {
        driver.findElement(openAccountButton).click();
    }

    @Step("Нажимаем на кнопку Личный Кабинет")
    public void clickPersonal() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Проверяем отображение кнопки Оформить заказ")
    public String getOrderButtonText() {
        return driver.findElement(createdOrderButton).getText();
    }

    @Step("Проверка активности раздела Булки")
    public boolean checkBuns() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(bunsName));
        return element.isDisplayed();
    }

    @Step("Клик по разделу Булки")
    public MainPage clickBuns() {
        driver.findElement(bunsButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(bunsName));
        return this;
    }

    @Step("Клик по разделу Соусы")
    public MainPage clickSauces() {
        driver.findElement(saucesButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(saucesName));
        return this;
    }

    @Step("Клик по разделу Начинки")
    public MainPage clickFillings() {
        driver.findElement(fillingButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(fillingName));
        return this;
    }
}
