package com.ztrain.steps;

import com.ztrain.pageObject.Homepage;
import com.ztrain.pageObject.LoginPage;
import com.ztrain.pageObject.Page;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertTrue;

public class HomeSteps extends Page {
    private Homepage homepage;
    private LoginPage loginPage;

    public HomeSteps(Homepage homepage, LoginPage loginPage) {
        this.homepage = homepage;
        this.loginPage = loginPage;
    }

    @Given("User is on the product page")
    public void userIsOnTheProductPage() {
        homepage.goToHomePage();
    }

    @When("User clicks on a product")
    public void userClicksOnAProduct() {
        homepage.displayProductSheet();
    }

    @Then("The product sheet should appear")
    public void theProductSheetShouldAppear() {
        assertTrue(homepage.displayProductDetails(), "This is not the product sheet");
    }
}
