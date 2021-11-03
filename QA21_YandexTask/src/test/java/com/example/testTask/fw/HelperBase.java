package com.example.testTask.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class HelperBase {
    WebDriver driver;

    public HelperBase(WebDriver driver) {
        this.driver = driver;
    }

    public void refresh() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(2000);
    }

    public void SleepAndAssert(int millisekonds, String itemName, String foundItemName) throws InterruptedException {
        Thread.sleep(millisekonds);
        Assert.assertEquals(foundItemName, itemName);
    }

    public void refreshAndSleep(int millisekonds) throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(millisekonds);
    }

    public void acceptCookies() {
        click(By.cssSelector("[data-id='button-all']"));
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


}
