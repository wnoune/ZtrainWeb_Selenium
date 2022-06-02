package com.ztrain.steps;

import com.ztrain.context.Context;
import com.ztrain.context.ScenarioContext;
import com.ztrain.pageObject.Homepage;
import com.ztrain.pageObject.LoginPage;
import com.ztrain.pageObject.Page;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomeSteps extends Page {
    private Homepage homepage;
    private LoginPage loginPage;
    private ScenarioContext context;

    public HomeSteps(Homepage homepage, LoginPage loginPage, ScenarioContext context) {
        this.homepage = homepage;
        this.loginPage = loginPage;
        this.context = context;
    }

    // TEST_OF-830: Display the sheet of a product
    @Given("User is on the homepage")
    public void userIsLoggedIn(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        this.loginPage.goToLoginPage();
        this.loginPage.login(rows.get(0).get("email"), rows.get(0).get("password"));
    }

    @When("User clicks on a product")
    public void userClicksOnAProduct() {
        homepage.displayProductSheet();
    }

    @Then("The product sheet should appear")
    public void theProductSheetShouldAppear() {
        assertTrue(homepage.displayProductDetails(), "This is not the product sheet");
    }

    // TEST_OF-831: Add product to cart
    @When("User clicks on a cart icon of product")
    public void userClicksOnACartIconOfProduct() {
        homepage.addProductToCard();
    }

    @And("User clicks on card icon")
    public void userClicksOnCardIcon() {
        homepage.openCard();
    }

    @Then("The product should be visible in the card")
    public void theProductShouldBeVisibleInTheCard() {
        String expectedProductName = "T-shirt en coto";
        assertTrue(expectedProductName.contains(this.homepage.displayProductName().replace(".", "")));
        // C'est une méthode de string; tu pourras checker ça after. la classe String en java. ça te sera utile
    }

    // TEST_OF-839: Delete product to card
    @And("User clicks on delete icon of product")
    public void userClicksOnDeleteIconOfProduct() {
        context.set(Context.PRODUCT_PRICE, homepage.getProductPrice());
        context.set(Context.CART_TOTAL_PRICE, homepage.getTotalPriceCart());
        homepage.deleteProductCard();
    }

    @Then("The product is not already visible in the card")
    public void theProductIsNotAlreadyVisibleInTheCard() {
        double cartTotal = Math.round((double)context.get(Context.CART_TOTAL_PRICE)*100.0)/100.0;
        System.out.println(cartTotal + "VART TOTAL");
        double productPrice = Math.round((double) context.get(Context.PRODUCT_PRICE)*100.0)/100.0;
        System.out.println(productPrice + "PRDUCT ¨>iicE");
        assertEquals(homepage.getTotalPriceCart(), cartTotal - productPrice);
    }

    @When("User adds products to cart")
    public void userAddsProductsToCart() {
        homepage.addProductToCard();
    }



    //TEST_OF-902: Trash cart
    @And("User clicks on trash cart button")
    public void userClicksOnTrashCartButton() {
        homepage.trashCart();
    }

    @Then("The cart should be emtying")
    public void theCartShouldBeEmtying() {
        assertEquals(this.homepage.displayEmptycartMessage(), "Votre panier est vide", "The cart is not empty");
    }

    //TEST_OF-840: log out to account
    @When("User move to account icon and clicks on logout")
    public void userMoveToAccountIconAndClicksOnLogout() {
        homepage.logOut();
    }

    //TEST_OF-901: Test the order button
    @And("User clicks on oder button")
    public void userClicksOnOderButton() {
        homepage.validateOder();
    }

    @Then("The oder validation pop-up is displaying")
    public void theOderValidationPopUpIsDisplaying() {
        assertEquals(this.homepage.getTitleValidationOder(), "Valider votre commande", "it's not the page");
    }
}
