package ui;

import com.microsoft.playwright.options.LoadState;
import com.Amex.core.model.Element;
import com.Amex.core.ui.UICommon;
import com.Amex.core.util.AllureReportsUtil;
import com.Amex.core.yml.YamlReader;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static com.Amex.core.ui.UICommon.page;

@Execution(ExecutionMode.CONCURRENT)

@Epic("UI Tests")  // This can be used for high-level grouping
@Feature("My Apps")  // More specific feature name
public class AmexFrTest
{

    @BeforeAll
    static void start()
    {
//        To launch a specific browser at a specific resolution call as below
//        UICommon.launchBrowser(String.valueOf(BrowserType.FIREFOX), Resolution.LARGE);
        UICommon.launchBrowser();
    }

    @BeforeEach
    void initiate()
    {
        UICommon.createContext();
        UICommon.createPage();
    }

    @AfterEach
    void close()
    {
        UICommon.closeSession();
    }

    @AfterAll
    static void exit()
    {
        UICommon.closeBrowser();
    }

    @Test
    @Story("AmEx Assignment")
    @Description("AmEx French Application Assignment tasks")
    public void myAppsEndToEnd()
    {
        YamlReader endpoint = new YamlReader("Endpoint");
        YamlReader route = new YamlReader("Routes");
        String amexURL = endpoint.getValue("UI_Endpoints.amex_home");
        String frenchRoute = route.getValue("UI_Routes.My_Applications");
        String frenchURL = UICommon.constructRoute(amexURL, frenchRoute);

        AllureReportsUtil.runStepWithScreenshot("Open the AmEx French Application", () -> {
            UICommon.goToURL(frenchURL);
        });

        AllureReportsUtil.runStepWithScreenshot("Navigate to My Applications", () -> {
            UICommon.goToURL(frenchURL);
        });

        AllureReportsUtil.runStepWithScreenshot("Select an Order New", () -> {
            Element dummy = new Element(page, "//div[@data-testid='yb-core-tabs_tab']//div[contains(text(), 'All')]");
            dummy.clickElement();
        });

        AllureReportsUtil.runStepWithScreenshot("Assert Details Page", () -> {
            String title = page.title();
            Assertions.assertEquals("Yubi", title, "Title does not match");
            page.waitForLoadState(LoadState.LOAD);
        });
    }
}