package gitlabTest;


import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.testng.Tag;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.*;

@Tag("UI")
public class UiTests {

    @BeforeSuite
    public static void setUp() {
        Configuration.headless = true;
        Configuration.browser = Browsers.CHROME;

    }

//    @BeforeTest
//    public void openPage() {
//        Selenide.open("https://www.google.com/");
//    }

    @BeforeMethod
    protected void beforeMethod() {
      //  DesiredCapabilities dc = new DesiredCapabilities();
        try {
            ChromeOptions options= new ChromeOptions();
            RemoteWebDriver driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), options);
            driver.manage().window().maximize();
            driver.get("https://www.google.com/");
            WebDriverRunner.setWebDriver(driver);
            driver.setFileDetector(new LocalFileDetector());


        } catch (MalformedURLException ex ){
            throw  new RuntimeException();
        }

    }
    private void assertAnswer(String value) {
        $x("//input[@name='q']").sendKeys(value + "=" + Keys.ENTER);
        String answer = $x("//span[@id='cwos']").getText();
        Assert.assertEquals("4", answer);
    }

    @AfterSuite
    protected  void after () {
        closeWebDriver();
    }
    @Test
    public void calcPlusTest() {
        assertAnswer("2+2");
    }
//
//    @Test
//    public void calcPlusTest2() {
//        assertAnswer("1+3");
//    }
//
//
//    @Test
//    public void calcMinusTest() {
//        assertAnswer("6-2");
//    }
//
//    @Test
//    public void calcMultipyTest() {
//        assertAnswer("2*2");
//    }
//
//    @Test
//    public void calcDevideTest() {
//        assertAnswer("8/2");
//    }
}
