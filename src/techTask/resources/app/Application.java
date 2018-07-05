package techTask.resources.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import techTask.resources.pageobjects.CarsPage;
import techTask.resources.pageobjects.HomePage;
import techTask.resources.pageobjects.LoginPage;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

/**
 * Created by opishcheiko on 4/7/2018.
 */
public class Application {
    private WebDriver wd;
    private WebDriverWait wait;

    public CarsPage carsPage;
    public HomePage homePage;
    public LoginPage loginPage;

    public Application() throws MalformedURLException {

        // Firefox selenium wd 3.9.1
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("marionette", true);
        wd = new FirefoxDriver(firefoxOptions);
        // wd = new RemoteWebDriver(new URL(Data.GetValue("RemoteServerUrl")), firefoxOptions);
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Chrome
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setCapability("chromeDriver", true);
//        wd = new ChromeDriver(chromeOptions);
//        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        wait = new WebDriverWait(wd, 55);

        carsPage = new CarsPage(wd, wait);
        homePage = new HomePage(wd, wait);
        loginPage = new LoginPage(wd, wait);
    }

    public void quit() {
        wd.quit();
    }
}
