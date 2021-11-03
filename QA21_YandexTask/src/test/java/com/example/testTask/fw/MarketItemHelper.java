package com.example.testTask.fw;

import com.example.testTask.model.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class MarketItemHelper extends HelperBase{
    public MarketItemHelper(WebDriver driver) {
        super(driver);
    }

    public String getItemNameFromListByOrder(int number) {
        return getTextOfElement(By.xpath("//*[@data-autotest-id='product-snippet']["+number+"]//h3"));
    }

    public String getTextOfElement(By locator) {
        return driver.findElement(locator).getText();
    }

    public void filterItem(Item item) throws InterruptedException {
        click(By.cssSelector("[href='/catalog--"+ item.getItemType() +"/list?onstock=1&how=dpop&cvredirect=3&track=srch_ddl']")); //        Выбрать раздел Смартфоны
        Actions actions = new Actions(driver);  // jump down
        actions.sendKeys(Keys.PAGE_DOWN).build().perform();
        Thread.sleep(1000);
        type(By.id("glpricefrom"), item.getPriceFrom());//        Зайти в расширенный поиск
        type(By.id("glpriceto"), item.getPriceTo());//        Задать параметр поиска от 20000 до 35000 рублей.
        click(By.xpath("//span[text()='"+ item.getBrand() +"']"));//        Выбрать производителя “Apple”
    }
}
