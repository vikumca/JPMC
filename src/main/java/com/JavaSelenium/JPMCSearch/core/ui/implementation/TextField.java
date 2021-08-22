package com.JavaSelenium.JPMCSearch.core.ui.implementation;

import com.JavaSelenium.JPMCSearch.core.exception.FrameworkExceptions;
import com.JavaSelenium.JPMCSearch.core.helper.Constants;
import com.JavaSelenium.JPMCSearch.core.logger.Logs;
import com.JavaSelenium.JPMCSearch.core.ui.ITextField;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class TextField extends Element implements ITextField {

    public TextField() {
        super();
    }

    public TextField(WebElement element, String elementName) {
        super(element, elementName);
    }

    // Set Text in Text Field Element
    @Override
    public void setText(String text) throws FrameworkExceptions {
        if(text == null)
            return;
        try {
            if (isLoaded()) {
                element.sendKeys(text);
                Logs.info(Constants.TEXTFIELDLOGMESSAGE + elementOriginalName + " => " + text + Constants.SETTEXTLOGMESSAGE);
            } else {
                Logs.error(Constants.FORMATTER + Constants.TEXTFIELDLOGMESSAGE + elementOriginalName + " failed to enter text");
                throw new FrameworkExceptions(Constants.TEXTFIELDLOGMESSAGE + elementOriginalName + " not loaded in method setText()");
            }
        } catch (Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " setText() of TextField class");
            throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " setText() of TextField class." + fe);
        }
    }
}
