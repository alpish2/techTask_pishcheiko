package techTask.resources.tests;

import techTask.resources.app.Application;
import techTask.resources.app.Data;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.net.MalformedURLException;

/**
 * Created by opishcheiko on 6/25/2018.
 */
public class CarsSearchTest {
    String dividingLine = "\n--------------------\n\n\n\n";

    @Test
    public void carsSearchTest() throws MalformedURLException, InterruptedException {
        Application app = new Application();

        logger.info("\n\ncarsSearchTest started ...");

        app.homePage.goToPage(Data.GetValue("DoneDealURL"));
        app.homePage.selectCarsAndMotorSearchSection();
        app.homePage.selectVehicleFromDropdown(Data.GetValue("VehicleName1"));
        app.homePage.selectPrice(Data.GetValue("PriceFrom1"), Data.GetValue("PriceTo1"));
        app.homePage.selectYear(Data.GetValue("YearFrom1"), Data.GetValue("YearTo1"));
        app.homePage.clickSearchIcon();
        app.carsPage.verifyThatPriceFilterIsApplied(Data.GetValue("PriceFrom1"), Data.GetValue("PriceTo1"));
        app.quit();

        logger.info("carsSearchTest completed successfully" + dividingLine);
    }

    final static Logger logger = LogManager.getLogger(CarsSearchTest.class);
}