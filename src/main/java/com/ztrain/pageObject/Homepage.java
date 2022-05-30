package com.ztrain.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Homepage extends Page{

    @FindBy(css = "nav h1")
    private WebElement ztrain_logo;

    @FindBy(id = "style_popular_product_wrapper__z6J0h")
    private WebElement products;

    @FindBy(id = "style_card_body__QuFGN")
    private WebElement Tshirt;

    @FindBy(id = "style_detail_wrapper__a7fpS")
    private WebElement productDetails;

    public void goToHomePage() {
        driver.get(ENV.getUrl("/home"));
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean isZTrainLogoDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(this.ztrain_logo));
        return this.ztrain_logo.isDisplayed();
    }

    public boolean displayProductPage() {
        waitUntil(ExpectedConditions.visibilityOf(this.products));
        this.scrollDownToElement(products);
        return this.products.isDisplayed();
    }

    public void displayProductSheet() {
        clickOn(Tshirt);
    }

    public boolean displayProductDetails() {
        return this.productDetails.isDisplayed();
    }
}
