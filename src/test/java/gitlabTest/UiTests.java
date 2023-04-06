package gitlabTest;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.testng.Tag;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$x;

@Tag("UI")
public class UiTests {

    @BeforeSuite
    public static void setUp() {
        Configuration.headless = true;
    }

    @BeforeTest
    public void openPage() {
        Selenide.open("https://www.google.com/");
    }

    private void assertAnswer(String value) {
        $x("//input[@name='q']").sendKeys(value + "=" + Keys.ENTER);
        String answer = $x("//span[@id='cwos']").getText();
        Assert.assertEquals("4", answer);
    }

    @Test
    public void calcPlusTest() {
        assertAnswer("2+2");
    }

    @Test
    public void calcPlusTest2() {
        assertAnswer("1+3");
    }


    @Test
    public void calcMinusTest() {
        assertAnswer("6-2");
    }

    @Test
    public void calcMultipyTest() {
        assertAnswer("2*2");
    }

    @Test
    public void calcDevideTest() {
        assertAnswer("8/2");
    }
}
