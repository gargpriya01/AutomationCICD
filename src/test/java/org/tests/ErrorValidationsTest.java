package org.tests;

import org.TestComponents.BaseTest;
import org.TestComponents.Retry;
import org.openqa.selenium.WebElement;
import org.pageobjects.CartPage;
import org.pageobjects.CheckoutPage;
import org.pageobjects.ConfirmationPage;
import org.pageobjects.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {

   @Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
   public void LoginErrorValidation() throws IOException {
       landingPage.loginApplication("test21@yahoo.com","Test@123");
        Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
        }

    @Test
    public void ProductErrorValidation() throws IOException {
        String productName="ZARA COAT 3";
        ProductCatalogue productCatalogue= landingPage.loginApplication("test2@yahoo.com","Test@123");

        List<WebElement> products=productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage=productCatalogue.goToCartPage();

        Boolean match=cartPage.verifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);
    }


}
