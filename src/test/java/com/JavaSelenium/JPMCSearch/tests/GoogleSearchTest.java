package com.JavaSelenium.JPMCSearch.tests;


import com.JavaSelenium.JPMCSearch.JPMCSearch.GoogleHomePage;
import com.JavaSelenium.JPMCSearch.JPMCSearch.LogoPage;
import com.JavaSelenium.JPMCSearch.JPMCSearch.SearchOptions;
import com.JavaSelenium.JPMCSearch.base.BaseTest;
import com.JavaSelenium.JPMCSearch.core.driver.DriverSetUp;
import com.JavaSelenium.JPMCSearch.core.exception.FrameworkExceptions;
import com.JavaSelenium.JPMCSearch.core.pageHelper.AllPages;
import com.JavaSelenium.JPMCSearch.core.utils.ListenerClass;
import com.JavaSelenium.JPMCSearch.core.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

@Listeners({ ListenerClass.class })
public class GoogleSearchTest extends BaseTest{

    private GoogleHomePage getHomePage() {return AllPages.getPage(GoogleHomePage.class);}
    private LogoPage getLogoPage() {return  AllPages.getPage(LogoPage.class);}
    private SearchOptions getSearchOptions() { return AllPages.getPage(SearchOptions.class);}

    @Test(enabled = true)
    public void searchTest(ITestContext testContext) throws FrameworkExceptions, Exception {

        testContext.setAttribute("WebDriver", driver);
        Assertions.setSoftAssert(new SoftAssert());

        getHomePage().setSearchedText("J.P.Morgan");
        Assertions.logInformation("Entered Text in the Search text field");

        getHomePage().clickSearchButton();
        Waits.waitForPageLoadJS();

        boolean blnFlagResult = getSearchOptions().clickFirstLink();
        Waits.waitForPageLoadJS();
        Assertions.hardAssertTrue(blnFlagResult, "Clicked on the First Link", "Failed to Click on the Link");

        Assertions.softAssertTrue(getLogoPage().verifyLogo(), "Logo is displayed correctly", "Not displayed");

        Assertions.assertAll();

    }

}
