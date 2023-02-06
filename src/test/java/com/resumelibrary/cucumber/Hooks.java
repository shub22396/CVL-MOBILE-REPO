package com.resumelibrary.cucumber;

import com.resumelibrary.utilities.PropertyFileReader;
import com.resumelibrary.utilities.Utility;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Hooks extends Utility {
    String GREEN_BACKGROUND = "\033[42m";
    String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";
    String RED_BOLD_BRIGHT = "\033[1;91m";
    String ANSI_RESET = "\u001B[0m";
    String GREEN_BOLD_BRIGHT = "\033[1;92m";
    String WHITE_BOLD_BRIGHT = "\033[1;97m";
    private static final Logger logger = LogManager.getLogger(Hooks.class);
    private static final String TEST_STATUS_SCRIPT = "browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"%s\", \"name\": \"%s\"}}";

    @Before
    public void before(){
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {

        if (scenario.getSourceTagNames().contains("@skip_hooks")) {
            logger.info("[--->In Hooks, cucumber after tearDown method, driver not initialized so don't need to quit driver ----> " + scenario.getSourceTagNames()+"<---]");
        }else {
            logger.info("[--->In Hooks, cucumber after tearDown method ---> "+"<---]");
            if (scenario.isFailed()) {
                scenario.log("[--->--------------------------------------------"+"<---]");;
                scenario.log("[--->CURRENT URL IS ----> " + getPresentURL()+"<---]");;
                scenario.log("[--->BROWSER NAME   ----> " + getBrowserName()+"<---]");
                scenario.log("[--->CURRENT TAG IS : " + scenario.getSourceTagNames()+"<---]");
                scenario.log("[--->--------------------------------------------"+"<---]");;
                getScreenshot(scenario);
           }else{
                if(System.getProperty("browserName").contains("lambda")) {
                    ((JavascriptExecutor) getThreadDriver()).executeScript("lambda-status=passed");
                }
            }
        }
            }
}