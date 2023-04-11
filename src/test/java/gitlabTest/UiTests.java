package gitlabTest;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.testng.Tag;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.*;


@Tag("UI")
public class UiTests {
  //  public static WebDriver driver;

    @BeforeSuite
    public static void setUp() {
           Configuration.headless = false;
        System.setProperty("webdriver.http.factory", "jdk-http-client");
//        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

//        driver = new ChromeDriver();
//
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }

    @BeforeClass
    public void openPage() {

        //  driver.get("https://www.ya.ru/");
            Selenide.open("https://www.google.com/");

    }

    //    @BeforeMethod
//    protected void beforeMethod() {
//      //  DesiredCapabilities dc = new DesiredCapabilities();
//        try {
//            ChromeOptions options= new ChromeOptions();
//            RemoteWebDriver driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), options);
//            driver.manage().window().maximize();
//            driver.get("https://www.google.com/");
//            WebDriverRunner.setWebDriver(driver);
//            driver.setFileDetector(new LocalFileDetector());
//
//
//        } catch (MalformedURLException ex ){
//            throw  new RuntimeException();
//        }
//
//    }
    private void assertAnswer(String value, String s) {
        $x("//input[@name='q']").shouldBe(Condition.visible, Duration.ofSeconds(5)).sendKeys(value + "=" + Keys.ENTER);

        String answer = $x("//span[@id='cwos']").getText();
        System.out.println(answer);
        Assert.assertEquals("4", answer);
    }

    private void assertans(String value, String s) {
     //   driver.get("https://www.ya.ru/");
        $(".body__content .search3__inner input").sendKeys(value,s + "=" + Keys.ENTER);
        int i = 0;
    }

    @AfterSuite
    protected void after() {
//        driver.close();
          closeWebDriver();
    }

  //
   @Parameters({"param", "param2"})
    @Test()
    public void calcPlusTest(String param, String param2) {
       assertAnswer(param, param2 + "+2");
    }

   // @Parameters({"param"})
    @Test(dataProvider="SearchProvider")
    public void Search(String param, String param2) throws InterruptedException {


        System.out.println();


        assertAnswer(param, param2 + "+2");

    }


    @DataProvider(name="SearchProvider")
    public Object [][] getData() {
        return new Object[][]
                {
                        {"5","6"},
                        {"8","7"},
                        {"9","1"}
                };
    }
    @annot(name = "gg")
    public class clss {

        @annot(name = "gg")
        public  void  aVoid () {

        }


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
