package com.example.testTask.fw;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.time.Duration;

public class ApplicationManager {
    String browser;
    WebDriver driver;

    MainPageHelper mainPage;  //declaration of Helpers
    MarketHeaderHelper marketHeader;
    MarketItemHelper marketItem;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser.equals(BrowserType.CHROME)) {
            driver = new ChromeDriver();  //  Открыть браузер и развернуть на весь экран
        } else if (browser.equals(BrowserType.FIREFOX)) {
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().to("https://yandex.ru/");//  Зайти на yandex.ru

        mainPage = new MainPageHelper(driver);
        marketHeader = new MarketHeaderHelper(driver);
        marketItem = new MarketItemHelper(driver);
    }

    public MainPageHelper getMainPage() {
        return mainPage;
    }

    public MarketHeaderHelper getMarketHeader() {
        return marketHeader;
    }

    public MarketItemHelper getMarketItem() {
        return marketItem;
    }

    public void stop() {
        driver.quit();
    }
}
