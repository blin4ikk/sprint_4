import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageobject.MainPage;
import java.time.Duration;


@RunWith(Parameterized.class)
public class TestMainPage {
    private WebDriver driver;
    private static final String DEFAULT_BROWSER_NAME = "CHROME";
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(3);
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final String BROWSER_NAME_ENV_VARIABLE = "BROWSER_NAME";

    private final String question;
    private final String answer;

    public TestMainPage(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
    @Parameterized.Parameters
    public static Object[][] getFAQ() {
        return new Object[][]{
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Before
    public void before() {
        String browserName = System.getenv(BROWSER_NAME_ENV_VARIABLE);
        driver =
                WebDriverFactory.createForName(browserName != null ? browserName : DEFAULT_BROWSER_NAME);
    }

    @Test
    public void testCompare() {
        driver.get(PAGE_URL);
        MainPage mainPage = new MainPage(driver);

       mainPage.scrollToAskAboutImportantBlock(); //скроллим до блока "Вопросы о важном"
       mainPage.visibilityAskAboutImportantBlock(); //ждем, пока блок "Вопросы о важном" отобразится
       mainPage.comparisonQuestion(question, answer); //проверяем ответ на вопрос
    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}