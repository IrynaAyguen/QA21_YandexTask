package com.example.testTask;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SearchItemTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();  //  Открыть браузер и развернуть на весь экран
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().to("https://yandex.ru/");//  Зайти на yandex.ru
    }

    @Test
    public void itemTest() throws InterruptedException {
        //  Перейти на Яндекс Маркет
        clickToMarketTab();
        switchToNextTab(1);

        // Выбрать раздел Экспресс
        selectExpressDepartment();
        acceptCookies();

        // Выбрать раздел Электроника
        selectDepartmentType("--elektronika/23282330");

        //Выбрать раздел Смартфоны. Зайти в расширенный поиск. Задать параметр поиска от 20000 до 35000 рублей.
        //Выбрать производителя “Apple” . Применить условия поиска
        filterItem("smartfony-i-aksessuary/23282379", "20000", "35000", "Apple");

        refreshAndSleep(2000);

        //  Запомнить второй элемент в результатах поиска
        String itemName = getItemName(2);

        //  В поисковую строку ввести запомненное значение.Найти товар.
        typeAndSearchOfSeconfElement(itemName);

        //  проверить, что наименование товара соответствует запомненному значению.
        String foundItemName = getItemName(1);
        SleepAndAssert(5000,itemName, foundItemName);
    }

    private String getItemName(int n) {
        return getTextOfElement(By.xpath("//*[@data-autotest-id='product-snippet']["+n+"]//h3"));
    }

    public void SleepAndAssert(int millisekonds, String itemName, String foundItemName) throws InterruptedException {
        Thread.sleep(millisekonds);
        Assert.assertEquals(foundItemName, itemName);
    }

    public void typeAndSearchOfSeconfElement(String itemName) {
        type(By.id("header-search"), itemName);
        click(By.cssSelector("[data-r='search-button']"));
    }

    public String getTextOfElement(By locator) {
        return driver.findElement(locator).getText();
    }

    public void refreshAndSleep(int millisekonds) throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(millisekonds);
    }

    public void filterItem(String itemType, String priceFrom, String priceTo, String brand) throws InterruptedException {
        click(By.cssSelector("[href='/catalog--"+ itemType +"/list?onstock=1&how=dpop&cvredirect=3&track=srch_ddl']")); //        Выбрать раздел Смартфоны
        Actions actions = new Actions(driver);  // jump down
        actions.sendKeys(Keys.PAGE_DOWN).build().perform();
        Thread.sleep(1000);
        type(By.id("glpricefrom"), priceFrom);//        Зайти в расширенный поиск
        type(By.id("glpriceto"), priceTo);//        Задать параметр поиска от 20000 до 35000 рублей.
        click(By.xpath("//span[text()='"+ brand +"']"));//        Выбрать производителя “Apple”
    }


    public void selectDepartmentType(String depType) {
        click(By.cssSelector("[href='/catalog" + depType +"/list?filter-express-delivery=1&searchContext=express']"));
    }

    public void acceptCookies() {
        click(By.cssSelector("[data-id='button-all']"));
    }

    public void selectExpressDepartment() {
        click(By.cssSelector("div:nth-child(3) ._3z8Gf"));
    }

    public void clickToMarketTab() {
        click(By.cssSelector("[data-id='market']"));
    }

    public void type(By locator, String text) {
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void switchToNextTab(int number) {
        List<String> availableWindows = new ArrayList<>(driver.getWindowHandles());
        if (!availableWindows.isEmpty()) {
            driver.switchTo().window(availableWindows.get(number));
        }
    }

    @AfterMethod(enabled = false)
    public void terDown() {
        driver.quit();
    }
}


