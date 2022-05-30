package com.ztrain.steps;

import com.ztrain.pageObject.Homepage;
import com.ztrain.pageObject.LoginPage;
import com.ztrain.pageObject.RegistrationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Objects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class LoginSteps {

    private LoginPage loginPage;
    private Homepage homepage;
    private RegistrationPage registrationPage;

    public LoginSteps(LoginPage loginPage, Homepage homepage, RegistrationPage registrationPage) {
        this.loginPage = loginPage;
        this.homepage = homepage;
        this.registrationPage = registrationPage;
    }

    // TEST_OF-808: Verify successful login with credentials
    @Given("User is on the login page")
    public void userIsOnTheLoginPage() {
        loginPage.goToLoginPage();
    }

    @When("User is logging-in with {string} {string}")
    public void userIsLoggingInWithUsernamePassword(String email, String password) {
        loginPage.login(email, password);
    }

    @Then("User should be redirected to the home page")
    public void userShouldBeRedirectedToHomePage() {
        assertTrue(homepage.isZTrainLogoDisplayed(), "This is not the passage");
        homepage.saveScreenShotPNG();
    }

    // TEST_OF-807: Login and password field requiered
    @When("User click on connexion button")
    public void userClickOnConnexionButton() {
        loginPage.loginFieldRequired();
    }

    @Then("User should see and error message below the login field")
    public void userShouldSeeAndErrorMessageBelowTheLoginField() {
    assertEquals("Le format de l'email est invalid", this.loginPage.getErrorMessage());
    }

    //TEST_OF-799: Access to the registration page
    @When("User fill in {string} field and click on displayed password button")
    public void userFillInFieldAndClickOnDisplayedPasswordButton(String password) {
        loginPage.displayPwd(password);
    }

    @Then("The password is displayed in clear")
    public void thePasswordIsDisplayedInClear() {
        assertTrue(this.loginPage.isPasswordInputOfType("text"));
    }

    //TEST_OF-799: Access to the registration page
    @When("User clicks on the resgister link")
    public void userClicksOnTheResgisterLink() {
        this.loginPage.goToRegistrationPage();
    }

    @Then("User should be directed to the registration page")
    public void userShouldBeDirectedToTheRegistrationPage() {
        assertEquals(this.registrationPage.getRegisterTitle(), "Inscription", "kjdzl");
    }

}
