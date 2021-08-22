package com.JavaSelenium.JPMCSearch.core.utils;

import com.JavaSelenium.JPMCSearch.core.driver.DriverSetUp;
import org.openqa.selenium.JavascriptExecutor;

public class JSExecutor {

    private JSExecutor(){}

    public static JavascriptExecutor getJavaScriptExec() {
        return (JavascriptExecutor) DriverSetUp.getDriver();
    }
}
