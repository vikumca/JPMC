package com.JavaSelenium.JPMCSearch.stepdefinitions;

import com.JavaSelenium.JPMCSearch.JPMCSearch.GoogleHomePage;
import com.JavaSelenium.JPMCSearch.JPMCSearch.LogoPage;
import com.JavaSelenium.JPMCSearch.JPMCSearch.SearchOptions;
import com.JavaSelenium.JPMCSearch.core.driver.DriverSetUp;
import com.JavaSelenium.JPMCSearch.core.exception.FrameworkExceptions;
import com.JavaSelenium.JPMCSearch.core.helper.Constants;
import com.JavaSelenium.JPMCSearch.core.logger.Logs;
import com.JavaSelenium.JPMCSearch.core.pageHelper.AllPages;
import com.JavaSelenium.JPMCSearch.core.utils.Assertions;
import com.JavaSelenium.JPMCSearch.core.utils.Config;
import com.JavaSelenium.JPMCSearch.core.utils.Waits;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class GoogleSearchSteps {

    WebDriver driver;
    Assertions Assertions = new Assertions();
    private GoogleHomePage googleHomePage() {return AllPages.getPage(GoogleHomePage.class);}
    private SearchOptions getSearchOptions() {return AllPages.getPage(SearchOptions.class);}
    private LogoPage getLogoPage() {return  AllPages.getPage(LogoPage.class);}

    @Given("^Goto Google HomePage \"(.*)\"$")
    public void gotoGoogleSearchPage(String url) throws Exception{
        DriverSetUp.initializeDriver(Config.getConfigProperty(Constants.BROWSERTYPE));
        driver = DriverSetUp.getDriver();
        driver.get(url);
        Waits.waitForPageLoadJS();
        Logs.info("Navigated to url--"+url);
    }

    @When("^Enter \"(.*)\" in the search text field$")
    public void setTextToSearch(String textToBeSearched) throws Exception {
        googleHomePage().setSearchedText(textToBeSearched);
    }

    @And("^Click on Search Button$")
    public void clickSearchButton() throws FrameworkExceptions {
        googleHomePage().clickSearchButton();
    }

    @And("^Click on First Link on Search Page$")
    public void clickFirstLink() throws Exception {
        getSearchOptions().clickFirstLink();
    }

    @Then("^JPMC Logo should appear$")
    public void verifyLogo() throws FrameworkExceptions {
        boolean result = getLogoPage().verifyLogo();
        Assertions.softAssertTrue(result,"Logo Displayed Correctly", "Logo is not displaying");
        Assertions.assertAll();
    }




}
