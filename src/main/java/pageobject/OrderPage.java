package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    public WebDriver driver;
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //блок заказа
    private By orderForm = By.className("Order_Content__bmtHS");

    //поле ввода Имя
    private By inputName = By.xpath(".//input[contains(@placeholder, 'Имя')]");

    //поле ввода Фамилия
    private By inputLastName = By.xpath(".//input[contains(@placeholder, 'Фамилия')]");

    //поле ввода Адрес
    private By inputAddress = By.xpath(".//input[contains(@placeholder, 'Адрес')]");

    //поле ввода Станция метро
    private By inputMetroStation = By.xpath(".//input[contains(@placeholder, 'Станция метро')]");

    //поле ввода Телефон
    private By inputPhone = By.xpath(".//input[contains(@placeholder, 'Телефон')]");

    //кнопка перехода "Далее"
    private By nextButton = By.xpath(".//button[text()= 'Далее']");

    //блок с выпадающим списком станций метро
    private By chooseMetroStation = By.className("select-search__select");

    //список станций метро
    private By listMetroStation = By.xpath(".//ul/li");

    //поле ввода даты аренды
    private By inputDateRent = By.xpath(".//input[contains(@placeholder, 'Когда привезти самокат')]");

    //блок аренды
    private By orderRentForm = By.className("Order_Content__bmtHS");

    //поле ввода срока аренды
    private By dropdownRentalPeriod = By.xpath(".//div[@class = 'Dropdown-control']");

    //поле для выбора цвета
    private By checkboxColor = By.className("Checkbox_Input__14A2w");

    //поле для ввода комментария
    private  By inputComment = By.xpath(".//input[contains(@placeholder, 'Комментарий для курьера')]");

    //кнопка "Заказать"
    private By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");

    //модальное окно с подтверждением заказа
    private By orderConfirmModal = By.className("Order_Modal__YZ-d3");

    //кнопка подтверждения заказа
    private By confirmOrderButton = By.xpath(".//button[text() = 'Да']");

    //модальное окно с инфо о созданном заказе
    private By comlpletedOrderModal = By.className("Order_ModalHeader__3FDaJ");

    //показ формы заказа
    public void visibleOrderForm(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(orderForm));
        driver.findElement(orderForm).isDisplayed();
    }

    //ввод имени
    public void setInputName(String userName){
        driver.findElement(inputName).sendKeys(userName);
    }

    //ввод фамилии
    public void setInputLastName(String userLastName){
        driver.findElement(inputLastName).sendKeys(userLastName);
    }

    //ввод адреса
    public void setInputAddress(String userAddress){
        driver.findElement(inputAddress).sendKeys(userAddress);
    }

    //ввод телефона
    public void setInputPhone(String userPhone){
        driver.findElement(inputPhone).sendKeys(userPhone);
    }

    //клик по кнопке "Далее"
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    //выбор станции метро
    public void setMetroStation(String metroStation){
        driver.findElement(inputMetroStation).sendKeys(metroStation);
        driver.findElement(chooseMetroStation).isEnabled();
        driver.findElement(listMetroStation).click();
    }

    //отображение блока аренды
    public void visibiltyOrderRentForm(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(orderRentForm));
        driver.findElement(orderRentForm).isDisplayed();
    }

    //устанавливаем дату аренды
    public void setDateRent(String dataRent) {
        driver.findElement(inputDateRent).click();
        driver.findElement(inputDateRent).sendKeys(dataRent);
        driver.findElement(inputDateRent).sendKeys(Keys.ENTER);
    }

    //выбор срока аренды
    public void setDropdownRentalPeriod(String period) {
        driver.findElement(dropdownRentalPeriod).click();
        driver.findElement(By.className("Dropdown-menu")).isEnabled();
        driver.findElement(By.xpath(".//div[text()='"+period+"']")).click();
    }

    //выбор цвета самоката
    public void setCheckboxColor(String color) {
        driver.findElement(By.xpath(".//label[text() = '"+color+"']")).click();
    }

    //ввод коммента для курьера
    public void setInputComment(String comment){
        driver.findElement(inputComment).sendKeys(comment);
    }

    //клик по кнопке "Заказать"
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    //показ модалки с подтверждением заказа
     public void visibleConfirmOrderModal(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                 .until(ExpectedConditions.visibilityOfElementLocated(orderConfirmModal));
       driver.findElement(orderConfirmModal).isDisplayed();
    }

    //клик по кнопке с подтвеждением заказа
    public void clickConfirmOrderButton() {
        driver.findElement(confirmOrderButton).click();
    }

    //отображение модального окна об успешном заказе
    public String getTextCompletedOrderModal() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(comlpletedOrderModal));
        driver.findElement(comlpletedOrderModal).isDisplayed();
       return driver.findElement(comlpletedOrderModal).getText();
    }
}
