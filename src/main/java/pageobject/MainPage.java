package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //верхняя кнопка создания заказа
    private By orderTopButton = By.xpath(".//div[@class = 'Header_Nav__AGCXC']/button[@class = 'Button_Button__ra12g']");

    //нижняя кнопка создания заказа
    private By orderBottomButton = By.xpath(".//div[@class = 'Home_FinishButton__1_cWm']/button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");

    //блок "Вопросы о важном" надо подумать нужен ли
    private By askAboutImportantBlock = By.className("Home_FourPart__1uthg");

    private By cookieModal = By.className("App_CookieConsent__1yUIN");

    //скроллим до блока "Вопросы о важном"
    public void scrollToAskAboutImportantBlock() {
        WebElement element = driver.findElement(askAboutImportantBlock);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //ждем пока блок "Вопросы о важном" отобразится
    public void visibilityAskAboutImportantBlock() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(askAboutImportantBlock));
    }

    //клик по верхней кнопка заказа
    public void clickOrderTopButton() {
        driver.findElement(orderTopButton).click();
    }

    //клик по нижней кнопке заказа
    public void clickOrderBottomButton() {
        driver.findElement(orderBottomButton).click();
    }

    //клик по строке в выпадающем списке и сравниваем наличие содержания
    public void comparisonQuestion(String question, String answer) {
        driver.findElement(By.xpath(".//div[text() = '"+question+"']")).click();
        driver.findElement(By.xpath(".//p[text() = '"+answer+"']")).isDisplayed();
    }

    public void clickAcceptCookie() {
        if (driver.findElement(cookieModal).isDisplayed()) {
            driver.findElement(By.id("rcc-confirm-button")).click();
        }
    }
}