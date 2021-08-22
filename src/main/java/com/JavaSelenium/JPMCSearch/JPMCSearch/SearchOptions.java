package com.JavaSelenium.JPMCSearch.JPMCSearch;

import com.JavaSelenium.JPMCSearch.core.driver.DriverSetUp;
import com.JavaSelenium.JPMCSearch.core.exception.FrameworkExceptions;
import com.JavaSelenium.JPMCSearch.core.logger.Logs;
import com.JavaSelenium.JPMCSearch.core.pageHelper.Components;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchOptions extends Components {

    public boolean clickFirstLink() throws Exception {
        boolean blnFlag = false;
        try{
            List<WebElement> list = DriverSetUp.getDriver().findElements(By.xpath("//a[contains(@href, 'https://www.jpmorgan.com')]"));
            for (WebElement element:list) {
                element.click();
                blnFlag = true;
                break;
            }
        }
        catch (Exception fe){
            Logs.error("Error clicking on the First link on Search Page");
        }
        return blnFlag;
    }
}
