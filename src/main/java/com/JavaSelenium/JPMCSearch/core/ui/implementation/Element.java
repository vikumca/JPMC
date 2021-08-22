package com.JavaSelenium.JPMCSearch.core.ui.implementation;

import com.JavaSelenium.JPMCSearch.core.driver.DriverSetUp;
import com.JavaSelenium.JPMCSearch.core.exception.FrameworkExceptions;
import com.JavaSelenium.JPMCSearch.core.helper.Constants;
import com.JavaSelenium.JPMCSearch.core.logger.Logs;
import com.JavaSelenium.JPMCSearch.core.ui.IElement;
import com.JavaSelenium.JPMCSearch.core.utils.Config;
import com.JavaSelenium.JPMCSearch.core.utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.Assertion;

public class Element implements IElement {

    protected WebElement element;
    protected WebDriver driver;
    public String elementOriginalName = "";

    public Element(){
        // TODO Auto-generated method stub
        getInitializedDriver();
    }

    public Element(WebElement element) {
        this.element = element;
        getInitializedDriver();
    }

    public Element(WebElement element, String elementName) {
        this.element = element;
        elementOriginalName = elementName;
        getInitializedDriver();
    }

    // Get the initialized Driver
    private void getInitializedDriver() {
        driver = DriverSetUp.getDriver();
    }

    // Return Web Element
    @Override
    public WebElement getElement(){ return element; }

    // Verify the element is loaded
    @Override
    public boolean isLoaded() throws FrameworkExceptions {
        try {
            boolean flag = false;
            if (Waits.fluentWait(element, Integer.parseInt(Config.getConfigProperty(Constants.ELEMENTWAITTIME))) != null) {
                flag = true;
                Logs.info(Constants.ELEMENTLOGMESSAGE + elementOriginalName + " loaded");
            } else {
                Assertion hardAssert = new Assertion();
                hardAssert.assertFalse(true, Constants.ELEMENTLOGMESSAGE + elementOriginalName + Constants.ISLOADEDLOGMESSAGE_FAILURE);
            }
            return flag;
        } catch (FrameworkExceptions ex) {
            Logs.error(Constants.FORMATTER + Constants.ELEMENTLOGMESSAGE + elementOriginalName + Constants.ISLOADEDLOGMESSAGE_FAILURE);
            throw new FrameworkExceptions("Element: " + elementOriginalName + " not loaded"+ex);
        }
    }

    // Click on Element
    @Override
    public void click() throws FrameworkExceptions {
        try {
            if (clickElement()) {
                Logs.info("Button element: " + elementOriginalName + " clicked");
            } else {
                Logs.error(Constants.FORMATTER + " Button element: " + elementOriginalName + " click failed");
                throw new FrameworkExceptions("Button element: " + elementOriginalName + " not found");
            }
        } catch (Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " click() of Element class");
            throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " click() of Element class." + fe);
        }
    }

    // Click on Element
    @Override
    public boolean clickElement() throws FrameworkExceptions {
        try {
            if (isLoaded()) {
                element.click();
                return true;
            } else {
                Logs.error(Constants.FORMATTER + Constants.ELEMENTLOGMESSAGE + elementOriginalName + Constants.ISLOADEDLOGMESSAGE_FAILURE);
                return false;
            }
        } catch (Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " getText() of Element class");
            return false;
        }
    }

    // Verify the element is Displayed
    @Override
    public boolean isDisplayed() throws FrameworkExceptions {
        boolean flag = false;
        try {
            flag = element.isDisplayed();
        } catch (Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " isDisplayed() of Element class");
            return false;
        }
        return flag;
    }
}
