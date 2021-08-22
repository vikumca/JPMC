package com.JavaSelenium.JPMCSearch.core.pageHelper;

import com.JavaSelenium.JPMCSearch.core.driver.DriverSetUp;
import org.openqa.selenium.support.PageFactory;

public class AllPages {
    private AllPages(){}

    public static <T> T getPage(Class<T> clazz) {
        return PageFactory.initElements(DriverSetUp.getDriver(), clazz);
    }
}
