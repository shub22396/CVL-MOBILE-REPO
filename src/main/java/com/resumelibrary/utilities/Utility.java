package com.resumelibrary.utilities;

import com.google.common.collect.ImmutableList;
import com.resumelibrary.webdriverprovider.DriverController;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Utility extends DriverController {
    public static VerticalScenarioCountPojo verticalScenarioCountPojo = new VerticalScenarioCountPojo();

    /*   Constructor to initiate the page elements */
    public Utility() {
        PageFactory.initElements(getThreadDriver(), this);
    }
  public  static Map<String,String> deviceNames=new HashMap<>();
    private static final Logger logger = LogManager.getLogger(Utility.class);

    /* Load the project URL and reload the page if its 502 bad gateway */
    public void getDriverWithUrl(String projectUrl, String endpoint) {
        getThreadDriver().get(projectUrl + endpoint);
        if (getThreadDriver().getTitle().equals(ERROR_502)) {
            reloadPage();
        }
        disableSQLQuery();

    }

    /* Adding DISABLE_DB_PROFILER Cookie*/
    public void disableSQLQuery() {
        // Disabling DB queries shown at the bottom of the page
        setCookie(DISABLE_DATABASE_QUERY, "1");
    }

    /* Load the RL Web URL */
    public void loadWebUrl(String url) {
        getDriverWithUrl(WebURLHelper.getWebUrl(), url);
        waitUntil(WebURLHelper.getWebUrl() + url);
    }

    /* Load the RL Admin URL */
    public void loadAdminUrl(String url) {
        getDriverWithUrl(AdminURLHelper.getAdminUrl(), url);
        waitUntil(AdminURLHelper.getAdminUrl() + url);
    }

    /* Reloading the RL Web home page, if we get 502 Bad Gateway error */
    public void loadWebHomePage() {
        getDriverWithUrl(WebURLHelper.getWebUrl(), "/");
    }

    /* Reloading the RL Admin home page, if we get 502 Bad Gateway error */
    public void loadAdminHomePage() {
        getDriverWithUrl(AdminURLHelper.getAdminUrl(), "/");
    }

    /* Explicit wait condition until the actual and expected web url matches */
    public void webUrlVerify(String Url) {
        try {
            WebDriverWait timer = new WebDriverWait(getThreadDriver(), Duration.ofSeconds(5));
            timer.until(ExpectedConditions.urlMatches(WebURLHelper.getWebUrl() + getURL(Url)));
        } catch (AssertionError e) {
            e.printStackTrace();
        }
    }

    /* Explicit wait condition until the actual and expected Admin url matches */
    public void adminUrlMatch(String Url) {
        try {
            WebDriverWait timer = new WebDriverWait(getThreadDriver(), Duration.ofSeconds(5));
            ExpectedCondition<Boolean> urlIsCorrect = arg0 -> getThreadDriver().getCurrentUrl().startsWith(AdminURLHelper.getAdminUrl() + Url);
            timer.until(urlIsCorrect);
        } catch (AssertionError e) {
            e.printStackTrace();
        }
    }

    /* Refreshing the page */
    public void reloadPage() {
        getThreadDriver().navigate().refresh();
        webDriverWaitContainsUrl("/");
    }

    /* Navigating back to the page */
    public void backwardOnThePage() {
        getThreadDriver().navigate().back();
    }


    public void clickMobileHeader() {
        clickOnElement(getThreadDriver().findElements(By.xpath("//*[@id='mobile-menu-trigger']")).get(0));
    }

    public void clickOnHiring() {
        clickOnElement(getThreadDriver().findElements(By.xpath(" //*[@aria-controls='footer-hiring']")).get(0));
    }

    public void clickOnResumeLibrary() {
        clickOnElement(getThreadDriver().findElements(By.xpath(" //*[@aria-controls='footer-resume-library']")).get(0));
    }

    /* Adding cookie along with key and value */
    public void setCookie(String key, String value) {
        try {
            getThreadDriver().manage().addCookie(new Cookie(key, value));
        }catch (Exception e){
            logger.info("[--->got exception in setCookie  : "+e.getMessage()+"<---]");
            e.printStackTrace();
        }
    }

    /* Reloading the url if we get the 502 error  and wait for actual and wait for actual and expected url matches*/
    public void waitUntil(String url) {
        if (getThreadDriver().getTitle().equals(ERROR_502)) {
            reloadPage();
        }
        webDriverWaitContainsUrl(url);
    }

    /* static wait for a specified amount of time */
    public void waitFor(int time) {
        try {
            Thread.sleep(time * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* Explicit wait for 30 seconds and wait for actual and wait for actual and expected url matches */
    public void webDriverWaitContainsUrl(String url) {
        WebDriverWait wait = new WebDriverWait(getThreadDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.urlContains(url));
    }

    /* Explicit wait for a specified amount of time and until the specified Web Element is clickable */
    public void waitUntilElementToBeClickable(WebElement element, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(getThreadDriver(), Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }catch (WebDriverException e){
            if(getThreadDriver()!=null){
                getThreadDriver().quit();
            }
        }
    }

    /* Explicit wait for a specified amount of time and until the specified Element(xpath/id/name/linkname etc..) is clickable */
    public void waitUntilElementToBeClickable(By by, int timeOut) {
        WebDriverWait wait = new WebDriverWait(getThreadDriver(), Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.elementToBeClickable(getThreadDriver().findElement(by)));
    }

    /* Explicit wait for a specified amount of time and until the invisibility of specified Web Element */
    public void waitUntilElementToBeDisappear(WebElement element, int timeOut) {
        WebDriverWait wait = new WebDriverWait(getThreadDriver(), Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /* Explicit wait for a specified amount of time and until the specified Web Element text is present*/
    public void waitUntilTextToBePresent(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(getThreadDriver(), Duration.ofSeconds(15));
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
        logger.info("[--->Element Text : " + element.getText() + "<---]");
    }

    /* Explicit wait for a specified amount of time and until the visibility of specified Web Element */
    public void waitUntilElementIsLocated(WebElement element, int timeOut) {
        WebDriverWait wait = new WebDriverWait(getThreadDriver(), Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilStalenessOf(WebElement element, int timeOut) {
        WebDriverWait wait = new WebDriverWait(getThreadDriver(), Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element)));
    }

    /*Assertion for exact match of Expected and Actual url*/
    public void externalUrlVerify(String url) {
        String currentUrl = getThreadDriver().getCurrentUrl();
        Assert.assertEquals(currentUrl, url);
    }

    /*Assertion for partial match of Expected and Actual url*/
    public void externalShortUrlVerify(String url) {
        String currentUrl = getThreadDriver().getCurrentUrl();
        currentUrl.contains(url);
    }

    /* Returning the current page url */
    public String getPresentURL() {
        return ((AndroidDriver) getThreadDriver()).getCurrentUrl();
    }

    /* Returning the current browser name and version */
    public String getBrowserName() {
        Capabilities cap = ((RemoteWebDriver) getThreadDriver()).getCapabilities();
        String browserName = cap.getBrowserName();
        String browserVersion = cap.getBrowserVersion();
        return browserName + "-" + browserVersion + "-" + cap.getPlatformName();
    }

    /* Returning the text of specified web element */
    public String getTextFromElement(WebElement element) {
        waitUntilTextToBePresent(element, element.getText());
        logger.info("[--->text on the element " + element.getText() + "<---]");
        return element.getText();
    }

    /* Returning the text of element by passing the text as input */
    public WebElement getElementByText(String text) {
        WebElement elementByText = null;
        getThreadDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        boolean result = ((RemoteWebDriver) getThreadDriver()).findElements(By.xpath("//*[text()=\"" + text + "\"]")).size() > 0;
        getThreadDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        if (!result) {
            elementByText = ((RemoteWebDriver) getThreadDriver()).findElements(By.xpath("//*[contains(text(),\"" + text + "\")]")).get(0);
        } else {
            elementByText = ((RemoteWebDriver) getThreadDriver()).findElements(By.xpath("//*[text()=\"" + text + "\"]")).get(0);
        }
        logger.info("[--->text on the element " + elementByText.getText() + "<---]");
        return elementByText;
    }

    /* Clicking on element by using xpath/id/name/link */
    public void clickOnElement(By by) {
        waitUntilElementToBeClickable(by, 15);
        getThreadDriver().findElement(by).click();
    }

    /* Clicking on  a specified web element by handling element click intercepted exception */
    public void clickOnElement(WebElement element) {
        ((JavascriptExecutor) getThreadDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        waitUntilElementToBeClickable(element, 10);
        logger.info("[--->Clicking on element " + element.toString() + "<---]");
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            getThreadDriver().navigate().refresh();
            waitUntilElementToBeClickable(element, 10);
            element.click();
        }
    }

    /* Wait until the specified element is clickable ,scrolling to element and click using action class */
    public void clickOnElementUsingActions(WebElement element) {
        waitUntilElementToBeClickable(element, 10);
        logger.info("[--->Clicking on element " + element.toString() + "<---]");
        Actions actions = new Actions(getThreadDriver());
        ((JavascriptExecutor) getThreadDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        actions.moveToElement(element).click().build().perform();
    }

    /* Clicking on specified web element using javascript executor */
    public void clickOnElementWithJS(WebElement element) {
        waitUntilElementToBeClickable(element, 15);
        logger.info("[--->Clicking on element " + element.toString() + "<---]");
        ((JavascriptExecutor) ((RemoteWebDriver) getThreadDriver())).executeScript("arguments[0].click();", element);
    }

    /* Clicking on  element by inputting the element text using explicit wait condition and stale element exception is handled */
    public void clickOnElementUsingText(String text) {
        // we need this wait because we are trying to get the element by text, sometimes text is not ready to get the element
        waitFor(1);
        WebElement result = getElementByText(text);
        mouseHoverToElement(result);
        logger.info("[--->found an element with text : " + text + " : " + result.toString() + "<---]");
        if (!result.isDisplayed()) {
            result = getDisplayedElement(text);
        }
        waitUntilTextToBePresent(result, text);
        try {
            logger.info("[--->click on element with text : " + text + "<---]");
            waitUntilElementIsLocated(result, 5);
            clickOnElement(result);
        } catch (StaleElementReferenceException e) {
            logger.info("[--->stale element exception : " + text + "<---]");
            waitFor(4);
            clickOnElement(result);

        }
        waitFor(4);
    }

    public void clickOnElementUsingTextwithNAtive(String text) {
        // we need this wait because we are trying to get the element by text, sometimes text is not ready to get the element
        try {
            System.out.println("context-->" + ((AndroidDriver) getThreadDriver()).getContext());
            ((AndroidDriver) getThreadDriver()).context("NATIVE_APP");
            if (isElementDisplay(getThreadDriver().findElement(By.xpath("//*[@text=" + text + "']")))) {
                getThreadDriver().findElement(By.xpath("//*[@text=" + text + "']")).click();
            }

            ((AndroidDriver) getThreadDriver()).context("CHROMIUM");

        } catch (Exception e) {

        }
    }

    public void clickOnElementUsingTextWithJS(String text) {
        // we need this wait because we are trying to get the element by text, sometimes text is not ready to get the element
        waitFor(1);
        WebElement result = getElementByText(text);
        mouseHoverToElement(result);
        logger.info("[--->found an element with text : " + text + " : " + result.toString() + "<---]");
        if (!result.isDisplayed()) {
            result = getDisplayedElement(text);
        }
        waitUntilTextToBePresent(result, text);
        try {
            logger.info("[--->click on element with text : " + text + "<---]");
            waitUntilElementIsLocated(result, 5);
            clickOnElementWithJS(result);
        } catch (StaleElementReferenceException e) {
            logger.info("[--->stale element exception : " + text + "<---]");
            waitFor(4);
            clickOnElementWithJS(result);

        }
        waitFor(4);
    }


    protected void tapAtPoint(Point point) {
        AppiumDriver d = (AppiumDriver) getThreadDriver();
        PointerInput input = new PointerInput(Kind.TOUCH, "finger1");
        Sequence tap = new Sequence(input, 0);
        tap.addAction(input.createPointerMove(Duration.ZERO, Origin.viewport(), point.x, point.y));
        tap.addAction(input.createPointerDown(MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(input, Duration.ofMillis(200)));
        tap.addAction(input.createPointerUp(MouseButton.LEFT.asArg()));
        d.perform(ImmutableList.of(tap));
    }

    protected void tapElement(WebElement el) {
        tapElementAt(el, 0.5, 0.5);
    }

    protected void tapElementAt(WebElement el, double xPct, double yPct) {
        Rectangle elRect = el.getRect();
        Point point = new Point(
                elRect.x + (int) (elRect.getWidth() * xPct),
                elRect.y + (int) (elRect.getHeight() * yPct)
        );
        tapAtPoint(point);
    }

    /* Clicking on  element by inputting the element text with out wait */
    public void clickOnElementUsingLinkText(String text) {
        logger.info("[--->Clicking on link text " + text + "<---]");
        WebElement element = getThreadDriver().findElement(By.linkText(text));
        clickOnElement(element);
    }

    /* Clicking on element by using id and handling element intractable exception*/
    public void clickOnId(String id) {
        List<WebElement> Elems;
        try {
            WebElement result = getThreadDriver().findElement(By.id("" + id + ""));
            waitUntilElementIsLocated(result, 10);
            clickOnElement(result, id);
        } catch (ElementNotInteractableException ex) {
            Elems = (List<WebElement>) getThreadDriver().findElements(By.id("" + id + ""));
            for (WebElement Elem : Elems) {
                waitUntilElementIsLocated(Elem, 10);
                if (Elem.isDisplayed()) {
                    clickOnElement(Elem);
                    waitFor(1);
                    break;
                }
            }
        }
    }

    public void enabledIdUsingJS(String id) {
        WebElement result = getThreadDriver().findElement(By.id(id));
        ((JavascriptExecutor) ((RemoteWebDriver) getThreadDriver())).executeScript("arguments[0].disabled = false;", result);
    }

    /* Clicking on element by using id with out wait*/
    public void clickOnIdStartsWith(String id) {
        WebElement result = getThreadDriver().findElement(By.cssSelector("*[id^=\"" + id + "\"]"));
        logger.info("[--->Clicking on id starts with  " + id + "<---]");
        clickOnElement(result);
    }

    /* Clicking on element starts with id and with out wait */
    public void clickOnIdEndsWith(String id) {
        WebElement result = getThreadDriver().findElement(By.cssSelector("*[id$=\"" + id + "\"]"));
        logger.info("[--->Clicking on id ends with  " + id + "<---]");
        clickOnElement(result);
    }

    /* Clicking on element contains the text with p tag and with out wait */
    public void clickOnElementContainsTextPTag(String text) {
        WebElement result = getThreadDriver().findElement(By.xpath("//p[contains(text(),'" + text + "')]"));
        waitUntilTextToBePresent(result, text);
        result.click();
    }

    /* Clicking on element contains the text with A tag and with out wait */
    public void clickOnElementContainsTextATag(String text) {
        WebElement result = getThreadDriver().findElement(By.xpath("//a[contains(text(),'" + text + "')]"));
        waitUntilTextToBePresent(result, text);
        result.click();
    }

    /* Clicking on web element with out wait */
    public void clickOnElement(WebElement element, String text) {
        clickOnElement(element);
    }

    /* Mouse over on element by passing element text using actions class */
    public void mouseHoverOnElement(String element) {
        WebElement result = getThreadDriver().findElement(By.xpath("//*[contains(text(),'" + element + "')]"));
        waitUntilTextToBePresent(result, element);
        Actions actions = new Actions(getThreadDriver());
        actions.moveToElement(result).perform();
    }

    /* Scroll to web element and Mouse over using actions class*/
    public void mouseHoverToElement(WebElement element) {
        Actions actions = new Actions(getThreadDriver());
        ((JavascriptExecutor) getThreadDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        //   actions.moveToElement(element).perform();
        waitFor(1);
    }

    /* Mouse over on element by passing element link text for A tag using actions class */
    public void mouseHoverToElementLink(String link) {
        List<WebElement> linkList = getThreadDriver().findElements(By.xpath("//a[contains(text(),'" + link + "')]"));
        for (WebElement element : linkList) {
            if (element.getText().equals(link)) {
                logger.info("[--->Hovering on element contains text :" + link + "<---]");
                waitUntilTextToBePresent(element, link);
                Actions actions = new Actions(getThreadDriver());
                actions.moveToElement(element).perform();
                waitFor(1);
                break;
            }
        }
    }

    /* Returning the element text by passing the text */
    public boolean verifyTextMessage(String text) {
        logger.info("[--->Verifying text message " + text);
        waitFor(1);
        WebElement result = null;

        try {
            result = getThreadDriver().findElement(By.xpath("//*[contains(text(),\"" + text + "\")]"));
            logger.info("[--->Element : " + result.toString() + "found with text " + result.getText() + "<---]");
        } catch (NoSuchElementException nse) {
            logger.info("[--->No Element found with text " + text + "<---]");
        }
        assert result != null;
        if (!result.isDisplayed()) {
            return getTextFromElement(getDisplayedElement(text)).contains(text);
        }
        waitUntilTextToBePresent(result, text);
        return getTextFromElement(result).contains(text);
    }

    /* Returning the element text by passing the different text's */
    public boolean verifyEitherTextMessage(String text1, String text2) {
        logger.info("[--->Verifying text message " + text1 + "<---]");
        waitFor(1);
        try {
            WebElement result1 = null;

            result1 = getThreadDriver().findElement(By.xpath("//*[contains(text(),\"" + text1 + "\")]"));
            logger.info("[--->Element : " + result1.toString() + "found with text " + result1.getText() + "<---]");
            if (!result1.isDisplayed()) {
                return getTextFromElement(getDisplayedElement(text1)).contains(text1);
            }

            waitUntilTextToBePresent(result1, text1);
            return getTextFromElement(result1).contains(text1);
        } catch (NoSuchElementException nse) {
            WebElement result2 = null;

            logger.info("[--->No Element found with text " + text2 + "<---]");
            result2 = getThreadDriver().findElement(By.xpath("//*[contains(text(),\"" + text2 + "\")]"));
            logger.info("[--->Element : " + result2.toString() + "found with text " + result2.getText() + "<---]");
            if (!result2.isDisplayed()) {
                return getTextFromElement(getDisplayedElement(text2)).contains(text2);
            }

            waitUntilTextToBePresent(result2, text2);
            return getTextFromElement(result2).contains(text2);
        }
    }

    /* Returning the web element if the element is present otherwise return null*/
    public WebElement getDisplayedElement(String text) {
        List<WebElement> elements = null;
        WebElement we = null;
        try {
            elements = getThreadDriver().findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
            for (WebElement webElement : elements) {
                if (webElement.isDisplayed() && text.equalsIgnoreCase(webElement.getText())) {
                    logger.info("[--->Element found with text : " + text + "<---]");
                    we = webElement;
                }
            }
        } catch (Exception e) {
            logger.info("[--->No element found with text : " + text + "<---]");
        }
        return we;
    }

    /* Returning the web element actual text by passing the id and starts with expected text*/
    public boolean verifyTextMessageUsingId(WebElement id, String text) {
        waitUntilTextToBePresent(id, text);
        return getTextFromElement(id).startsWith(text);
    }

    /* Returning the web element actual text by passing the expected text matches with actual text*/
    public boolean verifyTextMessageOnThePage(String text) {
        WebElement result = getThreadDriver().findElement(By.xpath("//*[contains(text(),\"" + text + "\")]"));
        waitUntilTextToBePresent(result, text);
        return getTextFromElement(result).matches(text);
    }

    /* Returning the web element actual text by passing the  expected text with h1 tag*/
    public String verifyTextMessageH1Tags(String text) {
        WebElement result = getThreadDriver().findElement(By.xpath("//h1[contains(text(),'" + text + "')]"));
        return getTextFromElement(result).replaceAll("\n", " ");
    }

    /* Returning the web element actual text by passing the  expected text with A tag*/
    public boolean verifyTextMessageATags(String text) {
        int linkSize = getThreadDriver().findElements(By.xpath("//a[contains(text(),'" + text + "')]")).size();
        logger.info("[--->Element fount with text " + text + "<---]");
        return (linkSize > 0) ? true : false;
    }

    /* Returning the web element actual text by passing the  expected text and by replacing all the '/n' characters*/
    public String verifyTextMessageReplaceAll(String text) {
        WebElement result = getThreadDriver().findElement(By.xpath("//*[contains(text(),'" + text + "')]"));
        waitUntilTextToBePresent(result, text);
        return getTextFromElement(result).replaceAll("\n", " ");
    }

    /* select dropdown value by visible text*/
    public void selectByVisibleText(WebElement element, String text) {
        waitUntilElementToBeClickable(element, 15);
        Select select = new Select(element);
        select.selectByVisibleText(text);

    }

    /* switch to parent tab by handling the multiple tab*/
    public void switchToPreviousTab() {
        Set<String> allWindows = getThreadDriver().getWindowHandles();
        ArrayList<String> tabs = new ArrayList<>(allWindows);
        getThreadDriver().switchTo().window(tabs.get(0));
    }

    /* switch to child tab by handling the multiple tab*/
    public void switchTab() {
        Set<String> allWindows = getThreadDriver().getWindowHandles();
        ArrayList<String> tabs = new ArrayList<>(allWindows);
        getThreadDriver().switchTo().window(tabs.get(1));
    }

    /* switch to child2 tab by handling the multiple tab*/
    public void switchTab2() {
        Set<String> allWindows = getThreadDriver().getWindowHandles();
        ArrayList<String> tabs = new ArrayList<>(allWindows);
        getThreadDriver().switchTo().window(tabs.get(2));
    }

    /* Capturing the screenshots based on scenario name */
    public void getScreenshot(Scenario scenario) {
        try {
            logger.info("[--->Initializing for screenshot<---]");
            moveToCurrentTAB();
            //Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(getThreadDriver());
            Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.scaling(2)).takeScreenshot(getThreadDriver());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(screenshot.getImage(), "jpg", baos);
            byte[] bytes = baos.toByteArray();
            scenario.attach(bytes, "image/jpg", scenario.getName());
            baos.close();
            logger.info("[--->Screenshot taken<---]");
        } catch (Exception e) {
            logger.info("[--->Unable to take screenshot, with error message: " + e.getMessage() + "<---]");
        }
    }

    /* switch to parent tab by handling the multiple tab*/
    private void moveToCurrentTAB() {
        String originalWindow = getThreadDriver().getWindowHandle();
        for (String windowHandle : getThreadDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                getThreadDriver().switchTo().window(originalWindow);
                break;
            }
        }
    }

    /* returning generated random email */
    public String generateRandomEmail() {
        return new DataHelper().getRandomEmail();
    }

    /* returning status code of get method */
    public int responseCodeViaGet(String url) {
        return RestAssured.get(WebURLHelper.getWebUrl() + url).statusCode();
    }

    /* selecting dropdown value by using visible text option and handling element click intercepted expection */
    public void selectByVisibleTextFromDropDown(WebElement element, String str) {
        Select selectEleme=new Select(element);
        waitUntilElementToBeClickable(element, 4);
        clickOnElement(element);
        if("Sort by Date Posted ASC".equalsIgnoreCase(str)) {
            getDriverWithUrl(WebURLHelper.getWebUrl(),getURL("Sort by Date Posted ASC"));
                 }else{
            clickOnElementUsingText(str);
        }
      }

    public void selectByVisibleTextFromDropDownWithJS(WebElement element, String str) {
        waitUntilElementToBeClickable(element, 4);
        clickOnElementWithJS(element);
        clickOnElementUsingTextWithJS(str);
        //  getThreadDriver().findElement(By.xpath("//*[text()=\"" + str + "\"]")).click();
    }

    public void selectByVisibleTextFromDropDownUsingJS(WebElement element, String str) {
        try {
            waitUntilElementToBeClickable(element, 4);
            ((JavascriptExecutor) getThreadDriver()).executeScript("var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].text == arguments[1]){ select.options[i].selected = true; } }", element, str);
        } catch (ElementClickInterceptedException e) {
            waitFor(1);
            ((JavascriptExecutor) getThreadDriver()).executeScript("window.scrollBy(0,-350)", "");
            waitUntilElementToBeClickable(element, 4);
            ((JavascriptExecutor) getThreadDriver()).executeScript("var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].text == arguments[1]){ select.options[i].selected = true; } }", element, str);
        }
    }

    /* selecting dropdown value by using Index */
    public void selectByIndexFromDropDown(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public WebElement getElementById(String id) {
        return getThreadDriver().findElement(By.id(id));
    }

    /* selecting dropdown value by using value */
    public void selectByValueFromDropDown(WebElement element, String value) {
        waitUntilElementToBeClickable(element, 5);
        clickOnElementWithJS(element);
        clickOnAttributeValueOnInputTag(value);
//        JavascriptExecutor jse = (JavascriptExecutor) getThreadDriver();
//        jse.executeScript("arguments[0].value="+value, element);
    }

    /* scroll down to element by passing x and y co-ordinates of elements using javascript executor*/
    public void scrollDown(int y, int x) {
        JavascriptExecutor js = (JavascriptExecutor) getThreadDriver();
        js.executeScript("window.scrollBy(" + y + "," + x + ")");
        waitFor(1);
    }
    public void scrollDownMobile(int y, int x) {
        JavascriptExecutor js = (JavascriptExecutor) getThreadDriver();
        js.executeScript("window.scrollBy(" + y + "," + x + ")");
        waitFor(1);
    }

    /* scroll up to element by passing x and y co-ordinates of elements using java script executor*/
    public void scrollUp(int y, int x) {
        JavascriptExecutor js = (JavascriptExecutor) getThreadDriver();
        js.executeScript("window.scrollBy(" + y + "," + "-" + x + ")");
        waitFor(1);
    }

    /* switch to window by a specified tab number*/
    public void switchWindowIndex(int number) {
        logger.info("[--->Navigate to new window<---]");
        Set<String> allWindows = getThreadDriver().getWindowHandles();
        ArrayList<String> tabs = new ArrayList<>(allWindows);
        getThreadDriver().switchTo().window(tabs.get(number));
    }

    /* verify the p tag text by passing the partial p tag text and full text to be verified*/
    public String verifyTextMessagePTags(String ptagText, String textTobeVerified) {
        waitFor(1);
        WebElement result = getThreadDriver().findElement(By.xpath("//*[contains(text(),\"" + ptagText + "\")]"));
        logger.info("[--->" + result.getText() + " : element with text : " + ptagText + "<---]");
        if (result.isDisplayed() && result.getText().equals(textTobeVerified)) {
            return getTextFromElement(result);
        } else {
            try {
                List<WebElement> elements = getThreadDriver().findElements(By.xpath("//*[contains(text(),\"" + ptagText + "\")]"));
                for (WebElement webElement : elements) {
                    if (webElement.isDisplayed() && textTobeVerified.equalsIgnoreCase(webElement.getText())) {
                        return getTextFromElement(webElement);
                    }
                }
            } catch (Exception e) {
                logger.info("[--->No element found with text : " + ptagText + "<---]");
            }
        }
        waitUntilTextToBePresent(result, ptagText);
        return getTextFromElement(result);
    }

    /*Returning the div tag actual text by passing the expected text and replacing the '/n' characters*/
    public String verifyTextMessageDivTags(String text) {
        WebElement result = getThreadDriver().findElement(By.xpath("//div[contains(text(),'" + text + "')]"));
        waitUntilTextToBePresent(result, text);
        return getTextFromElement(result).replaceAll("\n", " ");
    }

    /*Returning the h2 tag actual text by passing the expected text*/
    public String verifyTextMessageH2Tags(String text) {
        WebElement result = null;
        String textOnPage = null;
        try {
            result = getThreadDriver().findElement(By.xpath("//h2[contains(text(),'" + text + "')]"));
            logger.info("[--->text found on the page is : " + result.getText() + "<---]");
            textOnPage = result.getText();
        } catch (NoSuchElementException nse) {
            logger.info("[--->element not found with full text : " + text + "now trying to find with any of the word in a given string " + "<---]");
            for (String word : text.split(" ")) {
                logger.info("[--->finding an element with word " + word + "<---]");
                result = getThreadDriver().findElement(By.xpath("//h2[contains(text(),'" + word + "')]"));
                logger.info("[--->text on page : " + result.getText() + "<---]");
                if (result.getText().equals(text)) {
                    textOnPage = result.getText();
                    break;
                }
            }
        }
        return textOnPage;
    }

    /*Returning the th tag actual text by passing the expected text*/
    public String verifyTextMessageThTags(String text) {
        WebElement result = getThreadDriver().findElement(By.xpath("//th[contains(text(),'" + text + "')]"));
        waitUntilTextToBePresent(result, text);
        return getTextFromElement(result);
    }

    /*Returning the font tag actual text by passing the expected text*/
    public String verifyTextMessageFontTags(String text) {
        WebElement result = getThreadDriver().findElement(By.xpath("//font[contains(text(),'" + text + "')]"));
        waitUntilTextToBePresent(result, text);
        return getTextFromElement(result);
    }

    /*Returning the p tag actual text by passing the expected text and replacing the '/n' characters*/
    public String verifyTextMessagePTagsReplaceAll(String text) {
        WebElement result = getThreadDriver().findElement(By.xpath("//p[contains(text(),'" + text + "')]"));
        waitUntilTextToBePresent(result, text);
        return getTextFromElement(result).replaceAll("\n", " ");
    }

    /*Returning the textarea tag actual text by passing the expected text */
    public String verifyTextMessageTextareaTags(String text) {
        WebElement result = getThreadDriver().findElement(By.xpath("//textarea[contains(text(),'" + text + "')]"));
        waitUntilTextToBePresent(result, text);
        return getTextFromElement(result);
    }

    /*Returning the span tag actual text by passing the expected text */
    public String verifyTextMessageSpanTag(String text) {
        WebElement result = getThreadDriver().findElement(By.xpath("//span[contains(text(),'" + text + "')]"));
        waitUntilTextToBePresent(result, text);
        return getTextFromElement(result);
    }

    /*Returning the actual text on web page by passing the expected text */
    public boolean verifyPageContainsText(String text) {
        return getThreadDriver().getPageSource().contains(text);
    }

    /* sending text to web element by passing id of element and text*/
    public void fillFieldUsingIdAndText(String id, String text) {
        List<WebElement> Elems;
        try {
            WebElement we = getThreadDriver().findElement(By.id(id));
            waitUntilElementIsLocated(we, 15);
            this.clearTextFromField(we);
            we.sendKeys(text);
            //we.sendKeys(Keys.ENTER);
        } catch (ElementNotInteractableException ex) {
            Elems = (List<WebElement>) getThreadDriver().findElements(By.id("" + id + ""));
            for (WebElement Elem : Elems) {
                if (Elem.isDisplayed()) {
                    Elem.sendKeys(text);
                    break;
                }
            }
        }
    }

    /* sending text to web element by passing id  and adding random generated number to text*/
    public void fillRandomTextUsingId(String id) {
        WebElement element = getThreadDriver().findElement(By.id(id));
        waitUntilElementIsLocated(element, 15);
        int number = RandomUtils.nextInt();
        String randomText = DataHelper.getLatestJobTitle() + number;
        element.sendKeys(randomText);
    }

    /* Returning random string by inputting the string length*/
    public String getRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    /* Returning label tag actual text by inputting the expected text*/
    public String verifyTextLabelTag(String text) {
        WebElement result = getDisplayedElement(text);
        waitUntilTextToBePresent(result, text);
        return getTextFromElement(result);
    }

    /* removing the text from webelement*/
    public void clearTextFromField(WebElement element) {
        element.clear();
    }

    /* returning the boolean value by checking the A tag text presence*/
    public boolean isAnchorElementDisplayed(String text) {
        getThreadDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        boolean isAnchorElementPresent = getThreadDriver().findElements(By.xpath("//a[text()=\"" + text + "\"]")).size() > 0;
        getThreadDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return isAnchorElementPresent;
    }

    /* returning the boolean value by checking the text presence*/
    public boolean isElementOrTextDisplayed(String text) {
        getThreadDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        boolean isElementPresent = getThreadDriver().findElements(By.xpath("//*[contains(text(),\"" + text + "\")]")).size() > 0;
        getThreadDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return isElementPresent;
    }

    /* returning boolean value by checking the element presence and inputting the text*/
    public boolean isElementDisplay(String text) {
        boolean isElementPresent = false;
        try {
            WebElement element = getThreadDriver().findElement(By.xpath("//*[contains(text(),\"" + text + "\")]"));
            if (element.isDisplayed()) {
                isElementPresent = true;
            }
        } catch (Exception e) {
            isElementPresent = false;
        }
        return isElementPresent;
    }

    /* returning boolean value by checking the element presence and inputting the web element*/
    public boolean verifyThatElementIsDisplayed(WebElement element) {
        waitUntilElementIsLocated(element, 15);
        return element.isDisplayed();
    }

    /* returning boolean value by checking the element presence and inputting the web element*/
    public boolean isElementDisplay(WebElement element) {
        boolean elementFound = false;
        try {
            if (element.isDisplayed()) {
                logger.info("[--->the element is displayed  : " + "<---]");
                elementFound = true;
            }
        } catch (Exception e) {
            logger.info("[--->the element is not displayed  : " + "<---]");
            elementFound = false;
        }
        return elementFound;
    }

    /* returning boolean value by checking the element presence and inputting the web element locator(id/xpath)*/
    public boolean verifyThatElementIsDisplayedUsingLocator(String locator, String locatorInfo) {
        boolean elementDisplayed;

        if (locator.equals("id")) {
            getThreadDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            elementDisplayed = getThreadDriver().findElements(By.id(locatorInfo)).size() > 0;
            getThreadDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        } else {
            getThreadDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            elementDisplayed = getThreadDriver().findElements(By.xpath(locatorInfo)).size() > 0;
            getThreadDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        }
        return elementDisplayed;

    }

    /* returning the actual text of h3 tag by inputting the text*/
    public String verifyTextMessageH3Tags(String text) {
        WebElement result = getThreadDriver().findElement(By.xpath("//h3[contains(text(),'" + text + "')]"));
        waitUntilTextToBePresent(result, text);
        return getTextFromElement(result);
    }

    /* returning the actual text of h4 tag by inputting the text*/
    public String verifyTextMessageH4Tags(String text) {
        WebElement result = getThreadDriver().findElement(By.xpath("//h4[contains(text(),'" + text + "')]"));
        waitUntilTextToBePresent(result, text);
        return getTextFromElement(result);
    }

    /* returning boolean value by checking the element presence and inputting the web element locator(id)*/
    public boolean verifyElementWithId(String link, String elementId) {
        boolean elementPresent = false;
        logger.info("[--->Checking for the Web Element :" + link + " with elementId :  " + elementId + "<---]");
        getThreadDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        if (getThreadDriver().findElements(By.id(elementId)).size() != 0) {
            getThreadDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            elementPresent = true;
            logger.info("[--->element found with id " + elementId + " and text : " + getThreadDriver().findElement(By.id(elementId)).getText() + "<---]");
        }
        return elementPresent;
    }

    /*Accepting the web alerts*/
    public void acceptAlertPopUp() {
        logger.info("[--->click on Alert popup : " + getThreadDriver().switchTo().alert().getText() + "<---]");
        String MainWindow = getThreadDriver().getWindowHandle();
        logger.info("[--->on main window handle : " + MainWindow);
        getThreadDriver().switchTo().alert().accept();
        waitFor(1);
    }

    /* verifying the breadcrubms by passing the class name*/
    public String verifyBreadCrumbs(String className) {
        String locator = "//*[@class='" + className + "']";
        String breadCrumbsOnPage = getTextFromElement(getThreadDriver().findElement(By.xpath(locator)));
        String cleanString = breadCrumbsOnPage.replaceAll("\r", " ").replaceAll("\n", " ");
        logger.info("[--->Bread crumbs on the page : <" + cleanString + ">" + "<---]");
        return cleanString;
    }

    /*returning the element location by inputting the text of element*/
    public Point getElementLocation(String text) {
        Point location = null;
        try {
            WebElement webElement = getThreadDriver().findElement(By.xpath("//*[contains(text(),'" + text + "')]"));
            logger.info("[--->found the web element with text : " + webElement.toString() + "---" + webElement.getText() + "<---]");
            location = webElement.getLocation();
        } catch (org.openqa.selenium.NoSuchElementException nse) {
            logger.info("[--->Given text not found : " + text + "<---]");
        }
        return location;
    }

    /*returning the boolean value by checking element presence and inputting the id of element */
    public boolean verifyPageContainsElement(String id) {
        boolean found = false;
        logger.info("[--->Checking for the Web Element with elementId :  " + id + "<---]");
        getThreadDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        if (getThreadDriver().findElements(By.id(id)).size() != 0) {
            getThreadDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            found = true;
        }
        return found;
    }

    /* sending the text to element*/
    public void sendTextToElement(WebElement element, String str) {
        waitUntilElementIsLocated(element, 10);
        element.sendKeys(str);
    }

    /* Scroll to element by retrieving the x and y co-ordinates of element text*/
    public void scrollToAnElement(String text) {
        Point location = getElementLocation(text);
        scrollDown(location.x, location.y);
    }

    /* returning the drop down selected text*/
    public String getFirstSelectedOption(WebElement element) {
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }

    /* returning the sorted date name list in ascending order*/
    public List<String> getSortedDatesAsc(List<String> list) {
        Collections.sort(list, new Comparator<String>() {
            DateFormat f = new SimpleDateFormat("MM/dd/yyyy");

            @Override
            public int compare(String o1, String o2) {
                try {
                    return f.parse(o1).compareTo(f.parse(o2));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
        logger.info("[--->Sorting date name by asc order" + "<---]");
        return list;
    }

    /* returning Collection of String Stream Items into List*/
    public List<String> getDataList(List<WebElement> elements) {
        return elements.stream().map(s -> s.getText().toLowerCase()).collect(Collectors.toList());
    }

    public List<String> getDataListForDate(List<WebElement> elements) {
        return elements.stream().map(s -> s.getText().split(" ")[0].toLowerCase()).collect(Collectors.toList());
    }

    /* returning Collection of double Stream Items into List*/
    public List<Double> getNumberDataList(List<WebElement> elements) {
        return elements.stream().map(s -> Double.parseDouble(s.getText())).collect(Collectors.toList());
    }

    /* returning the sorted date name list in descending order*/
    public List<String> getSortedDatesDesc(List<String> list) {
        Collections.sort(list, new Comparator<String>() {
            DateFormat f = new SimpleDateFormat("MM/dd/yyyy");

            @Override
            public int compare(String o1, String o2) {
                try {
                    return f.parse(o2).compareTo(f.parse(o1));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
        logger.info("[--->Sorting date name by desc order" + "<---]");
        return list;
    }

    /* returning the  list of elements in descending order*/
    public List<String> getListByDescOrder(List<WebElement> elements) {
        return getDataList(elements).stream().map(String::toLowerCase).sorted(Collections.reverseOrder()).collect(Collectors.toList());
    }

    public List<String> getListByDescOrderForDate(List<WebElement> elements) {
        return getDataListForDate(elements).stream().map(String::toLowerCase).sorted(Collections.reverseOrder()).collect(Collectors.toList());
    }

    /* returning the  number list of elements in descending order*/
    public List<Double> getNumberListByDescOrder(List<WebElement> elements) {
        return getNumberDataList(elements).stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
    }

    /* returning the  list of elements in ascending order*/
    public List<String> getListByAscOrder(List<WebElement> elements) {
        return getDataList(elements).stream().map(String::toLowerCase).sorted().collect(Collectors.toList());
    }

    public List<String> getListByAscOrderForDate(List<WebElement> elements) {
        return getDataListForDate(elements).stream().map(String::toLowerCase).sorted().collect(Collectors.toList());
    }

    public List<Double> getNumberListByAscOrder(List<WebElement> elements) {
        return getNumberDataList(elements).stream().sorted().collect(Collectors.toList());
    }


    /* returning the actual text by comparing the actual text(from application) and expected text(from feature) */
    public String getTextFromElementUsingXpath(String text, String xpath) {
        List<WebElement> we = getThreadDriver().findElements(By.xpath(xpath));
        String textFromElement = "";
        for (WebElement element : we) {
            String s = getTextFromElement(element);
            logger.info("[--->element found with text : " + s + "<---]");
            if (s.trim().equalsIgnoreCase(text)) {
                textFromElement = s.trim();
                break;
            }
        }
        return textFromElement;
    }

    /* returning the boolean value(true/false) by comparing the actual text(from application) and expected text(from feature) */
    public boolean compareTextFromElementUsingXpath(String xpath, String text) {
        logger.info("[--->xpath is" + xpath + "<---]");
        List<WebElement> we = getThreadDriver().findElements(By.xpath(xpath));
        boolean textFromElement = false;
        for (WebElement element : we) {
            String s = getTextFromElement(element);
            logger.info("[--->element found with text : " + s + "<---]");
            if (s.trim().equals(text.trim())) {
                textFromElement = true;
                break;
            }
        }
        return textFromElement;
    }

    /* clicking on element using atrribute "attribute name : value" text for input tag*/
    public void clickOnAttributeValueOnInputTag(String text) {
        WebElement result = getThreadDriver().findElement(By.xpath("//*[@value='" + text + "']"));
        logger.info("[--->Clicking on value contains" + text + "<---]");
        result.click();

    }

    /* Verifying the Element presence using the xpath and size ofe element */
    public boolean checkElementPresence(String xpath) {
        logger.info("[--->check the element presence" + "<---]");
        boolean elementPresence = false;

            if (getThreadDriver().findElements(By.xpath(xpath)).size() > 0) {
                elementPresence = true;

            }
        logger.info("[---> element presence for the xpath:" + xpath+"::elementPresence:"+elementPresence+"<---]");
        return elementPresence;
    }

    /* return the text url from URL constants class by replacing the special characters " " and "_"*/
    public synchronized String getURL(String textUrl) {
        URLConstants urlConstantsObj = new URLConstants();
        if (!textUrl.contains("/")) {
            String url = "";
            String[] splitUrlTexts = textUrl.split(" ");
            for (int i = 0; i < splitUrlTexts.length; i++) {
                if (i == (splitUrlTexts.length - 1)) {
                    url += splitUrlTexts[i].toUpperCase();
                } else {
                    url += splitUrlTexts[i].toUpperCase() + "_";
                }
            }
            return urlConstantsObj.getConstantsURL(url);
        } else {
            return textUrl;
        }
    }

    /* returning the title of page */
    public String getTitleOfThePage() {
        logger.info("[--->Getting title of the page " + "<---]");
        return getThreadDriver().getTitle();
    }

    /* returning the current runner name*/
    public String getRunnerName() {
        return (((Map) threadLocalMap.get()).get("runnerClass")).toString();
    }

    public boolean hasDisabledClass(WebElement element) {
        String classes = element.getAttribute("class");
        for (String c : classes.split(" ")) {
            if (c.equals("disabled")) {
                return true;
            }
        }
        return false;
    }

    public void clickAllowPopUp() {
        try {
            System.out.println("context-->" + ((AndroidDriver) getThreadDriver()).getContext());
            ((AndroidDriver) getThreadDriver()).context("NATIVE_APP");
            if (isElementDisplay(getThreadDriver().findElement(By.xpath("//android.widget.Button[@text='Allow']")))) {
                getThreadDriver().findElement(By.xpath("//android.widget.Button[@text='Allow']")).click();
            }
            //  ((AndroidDriver)getThreadDriver()).context("WEBVIEW_com.dayizhihui.dayishi.hpv");
            ((AndroidDriver) getThreadDriver()).context("CHROMIUM");

        } catch (Exception e) {

        }
    }

    public void setFailedCountSummary(String tagName) {
        int count;
        logger.info("[--->setFailedCountSummary<---]=====>" + tagName);
        ExcelUtil excelUtil = new ExcelUtil();
        String verticalName = excelUtil.getVerticalName(tagName);

        switch (verticalName) {
            case "Recruiters":
                count = verticalScenarioCountPojo.getFailedRecruitersCount();
                logger.info("[--->Failed:before  Recruiters :" + count + "<---]");
                verticalScenarioCountPojo.setFailedRecruitersCount(count + 1);
                logger.info("[--->Failed:after  Recruiters :" + verticalScenarioCountPojo.getFailedRecruitersCount() + "<---]");
                break;
            case "Partnerships":
                count = verticalScenarioCountPojo.getFailedPartnershipsCount();
                logger.info("[--->Failed:before  Partnerships :" + count + "<---]");
                verticalScenarioCountPojo.setFailedPartnershipsCount(count + 1);
                logger.info("[--->Failed:after  Partnerships :" + verticalScenarioCountPojo.getFailedPartnershipsCount() + "<---]");
                break;
            case "Traffic":
                count = verticalScenarioCountPojo.getFailedTrafficCount();
                logger.info("[--->Failed:before  Traffic :" + count + "<---]");
                verticalScenarioCountPojo.setFailedTrafficCount(count + 1);
                logger.info("[--->Failed:after  Traffic :" + verticalScenarioCountPojo.getFailedTrafficCount() + "<---]");
                break;
            case "Candidate":
                count = verticalScenarioCountPojo.getFailedCandidateCount();
                logger.info("[--->Failed:before  Candidate :" + count + "<---]");
                verticalScenarioCountPojo.setFailedCandidateCount(count + 1);
                logger.info("[--->Failed:after  Candidate :" + verticalScenarioCountPojo.getFailedCandidateCount() + "<---]");
                break;
            case "Search":
                count = verticalScenarioCountPojo.getFailedSearchCount();
                logger.info("[--->Failed:before  Search :" + count + "<---]");
                verticalScenarioCountPojo.setFailedSearchCount(count + 1);
                logger.info("[--->Failed:after  Search :" + verticalScenarioCountPojo.getFailedCandidateCount() + "<---]");
                break;
        }


    }

    public void setPassedCountSummary(String tagName) {
        int count;
        logger.info("[--->setPassedCountSummary<---]=====>" + tagName);
        ExcelUtil excelUtil = new ExcelUtil();
        String verticalName = excelUtil.getVerticalName(tagName);

        switch (verticalName) {

            case "Recruiters":
                count = verticalScenarioCountPojo.getPassedRecruitersCount();
                logger.info("[--->Passed:before  Recruiters :" + count + "<---]");
                verticalScenarioCountPojo.setPassedRecruitersCount(count + 1);
                logger.info("[--->Passed:after  Recruiters :" + verticalScenarioCountPojo.getPassedRecruitersCount() + "<---]");
                break;
            case "Partnerships":
                count = verticalScenarioCountPojo.getPassedPartnershipsCount();
                logger.info("[--->Passed:before  Partnerships :" + count + "<---]");
                verticalScenarioCountPojo.setPassedPartnershipsCount(count + 1);
                logger.info("[--->Passed:after  Partnerships :" + verticalScenarioCountPojo.getPassedPartnershipsCount() + "<---]");
                break;
            case "Traffic":
                count = verticalScenarioCountPojo.getPassedTrafficCount();
                logger.info("[--->Passed:before  Traffic :" + count + "<---]");
                verticalScenarioCountPojo.setPassedTrafficCount(count + 1);
                logger.info("[--->Passed:after  Traffic :" + verticalScenarioCountPojo.getPassedTrafficCount() + "<---]");
                break;
            case "Candidate":
                count = verticalScenarioCountPojo.getPassedCandidateCount();
                logger.info("[--->Passed:before  Candidate :" + count + "<---]");
                verticalScenarioCountPojo.setPassedCandidateCount(count + 1);
                logger.info("[--->Passed:after  Candidate :" + verticalScenarioCountPojo.getPassedCandidateCount() + "<---]");
                break;
            case "Search":
                count = verticalScenarioCountPojo.getPassedSearchCount();
                logger.info("[--->Passed:before  Search :" + count + "<---]");
                verticalScenarioCountPojo.setPassedSearchCount(count + 1);
                logger.info("[--->Passed:after  Search :" + verticalScenarioCountPojo.getPassedSearchCount() + "<---]");
                break;
        }
    }

    public ByteArrayOutputStream getScreenshotAsBaos() {
        ByteArrayOutputStream baos = null;
        try {
            logger.info("[--->Initializing for screenshot<---]");
            moveToCurrentTAB();
            // Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(getThreadDriver());
            Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.scaling(2)).takeScreenshot(getThreadDriver());
            baos = new ByteArrayOutputStream();
            ImageIO.write(screenshot.getImage(), "PNG", baos);
            baos.close();
            logger.info("[--->Screenshot taken<---]");
        } catch (Exception e) {
            logger.info("[--->Unable to take screenshot, with error message: " + e.getMessage() + "<---]");
        }
        return baos;
    }


    public void swipeScreen(Direction dir) {
        System.out.println("swipeScreen(): dir: '" + dir + "'"); // always log your actions

        // Animation default time:
        //  - Android: 300 ms
        //  - iOS: 200 ms
        // final value depends on your app and could be greater
        final int ANIMATION_TIME = 200; // ms

        final int PRESS_TIME = 200; // ms

        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;

        // init screen variables
        Dimension dims = ((AndroidDriver)getThreadDriver()).manage().window().getSize();

        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

        switch (dir) {
            case DOWN: // center of footer
                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
                break;
            case UP: // center of header
                pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
                break;
            case LEFT: // center of left side
                pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
                break;
            case RIGHT: // center of right side
                pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            new TouchAction(((AndroidDriver)getThreadDriver()))
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

}