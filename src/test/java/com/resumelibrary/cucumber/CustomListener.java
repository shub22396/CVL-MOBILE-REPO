package com.resumelibrary.cucumber;

import com.resumelibrary.utilities.*;
import org.testng.*;

import com.lambdatest.tunnel.Tunnel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomListener extends Utility implements ITestListener, IExecutionListener {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CustomListener.class);
    List<Tunnel>  tunnelObject=null;
    String GREEN_BACKGROUND = "\033[42m";
    String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";
    String RED_BOLD_BRIGHT = "\033[1;91m";
    String ANSI_RESET = "\u001B[0m";
    String GREEN_BOLD_BRIGHT = "\033[1;92m";
    String WHITE_BOLD_BRIGHT = "\033[1;97m";
    @Override
    public void onExecutionStart() {
        deviceNames.put("TestNG-PoolService-2","Galaxy S10+@11");
        deviceNames.put("TestNG-PoolService-1","Galaxy Note20@11");
        deviceNames.put("TestNG-PoolService-0","Galaxy S20+@11");
        deviceNames.put("TestNG-PoolService-3","Galaxy Note10+@11");
        deviceNames.put("TestNG-PoolService-4","Galaxy S10@11");
        deviceNames.put("TestNG-PoolService-5","Galaxy S20+@11");
        tunnelObject=new ArrayList<Tunnel>();
        boolean flag=false;
        String username = PropertyFileReader.getInstance().getProperty("lambdaUsername");
        String  lamdaUserName= WebURLHelper.getParameterFromEnvOrSysParam("lamdaUserName", username);
        String accessKey = PropertyFileReader.getInstance().getProperty("lambdaAccessKey");
        String    lambdaAccessKey=  WebURLHelper.getParameterFromEnvOrSysParam("lambdaAccessKey", accessKey);
        String buildIdFromConfig = PropertyFileReader.getInstance().getProperty("lambdaBuildId");
        String buildId = WebURLHelper.getParameterFromEnvOrSysParam("BUILD_NUMBER", buildIdFromConfig);

        int noOfTunnels = Integer.parseInt(WebURLHelper.getParameterFromEnvOrSysParam("TUNNELS", PropertyFileReader.getInstance().getProperty("noOfTunnels")));
        //int threadCount = Integer.parseInt(System.getProperty("ThreadCount"));
        String tunnelName =WebURLHelper.getParameterFromEnvOrSysParam("TUNNELNAME", PropertyFileReader.getInstance().getProperty("tunnelName"));
        LOGGER.info("[--->tunnelName = " + tunnelName+buildId+"<---]");

        for(int j=0;j<noOfTunnels;j++){

            HashMap<String, String> option = new HashMap<String, String>();
            option.put("user", lamdaUserName);
            option.put("key", lambdaAccessKey);
            option.put("load-balanced","true");
            option.put("mitm", "true");
            option.put("sharedTunnel", "true");
            option.put("tunnelName", tunnelName+buildId);
            int i=0;
            do{
                  flag= startTunnel(option);
                i++;
            }while(!flag && i<2);
        }


        ASCIIArtGenerator artGen = new ASCIIArtGenerator();
        try {
            artGen.printText("RL Regression started");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ExcelUtil excelUtil = new ExcelUtil();
        excelUtil.createFailedExcel(buildId);
    }

    @Override
    public void onExecutionFinish() {
        try {
            for(int i=0;i<tunnelObject.size();i++){
                    tunnelObject.get(i).stopTunnel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ExcelUtil excelUtil=new ExcelUtil();
        excelUtil.createSummarySheet(verticalScenarioCountPojo);
        ASCIIArtGenerator artGen = new ASCIIArtGenerator();
        try {
            artGen.printText("RL Regression Finished");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onFinish(ITestContext arg0) {
        if (getThreadDriver() != null) {
            LOGGER.info("|----------------------------------------------------------------------------------------------------------------------------------|");
            LOGGER.info(RED_BOLD_BRIGHT + "[--->" + GREEN_BACKGROUND + WHITE_BOLD_BRIGHT + " browser closed for scenario (Place --3)  : " + arg0.getName() + ANSI_RESET + RED_BOLD_BRIGHT + "<---]" + ANSI_RESET);
            getThreadDriver().quit();
            LOGGER.info("|----------------------------------------------------------------------------------------------------------------------------------|");
        }
   }
    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
    }

    @Override
    public void onTestFailure(ITestResult arg0) {
   }

 @Override
    public void onTestSkipped(ITestResult arg0) {
     }

    @Override
    public void onTestStart(ITestResult arg0) {
   }

    @Override
    public void onTestSuccess(ITestResult arg0) {
    }

    public synchronized boolean startTunnel(Map options) {
        boolean flag = false;
        try{
            for(int i = 0; i < 5; ++i) {
                Thread.sleep(1000L);
            }
            Tunnel t=new Tunnel();
                flag = t.start(options);
            if(flag) {
                tunnelObject.add(t);
            }
        }catch (Exception e){
            flag=false;

        }
        return flag;
    }
}