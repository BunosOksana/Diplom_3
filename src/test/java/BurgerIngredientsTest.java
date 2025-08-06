import PageObjectModel.MainPage;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BurgerIngredientsTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        mainPage.openMainPage();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    //    Т.к. при открытии главное страницы сразу открываются Булки, сначала проверяю что отображаются булки, потом клик на соседний таб и потом клик на таб Булки
    @Test
    @DisplayName("Раздел Конструктор")
    @Description("Раздел Булки")
    public void switchBuns() {
        mainPage.clickSauces();
        mainPage.clickBuns();
        assertEquals("Булки", mainPage.getActiveTabText());
    }

    @Test
    @DisplayName("Раздел Конструктор")
    @Description("Раздел Соусы")
    public void switchSauces() {
        mainPage.clickSauces();
        assertEquals("Соусы", mainPage.getActiveTabText());
    }

    @Test
    @DisplayName("Раздел Конструктор")
    @Description("Раздел Начинки")
    public void switchFilling() {
        mainPage.clickFillings();
        assertEquals("Начинки", mainPage.getActiveTabText());
    }
}
