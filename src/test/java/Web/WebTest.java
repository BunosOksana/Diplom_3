package Web;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTest {
    private WebDriver driver;

    public static WebDriver getWebDriver(Browser browser){
       switch (browser){
           case CHROME:
               System.setProperty("webdriver.chrome.driver","C:\\Diplom\\Diplom_3\\driver\\chromedriver\\chrome.exe");
           return new ChromeDriver();
           case YANDEX:
               System.setProperty("webdriver.chrome.driver","C:\\Diplom\\Diplom_3\\driver\\yandexdriver\\yandexdriver.exe");
               return new ChromeDriver();
           default:return new ChromeDriver();
       }
    }

    @AfterEach
    public void after(){
        driver.quit();
    }

    @Test
    public void openPage(){
driver=getWebDriver(Browser.YANDEX);
driver.get("https://www.google.com");
    }
}
