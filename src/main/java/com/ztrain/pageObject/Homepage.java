package com.ztrain.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Homepage extends Page{

    @FindBy(css = "nav h1")
    private WebElement ztrain_logo;

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean isZTrainLogoDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(this.ztrain_logo));
        return this.ztrain_logo.isDisplayed();
    }
}
