package com.Amex.core.ui;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import com.Amex.core.model.Resolution;

import java.util.List;

public class UICommon
{
    private static Playwright playwright;
    private static Browser browser;
    public static Page page;
    public static BrowserContext context;

    //Use this method to read browser type form environment variable
    public static void launchBrowser()
    {
        // Use this method to launch the browser with default parameters
        String browserType = System.getenv("BROWSER_TYPE");
        if(browserType == null)
            {
                browserType = "msedge";
            }


        Resolution resolutionEnum = Resolution.LARGE;
        String resolution = System.getenv("RESOLUTION");
        if(resolution != null)
        {
            resolutionEnum = Resolution.getResolution(resolution);
        }
        launchBrowser(browserType,resolutionEnum);
    }

    /***
     * Use this method to launch a browser
     * @param browserType - The type of browser to be launched
     * @param resolution - The resolution of the browser
     */
    //Use this method to Launch a specific browser, pass the browser type as a parameter using the ENUM BrowserType
    public static void launchBrowser(String browserType, Resolution resolution)
    {
        playwright = Playwright.create();
        Resolution defaultResolution = Resolution.LARGE;

        // Check for IS_LOCAL environment variable
        boolean isLocal = Boolean.parseBoolean(System.getenv("IS_LOCAL"));
        boolean headless = !isLocal; // If isLocal is true, headless will be false and vice versa

        // Initialize browser based on type
        switch (browserType)
        {
            case "chrome":
                System.out.println("Test Running on Chrome...");
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headless).setArgs(List.of("--start-maximixed")));
                break;

            case "firefox":
                System.out.println("Test Running on Firefox...");
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless).setArgs(List.of("--start-maximixed")));
                break;

            case "msedge":
                System.out.println("Test Running on MS Edge...");
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(headless).setArgs(List.of("--start-maximixed")));
                break;

            case "webkit":
                System.out.println("Test Running on Webkit...");
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless).setArgs(List.of("--start-maximixed")));
                break;

            default:
                System.out.println("Launching the Default Browser: Edge...");
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(headless).setArgs(List.of("--start-maximixed")));
                resolution = defaultResolution; // Use default resolution for default browser
        }

        // Create context with viewport size after browser is initialized
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
                .setViewportSize(resolution.width(), resolution.height());
        context = browser.newContext(contextOptions);
    }

    //Use this method to create a new context
    public static void createContext()
    {
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions().setViewportSize(1920, 1080);
        context = browser.newContext(contextOptions);
//        context = browser.newContext();
    }

    //Use this method to create a new page
    public static void createPage()
    {
        page = context.newPage();
    }

    //Use this method to close the current session
    public static void closeSession()
    {
        page.close();
        context.close();
    }

    //Use this method to close the browser
    public static void closeBrowser()
    {
        browser.close();
        playwright.close();
    }

    //Use this method to navigate to a URL
    public static void goToURL(String url)
    {
        page.navigate(url);
        page.waitForLoadState(LoadState.LOAD);
    }

    // Use this method to construct the URL based on Routes
    public static String constructRoute(String baseURL, String route)
    {
        return baseURL + route;
    }

}
