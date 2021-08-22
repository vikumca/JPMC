package com.JavaSelenium.JPMCSearch.core.driver;

import com.JavaSelenium.JPMCSearch.core.helper.Constants;
import com.JavaSelenium.JPMCSearch.core.logger.LogEventListener;
import com.JavaSelenium.JPMCSearch.core.logger.Logs;
import com.JavaSelenium.JPMCSearch.core.utils.Config;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class DriverSetUp {

    private static final ThreadLocal<WebDriver> webDriverThreadLocal;
    public static boolean browserInitialized = false;

    static {
        webDriverThreadLocal = new ThreadLocal<>();
    }

    private DriverSetUp() {}

    /**
     * To initialize the Driver
     * @param   browser
     *          - Browser (e.g. Chrome, Firefox IE etc)
     * @return
     */
    public static void initializeDriver(String browser) {
        WebDriver driver = null;
        if (("chrome").equalsIgnoreCase(browser)) {
            driver = initiateChromeDriver();
            Logs.info("Started Chrome Browser successfully.");
        }

        driver = new EventFiringWebDriver(driver).register(new LogEventListener());
        setDriver(driver);
        browserInitialized = true;
    }


    /**
     * To initialize the Chrome Driver
     * @param
     * @return  driver
     *          - chrome driver
     */
    private static WebDriver initiateChromeDriver() {
        WebDriver driver = null;
        try {
            String chromePath = Config.getConfigProperty(Constants.CHROMEDRIVERPATH);
            System.setProperty("webdriver.chrome.driver", chromePath);

            ChromeDriverService chromeService = ChromeDriverService.createDefaultService();
            String commandSwitches = "WebDriverCommandSwitch";
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);

            if (!commandSwitches.isEmpty() || commandSwitches.contains("--")) {
                Logs.info("User had specified [" + commandSwitches + "] command switch for Chrome Browser");
                ChromeOptions options = new ChromeOptions();
                String[] commandList = commandSwitches.split(",");
                for (String command : commandList) {
                    options.addArguments(command);
                }
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                driver = new ChromeDriver(chromeService, capabilities);
                Logs.info("Started Google Chrome Driver with command switches successfully");
            } else {
                Logs.debug("Starting the Chrome Driver");
                driver = new ChromeDriver(chromeService);
                Logs.info("Started Google Chrome Browser successfully");
            }
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            Logs.error("Failed to initiate Chrome Driver"+e);
        }
        return driver;
    }



    //Setter
    private static void setDriver(WebDriver driver) {
        webDriverThreadLocal.set(driver);
    }

    //Getter
    public static WebDriver getDriver() {
        WebDriver driver = null;
        driver = webDriverThreadLocal.get();
        if (driver == null)
            throw new IllegalStateException("Driver not set...");
        return driver;
    }

}
