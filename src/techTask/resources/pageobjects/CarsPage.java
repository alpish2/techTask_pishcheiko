package techTask.resources.pageobjects;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by opishcheiko on 4/7/2018.
 */
public class CarsPage {
    private WebDriver wd;
    private WebDriverWait wait;

    public CarsPage(WebDriver w, WebDriverWait wt) {
        wd = w;
        wait = wt;
        PageFactory.initElements(wd, this);
    }


    @FindBy(css = "button.page-number")
    private List<WebElement> pagesOfSearchResults;

    @FindBy(css = "span.icon-nav_arrow_right")
    private WebElement nextPage;

    @FindBy(css = "span.icon-nav_arrow_left")
    private WebElement prevPage;

    @FindBy(css = "p.card__price")
    private List<WebElement> prices;


    public int getNumberOfPagesOfSearchResults() {
        return pagesOfSearchResults.size();
    }

    public void goToNextPage() {
        nextPage.click();
    }

    public void goToPreviousPage() {
        prevPage.click();
    }

    public void verifyThatPriceFilterIsApplied(String priceFrom, String priceTo) {
        for (int i = 0; i < getNumberOfPagesOfSearchResults(); i++) {
            for (WebElement price : prices) {
                int priceInNum = parseInt(price.getAttribute("textContent").replaceAll("[^0-9]", ""));
                int priceFromInNum = parseInt(priceFrom);
                int priceToInNum = parseInt(priceTo);
                System.out.println(priceInNum);

                if (!(priceInNum >= priceFromInNum && priceInNum <= priceToInNum)) {
                    AssertionError assertError = new AssertionError("Price for current item is not correct");
                    logger.error(assertError.getMessage(), assertError);
                    logger.error("Price is " + priceInNum + ".It should be from " + priceFrom + " to " + priceTo);
                    Assert.fail();
                }
            }
            goToNextPage();
            logger.info("Next Page icon clicked.");
        }
        logger.info("Price filtering verified. All prices are located between " + priceFrom + " and " + priceTo);

    }


    final static Logger logger = LogManager.getLogger(CarsPage.class);
}
