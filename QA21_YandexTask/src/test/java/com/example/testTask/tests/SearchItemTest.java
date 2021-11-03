package com.example.testTask.tests;

import com.example.testTask.model.Item;
import org.testng.annotations.Test;

public class SearchItemTest extends TestBase{

    @Test
    public void itemTest() throws InterruptedException {
        //  Перейти на Яндекс Маркет
        app.getMainPage().clickToMarketTab();
        app.getMainPage().switchToNextTab(1);

        // Выбрать раздел Экспресс
        app.getMarketHeader().selectExpressDepartment();
        app.getMainPage().acceptCookies();

        // Выбрать раздел Электроника
        app.getMarketHeader().selectDepartmentType("--elektronika/23282330");

        //Выбрать раздел Смартфоны. Зайти в расширенный поиск. Задать параметр поиска от 20000 до 35000 рублей.
        //Выбрать производителя “Apple” . Применить условия поиска
        app.getMarketItem().filterItem(new Item().setItemType("smartfony-i-aksessuary/23282379").setPriceFrom("20000").setPriceTo("35000").setBrand("Apple"));

        app.getMainPage().refreshAndSleep(2000);

        //  Запомнить второй элемент в результатах поиска
        String itemName = app.getMarketItem().getItemNameFromListByOrder(2);

        //  В поисковую строку ввести запомненное значение.Найти товар.
        app.getMarketHeader().typeInSearchInputField(itemName);

        //  проверить, что наименование товара соответствует запомненному значению.
        String foundItemName = app.getMarketItem().getItemNameFromListByOrder(1);
        app.getMainPage().SleepAndAssert(5000,itemName, foundItemName);
    }


}


