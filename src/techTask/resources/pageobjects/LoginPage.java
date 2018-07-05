package techTask.resources.pageobjects;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by opishcheiko on 4/7/2018.
 */
public class LoginPage {
    private WebDriver wd;
    private WebDriverWait wait;

    public LoginPage(WebDriver w, WebDriverWait wt) {
        wd = w;
        wait = wt;
        PageFactory.initElements(wd, this);
    }


    @FindBy(css = "a[href='/accounts/register/']")
    private WebElement sighUpLink;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "tos-agreed")
    private WebElement tearmsOfUseCheckbox;

    @FindBy(id = "contact-prefs-none")
    private WebElement noUpdatesCheckbox;

    @FindBy(id = "contact-prefs-email")
    private WebElement emailUpdatesCheckbox;

    @FindBy(id = "contact-prefs-notifications")
    private WebElement notificationUpdatesCheckbox;

    @FindBy(id = "submit")
    private WebElement signUpButton;

    @FindBy(css ="p.validation-msg")
    private List <WebElement> errorMessages;


    public void clickSignUpLink() throws InterruptedException {
        sighUpLink.click();
        logger.info("Sign Up linked clicked.");
        Thread.sleep(5000);
    }

    public void typeEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
        logger.info("Typed email = " + email);
    }

    public void typePassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
        logger.info("Typed password = " + password);
    }

    public void typeName(String name) {
        nameField.clear();
        nameField.sendKeys(name);
        logger.info("Typed name = " + name);
    }

    public void acceptTermsOfUse() {
        if (!tearmsOfUseCheckbox.isSelected())
            tearmsOfUseCheckbox.click();
        logger.info("Terms Of Use checkbox ticked");
    }

    public void setNoUpdatesCheckbox() {
        if (!noUpdatesCheckbox.isSelected())
            noUpdatesCheckbox.click();
        logger.info("No updates checkbox ticked");
    }

    public void pressSignUp() throws InterruptedException {
        Thread.sleep(5000);
        signUpButton.click();
        logger.info("Pressed sign Up button");
        Thread.sleep(5000);
    }

    public void clickOkay() throws InterruptedException {
        wd.findElement(By.cssSelector("a.input-btn")).click();
        logger.info("Okay button pressed");
        Thread.sleep(5000);
    }

    public void requiredFieldsVerification(String email,
                                           String emailWrong1,
                                           String emailWrong2,
                                           String emailWrong3,
                                           String emailWrong4,
                                           String password,
                                           String passwordShort,
                                           String name

    ) {
        emailVerification(email, emailWrong1, emailWrong2, emailWrong3, emailWrong4);
        passwordVerification(password, passwordShort);
        nameVerification(name);
        //checkboxes verification
    }

    private void emailVerification(String email,
                                   String emailWrong1,
                                   String emailWrong2,
                                   String emailWrong3,
                                   String emailWrong4) {

        logger.info("Verifying Email field...");
        logger.info("Checking case when field is empty...");
        emailField.clear();
        signUpButton.click();
        logger.info("Pressed Sign Up button");
        wait.until(ExpectedConditions.visibilityOf(errorMessages.get(0)));
        logger.info("Error message appears: '" +errorMessages.get(0).getAttribute("textContent"));

        logger.info("Checking cases when email is not valid...");
        typeEmail(emailWrong1);
        signUpButton.click();
        logger.info("Pressed Sign Up button");
        wait.until(ExpectedConditions.visibilityOf(errorMessages.get(0)));
        logger.info("Error message appears: '" +errorMessages.get(0).getAttribute("textContent"));

        typeEmail(emailWrong2);
        signUpButton.click();
        logger.info("Pressed Sign Up button");
        wait.until(ExpectedConditions.visibilityOf(errorMessages.get(0)));
        logger.info("Error message appears: '" +errorMessages.get(0).getAttribute("textContent"));

        typeEmail(emailWrong3);
        signUpButton.click();
        logger.info("Pressed Sign Up button");
        wait.until(ExpectedConditions.visibilityOf(errorMessages.get(0)));
        logger.info("Error message appears: '" +errorMessages.get(0).getAttribute("textContent"));

        typeEmail(emailWrong4);
        signUpButton.click();
        logger.info("Pressed Sign Up button");
        wait.until(ExpectedConditions.visibilityOf(errorMessages.get(0)));
        logger.info("Error message appears: '" +errorMessages.get(0).getAttribute("textContent"));

        typeEmail(email);
    }

    private void passwordVerification(String password,
                                      String passwordShort) {
        logger.info("Verifying Password field...");
        logger.info("Checking case when field is empty...");
        passwordField.clear();
        signUpButton.click();
        logger.info("Pressed Sign Up button");
        wait.until(ExpectedConditions.visibilityOf(errorMessages.get(1)));
        logger.info("Error message appears: '" +errorMessages.get(1).getAttribute("textContent"));

        logger.info("Checking case when password is less than 5 characters...");
        typePassword(passwordShort);
        signUpButton.click();
        logger.info("Pressed Sign Up button");
        wait.until(ExpectedConditions.visibilityOf(errorMessages.get(1)));
        logger.info("Error message appears: '" +errorMessages.get(1).getAttribute("textContent"));

        typePassword(password);
    }


    private void nameVerification(String name) {
        logger.info("Verifying  Name field...");
        logger.info("Checking case when field is empty...");
        nameField.clear();
        signUpButton.click();
        logger.info("Pressed Sign Up button");
        wait.until(ExpectedConditions.visibilityOf(errorMessages.get(1)));
        logger.info("Error message appears: '" +errorMessages.get(1).getAttribute("textContent"));
        typeName(name);
    }



    final static Logger logger = LogManager.getLogger(LoginPage.class);
}

