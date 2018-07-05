package techTask.resources.pageobjects;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by opishcheiko on 4/7/2018.
 */
public class HomePage {
    private WebDriver wd;
    private WebDriverWait wait;

    public HomePage(WebDriver w, WebDriverWait wt) {
        wd = w;
        wait = wt;
        PageFactory.initElements(wd, this);
    }


    @FindBy(css = "select.trackable")
    private WebElement searchForDropdown;

    @FindBy(xpath = ".//*[@id='topContentPanel']//search-hero//div[3]/button")
    private WebElement priceMenu;

    @FindBy(xpath = ".//*[@id='topContentPanel']//search-hero//div[4]/button")
    private WebElement yearMenu;

    @FindBy(css = "button.search-hero-btn")
    private WebElement searchIconOnSearchPanel;

   @FindBy(css = "a.landing-cta-place-ad.trackable")
    private WebElement placeAdIcon;

    @FindBy(css = "span.header-menu-name")
    private WebElement userIconInTheRightUpperCorner;


    public void goToPage(String url) throws InterruptedException {
        wd.navigate().to(url);
        Thread.sleep(5000);
        if (!wd.getTitle().equals("DoneDeal.co.uk - Ireland's biggest classifieds site")) {
            AssertionError assertError = new AssertionError("Page title is NOT 'DoneDeal.co.uk - Ireland's biggest classifieds site' ");
            logger.error(assertError.getMessage(), assertError);
            logger.error("Current URL is " + wd.getCurrentUrl());
            Assert.fail();
        }
        logger.info("Current URL is " + wd.getCurrentUrl());
    }

    public void selectCarsAndMotorSearchSection() {

        if (wd.findElements(By.cssSelector(" div.motor-focus")).size() == 0) {
            List<WebElement> listOfTabs = wd.findElements(By.cssSelector("div.search-hero-tab a"));
            for (WebElement listOfTab : listOfTabs) {
                if (listOfTab.getAttribute("textContent").contains("Cars & Motor")) {
                    listOfTab.click();
                    logger.info("Cars & Motor tab on search panel clicked.");
                    break;
                }
            }
        } else logger.info("Cars & Motor tab is already selected.");
    }

    public void selectVehicleFromDropdown(String vehicleName) {
        Select oSelect = new Select(searchForDropdown);
        oSelect.selectByVisibleText(vehicleName);
        logger.info(vehicleName + " selected in 'Search For' drop down.");
    }

    public void selectPrice(String priceFrom, String priceTo) throws InterruptedException {
        priceMenu.click();
        Thread.sleep(3000);

        if (wd.findElements(By.cssSelector("header.popout-menu-header p")).size() != 0) {
            if (wd.findElement(By.cssSelector("header.popout-menu-header p")).getAttribute("textContent").equals("Choose Price"))
                logger.info("Price menu selected.");
        } else {
            AssertionError assertError = new AssertionError("Price menu may not be opened.");
            logger.error(assertError.getMessage(), assertError);
            Assert.fail();
        }

        WebElement priceFromMenu = wd.findElement(By.xpath(".//*[@id='topContentPanel']//search-hero//div[3]//div[1]//select"));
        Select oSelect = new Select(priceFromMenu);
        oSelect.selectByValue("number:" + priceFrom);
        logger.info("Price From: " + oSelect.getFirstSelectedOption().getAttribute("textContent") + " selected.");

        WebElement priceToMenu = wd.findElement(By.xpath(".//*[@id='topContentPanel']//search-hero//div[3]//div[2]//select"));
        oSelect = new Select(priceToMenu);
        oSelect.selectByValue("number:" + priceTo);
        logger.info("Price To: " + oSelect.getFirstSelectedOption().getAttribute("textContent") + " selected.");

        wd.findElement(By.cssSelector("a.icon-close")).click();
        logger.info("Close button on Price menu clicked.");
    }

    public void selectYear(String yearFrom, String yearTo) throws InterruptedException {
        yearMenu.click();
        Thread.sleep(3000);

        if (wd.findElements(By.cssSelector("header.popout-menu-header p")).size() != 0) {
            if (wd.findElement(By.cssSelector("header.popout-menu-header p")).getAttribute("textContent").equals("Choose Year"))
                logger.info("Year menu selected.");
        } else {
            AssertionError assertError = new AssertionError("Year menu may not be opened.");
            logger.error(assertError.getMessage(), assertError);
            Assert.fail();
        }

        WebElement yearFromMenu = wd.findElement(By.xpath(".//*[@id='topContentPanel']//search-hero//div[4]//div[1]//select"));
        Select oSelect = new Select(yearFromMenu);
        oSelect.selectByValue("object:" + yearFrom);
        logger.info("Year From: " + oSelect.getFirstSelectedOption().getAttribute("textContent") + " selected.");

        WebElement yearToMenu = wd.findElement(By.xpath(".//*[@id='topContentPanel']//search-hero//div[4]//div[2]//select"));
        oSelect = new Select(yearToMenu);
        oSelect.selectByValue("object:" + yearTo);
        logger.info("Year To: " + oSelect.getFirstSelectedOption().getAttribute("textContent") + " selected.");

        wd.findElement(By.cssSelector("a.icon-close")).click();
        logger.info("Close button on Year menu clicked.");
    }

    public void clickSearchIcon() {
        searchIconOnSearchPanel.click();
        logger.info("Search icon clicked.");
    }

    public void clickPlaceAdIcon() {
        placeAdIcon.click();
        logger.info("Search icon clicked.");
    }

    public void verifyLogin(String name) {
        if (!userIconInTheRightUpperCorner.getAttribute("textContent").equals(name)){
            AssertionError assertError = new AssertionError("User may not be logged in.");
            logger.error(assertError.getMessage(), assertError);
            logger.error("Current user name: "+ userIconInTheRightUpperCorner.getAttribute("textContent"));
            Assert.fail();
        }
            logger.info("User " + name + " is logged in successfully");
        userIconInTheRightUpperCorner.click();
    }


    final static Logger logger = LogManager.getLogger(HomePage.class);
}
