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
public class SignUpTest {
    String dividingLine = "\n--------------------\n\n\n\n";

    @Test
    public void signUpTest() throws MalformedURLException, InterruptedException {
        Application app = new Application();

        logger.info("\n\nsignUpTest started ...");

        app.homePage.goToPage(Data.GetValue("DoneDealURL"));
        app.homePage.clickSignUpIcon();
        app.loginPage.clickSignUpLink();
        app.loginPage.requiredFieldsVerification(
                Data.GetValue("Email1"),
                Data.GetValue("NotValidEmail1"),
                Data.GetValue("NotValidEmail2"),
                Data.GetValue("NotValidEmail3"),
                Data.GetValue("NotValidEmail4"),
                Data.GetValue("Password1"),
                Data.GetValue("ShortPassword"),
                Data.GetValue("Name1")
        );

        app.loginPage.typeName(Data.GetValue("Email1"));
        app.loginPage.typePassword(Data.GetValue("Password1"));
        app.loginPage.typeName(Data.GetValue("Name1"));
        app.loginPage.acceptTermsOfUse();
        app.loginPage.setNoUpdatesCheckbox();
        app.loginPage.pressSignUp();

        app.homePage.verifyLogin(Data.GetValue("Name1"));

        app.quit();

        logger.info("signUpTest completed successfully" + dividingLine);
    }

    final static Logger logger = LogManager.getLogger(SignUpTest.class);
}