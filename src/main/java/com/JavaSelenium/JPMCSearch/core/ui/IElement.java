package com.JavaSelenium.JPMCSearch.core.ui;

import com.JavaSelenium.JPMCSearch.core.exception.FrameworkExceptions;
import org.openqa.selenium.WebElement;

public interface IElement {
    WebElement getElement();
    boolean clickElement() throws FrameworkExceptions;
    boolean isLoaded() throws FrameworkExceptions;
    void click() throws FrameworkExceptions;
    boolean isDisplayed() throws FrameworkExceptions;


}
