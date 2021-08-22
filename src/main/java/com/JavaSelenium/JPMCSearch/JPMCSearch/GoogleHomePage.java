package com.JavaSelenium.JPMCSearch.JPMCSearch;

import com.JavaSelenium.JPMCSearch.core.exception.FrameworkExceptions;
import com.JavaSelenium.JPMCSearch.core.pageHelper.Components;
import com.JavaSelenium.JPMCSearch.core.ui.IElement;
import com.JavaSelenium.JPMCSearch.core.ui.ITextField;
import com.JavaSelenium.JPMCSearch.core.ui.implementation.Element;
import com.JavaSelenium.JPMCSearch.core.ui.implementation.TextField;
import com.JavaSelenium.JPMCSearch.core.utils.Waits;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleHomePage extends Components {

    @FindBy(xpath = "//input[@name='q']")
    private WebElement txtSearch;

    @FindBy(xpath = "//input[@value='Google Search']")
    private WebElement btnGoogleSearch;

    public final ITextField getTextSearch() {return getComponent(txtSearch, TextField.class, this.getClass());    }

    public final IElement getButtonGoogleSearch() {return getComponent(btnGoogleSearch, Element.class, this.getClass());    }

    public GoogleHomePage setSearchedText(String textToBeSearched) throws FrameworkExceptions {
        getTextSearch().setText(textToBeSearched);
        return this;
    }

    public GoogleHomePage clickSearchButton() throws FrameworkExceptions{
        Waits.explicitWait();
        getButtonGoogleSearch().click();
        return this;
    }
}
