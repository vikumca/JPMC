package com.JavaSelenium.JPMCSearch.core.ui;

import com.JavaSelenium.JPMCSearch.core.exception.FrameworkExceptions;
import com.JavaSelenium.JPMCSearch.core.ui.IElement;
import org.openqa.selenium.Keys;

public interface ITextField extends IElement {
    void setText(String text) throws FrameworkExceptions;

}

