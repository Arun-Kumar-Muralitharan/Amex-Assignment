package ui;

import com.Amex.core.pages.Cartes_pour_les_particuliers;
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
@Feature("French page Gold Cards")  // More specific feature name
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
    public void amexFrenchApplicationAssignment()
    {
//        YamlReader endpoint = new YamlReader("Endpoint");
//        YamlReader route = new YamlReader("Routes");
//        String amexURL = endpoint.getValue("UI_Endpoints.amex_home");
//        String frenchRoute = route.getValue("UI_Routes.french");
//        String frenchURL = UICommon.constructRoute(amexURL, frenchRoute);
        String frenchURL = "https://www.americanexpress.com/fr-fr/?inav=NavLogo";

        AllureReportsUtil.runStepWithScreenshot("Open the AmEx French Application", () -> {
            UICommon.goToURL(frenchURL);
        });

        AllureReportsUtil.runStepWithScreenshot("Accept the cookies", () -> {
            page.locator("//button[contains(text(), 'Tout Accepter')]").click();
        });

        AllureReportsUtil.runStepWithScreenshot("Select Cartes pour les particuliers", () -> {
            Cartes_pour_les_particuliers.selectCartes();
        });

        AllureReportsUtil.runStepWithScreenshot("Accept the cookies", () -> {
            page.locator("//button[contains(text(), 'Tout Accepter')]").click();
        });

        AllureReportsUtil.runStepWithScreenshot("Select the Gold Card", () -> {
            Cartes_pour_les_particuliers.selectGold();
        });

        AllureReportsUtil.runStepWithScreenshot("Select Demandez", () -> {
            Cartes_pour_les_particuliers.selectDemandez();
        });

        AllureReportsUtil.runStepWithScreenshot("Fill the form and Submit", () -> {
            Cartes_pour_les_particuliers.fillform();
        });
    }
}