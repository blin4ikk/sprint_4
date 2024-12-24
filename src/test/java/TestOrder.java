import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageobject.MainPage;
import pageobject.OrderPage;
import java.time.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;

@RunWith(Parameterized.class)
public class TestOrder {
    private WebDriver driver;
    private static final String DEFAULT_BROWSER_NAME = "FIREFOX";
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(3);
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final String BROWSER_NAME_ENV_VARIABLE = "BROWSER_NAME";

    private final String userName;
    private final String userLastName;
    private final String userPhone;
    private final String userAddress;
    private final String userMetroStation;
    private final String userComment;
    private final String rentalPeriod;
    private final String checkboxColor;
    private final String dataRent;

    private final String expected;

    private static final String TODAY_DATE_RENT = todayDataRent();
    public static String todayDataRent() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.DAY_OF_MONTH) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR);
    }
    private static final String TOMORROW_DATE_RENT = tomorrowDataRent();
    public static String tomorrowDataRent() {
        Calendar calendar = new GregorianCalendar();
        calendar.roll(Calendar.DAY_OF_MONTH, 1);
        return calendar.get(Calendar.DAY_OF_MONTH) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR);
    }

    public TestOrder (String userName, String userLastName, String userAddress, String userMetroStation, String userPhone, String dataRent, String rentalPeriod, String checkboxColor, String userComment, String expected) {
        this.userName = userName;
        this.userLastName = userLastName;
        this.userAddress = userAddress;
        this.userMetroStation = userMetroStation;
        this.userPhone = userPhone;

        this.dataRent = dataRent;
        this.rentalPeriod = rentalPeriod;
        this.checkboxColor = checkboxColor;
        this.userComment = userComment;

        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] settingFAQ() {
        return new Object[][]{
                {"Пятнадцатьбуков", "ВполеФамилияНетОграниченияНаКоличествоСимволовНоЕстьОграничениеНаСимволы", "Адрес пробелы символы -, цифры 10 ёЁ приветпривет", "Петровско-Разумовская", "71231231212", TODAY_DATE_RENT, "сутки", "чёрный жемчуг", "комментарий", "Заказ оформлен"},
                {"Бу", "Бу", "Адрес", "Бульвар Дмитрия Донского", "+70000000000", TOMORROW_DATE_RENT, "семеро суток", "серая безысходность", " ", "Заказ оформлен"},
        };
    }

    @Before
    public void before() {
        String browserName = System.getenv(BROWSER_NAME_ENV_VARIABLE);
        driver =
                WebDriverFactory.createForName(browserName != null ? browserName : DEFAULT_BROWSER_NAME);
    }

    @Test
    public void testBottomButtonOrder() {
        driver.get(PAGE_URL);

        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        mainPage.clickAcceptCookie(); //если есть окно с куками, при принимаем

        mainPage.clickOrderBottomButton(); //нажимаем на кнопку "Заказать" внизу страницы
        orderPage.visibleOrderForm(); //ожидаем и проверяем, что отобразилась форма заказа
        orderPage.setInputName(userName); //заполняем поле "Имя"
        orderPage.setInputLastName(userLastName); //заполняем поле "Фамилия"
        orderPage.setInputAddress(userAddress); //заполняем поле "Адрес"
        orderPage.setMetroStation(userMetroStation); //заполняем поле "Станция метро"
        orderPage.setInputPhone(userPhone); //заполняем поле "Телефон"
        orderPage.clickNextButton(); //нажимаем на кноку "Далее"
        orderPage.visibiltyOrderRentForm(); //проверяем, что отобразилась форма для аренды
        orderPage.setDateRent(dataRent); //заполняем поле "Когда привезти самокат"
        orderPage.setDropdownRentalPeriod(rentalPeriod); //заполняем поле "Срок аренды"
        orderPage.setCheckboxColor(checkboxColor); //выбираем поле "Цвет самоката"
        orderPage.setInputComment(userComment); //заполняем поле "Комментарий для курьера"
        orderPage.clickOrderButton(); //нажимаем на кнопку "Заказать"
        orderPage.visibleConfirmOrderModal(); //проверяем, что отобразилось модальное окно с подтверждением заказа
        orderPage.clickConfirmOrderButton(); //в модальном окне подтверждаем создание заказа
        String actual = orderPage.getTextCompletedOrderModal(); //проверяем, какой текст в отобразившемся окне
        Assert.assertTrue("Ожидаем, что в модальном окне инфо об успешном заказе", actual.contains(expected));
    }

    @Test
    public void testTopButtonOrder() {
        driver.get(PAGE_URL);

        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        mainPage.clickAcceptCookie(); //если есть окно с куками, при принимаем

        mainPage.clickOrderTopButton(); //нажимаем на кнопку "Заказать" вверху страницы
        orderPage.visibleOrderForm(); //проверяем, что отобразилась форма заказа
        orderPage.setInputName(userName); //заполняем поле "Имя"
        orderPage.setInputLastName(userLastName); //заполняем поле "Фамилия"
        orderPage.setInputAddress(userAddress); //заполняем поле "Адрес"
        orderPage.setMetroStation(userMetroStation); //заполняем поле "Станция метро"
        orderPage.setInputPhone(userPhone); //заполняем поле "Телефон"
        orderPage.clickNextButton(); //нажимаем на кноку "Далее"
        orderPage.visibiltyOrderRentForm(); //проверяем, что отобразилась форма для аренды
        orderPage.setDateRent(dataRent); //заполняем поле "Когда привезти самокат"
        orderPage.setDropdownRentalPeriod(rentalPeriod); //заполняем поле "Срок аренды"
        orderPage.setCheckboxColor(checkboxColor); //выбираем поле "Цвет самоката"
        orderPage.setInputComment(userComment); //заполняем поле "Комментарий для курьера"
        orderPage.clickOrderButton(); //нажимаем на кнопку "Заказать"
        orderPage.visibleConfirmOrderModal(); //проверяем, что отобразилось модальное окно с подтверждением заказа
        orderPage.clickConfirmOrderButton(); //в модальном окне подтверждаем создание заказа
        String actual = orderPage.getTextCompletedOrderModal(); //проверяем, какой текст в отобразившемся окне
        Assert.assertTrue("Ожидаем, что в модальном окне инфо об успешном заказе", actual.contains(expected));
    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }

}