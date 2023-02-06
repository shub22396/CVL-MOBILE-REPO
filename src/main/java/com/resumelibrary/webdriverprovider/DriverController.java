package com.resumelibrary.webdriverprovider;

import com.resumelibrary.utilities.Constants;
import com.resumelibrary.utilities.PropertyFileReader;
import com.resumelibrary.utilities.WebURLHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.Map;

public class DriverController extends CloudDriverProvider implements Constants {

    private static final Logger logger = LogManager.getLogger(DriverController.class);

    public DriverController() {
        PageFactory.initElements(getThreadDriver(), this);
        PropertyConfigurator.configure(System.getProperty("user.dir") + LOG_PROPERTY_FILE_PATH);
    }

    public void getDriver(String browserName, String machineName, Map threadMap, String testName,String deviceName) {

        switch (browserName) {
            case "chrome":
                logger.info("[--->Using chrome browser<---]");
                chromeBrowser(threadMap);
                break;
            case "chromeHeadless":
                logger.info("[--->Using chrome Head less browser<---]");
                headLessChromeBrowser(threadMap);
                break;
            case "firefoxHeadless":
                logger.info("[--->Using firefox Head less browser<---]");
                headLessFirefoxBrowser(threadMap);
                break;
            case "firefox":
                logger.info("[--->Using firefox browser<---]");
                firefoxBrowser(threadMap);
                break;
            case "remoteChromeBrowser":
                logger.info("[--->Using remote chrome browser<---]");
                remoteChromeBrowser(machineName, threadMap);
                break;
            case "remoteFirefoxBrowser":
                logger.info("[--->Using remote firefox browser<---]");
                remoteFirefoxBrowser(machineName, threadMap);
                break;
            case "lambdaTestChrome":
                logger.info("[--->Using lambdatest cloud chrome browser<---]");
                remoteLambdaTestinChrome(threadMap, testName);
                break;
            case "lambdaTestFirefox":
                logger.info("[--->Using lambdatest cloud firefox browser<---]");
                remoteLambdaTestinFirefox(threadMap, testName);
                break;
            case "browserStackChrome":
                logger.info("[--->Using browser stack cloud chrome browsers<---]");
                remoteBrowserStackChrome(threadMap, testName);
                break;
            case "browserStackFireFox":
                logger.info("[--->Using browser stack cloud firefox browsers<---]");
                remoteBrowserStackFireFox(threadMap, testName);
                break;
            case "lambdaMobileWeb":
                logger.info("[--->Using  lambdaMobileWeb<---]");
                String isRealDevice = PropertyFileReader.getInstance().getProperty("isRealDevice");
                String isRealDeviceVal = WebURLHelper.getParameterFromEnvOrSysParam("ISREALDEVICE", isRealDevice);
                if(isRealDeviceVal.equalsIgnoreCase("yes")) {
                    logger.info("[--->isRealDeviceVal:"+isRealDeviceVal+"<---]");
                    androidRealMobileWeb(threadMap, testName,deviceName);
                }else{
                    logger.info("[--->isRealDeviceVal:"+isRealDeviceVal+"<---]");
                    androidMobileWeb(threadMap, testName);
                }
                break;
            case "lambdaIosMobileWeb":
                logger.info("[--->Using  lambdaMobileWeb<---]");

                iosMobileWeb(threadMap,testName);
                break;
            case "localMobileWeb":
                logger.info("[--->Using  localMobileWeb<---]");

                localMobileWeb(threadMap);
                break;
        }
        manageBrowser();
    }

    private void manageBrowser() {
        //getThreadDriver().manage().window().setSize(new Dimension(414,736));
       // getThreadDriver().manage().window().maximize();
        getThreadDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        getThreadDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(PropertyFileReader.getInstance().getProperty("implicitlyWait"))));
        getThreadDriver().manage().deleteAllCookies();
    }

    public WebDriver getThreadDriver() {
        WebDriver webdriverObj = null;
        try {
            webdriverObj = (AndroidDriver) ((Map) threadLocalMap.get()).get("webdriverObj");
        } catch (Exception e) {

        }
        return webdriverObj;
    }
    public WebDriver getAppiumdDriver() {
        WebDriver webdriverObj = null;
        try {
            webdriverObj = (AppiumDriver) ((Map) threadLocalMap.get()).get("webdriverObj");
        } catch (Exception e) {

        }
        return webdriverObj;
    }
    public String getRunnerName() {
        return (((Map) threadLocalMap.get()).get("runnerClass")).toString();
    }

    public String isSkipTest() {
        return ((Map) threadLocalMap.get()).get("skipTest").toString();
    }

    public String getRandomEmail() {
        return ((Map) threadLocalMap.get()).get("randomEmail").toString();
    }

    public void setRandomEmail(String email) {
        ((Map) threadLocalMap.get()).put("randomEmail", email);
    }

    public String getConstantsURL(String URL) {
        return null;
    }

}