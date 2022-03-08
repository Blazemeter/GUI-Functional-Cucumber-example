package hellocucumber;

import config.BzmConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    private final static ThreadLocal<WebDriver> WEB_DRIVER = new ThreadLocal<>();
    private final static String BUILD_ID = Long.toString(System.nanoTime());
    private static URL url = null;

    private static void initDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("blazemeter.apiKey", BzmConfig.API_KEY);
        capabilities.setCapability("blazemeter.apiSecret", BzmConfig.API_TOKEN);
        capabilities.setCapability("blazemeter.projectId", BzmConfig.PROJECT_ID);
//        Specify location to use for tests execution, otherwise default public location will be used:
//        capabilities.setCapability("blazemeter.locationId", BzmConfig.LOCATION_ID);

        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("blazemeter.buildId", BUILD_ID);
        capabilities.setCapability("blazemeter.testName", "Cucumber test");
        capabilities.setCapability("blazemeter.reportName", "Cucumber report");
        try {
            url = new URL("https://a.blazemeter.com/api/v4/grid/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        WEB_DRIVER.set(new RemoteWebDriver(url, capabilities));
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    public static WebDriver getDriver() {
        if (WEB_DRIVER.get() == null) {
            initDriver();
        }
        return WEB_DRIVER.get();
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            WEB_DRIVER.remove();
        }
    }

}
