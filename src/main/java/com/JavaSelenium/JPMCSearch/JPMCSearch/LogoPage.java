package com.JavaSelenium.JPMCSearch.JPMCSearch;

import com.JavaSelenium.JPMCSearch.core.exception.FrameworkExceptions;
import com.JavaSelenium.JPMCSearch.core.pageHelper.Components;
import com.JavaSelenium.JPMCSearch.core.ui.IElement;
import com.JavaSelenium.JPMCSearch.core.ui.implementation.Element;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoPage extends Components{

    @FindBy(xpath = "//div[@class='logo-desktop-only']//img[@class='first-logo']")
    private WebElement imgLogo;

    public final IElement getImageLogo() {return getComponent(imgLogo, Element.class, this.getClass());    }

    public boolean verifyLogo() throws FrameworkExceptions {
        return getImageLogo().isDisplayed();
    }
}
