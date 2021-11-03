package com.example.testTask.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MarketHeaderHelper extends HelperBase{
    public MarketHeaderHelper(WebDriver driver) {
        super(driver);
    }

    public void typeInSearchInputField(String itemName) {
        type(By.id("header-search"), itemName);
        click(By.cssSelector("[data-r='search-button']"));
    }

    public void selectDepartmentType(String depType) {
        click(By.cssSelector("[href='/catalog" + depType +"/list?filter-express-delivery=1&searchContext=express']"));
    }

    public void selectExpressDepartment() {
        click(By.cssSelector("div:nth-child(3) ._3z8Gf"));
    }
}
