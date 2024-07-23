package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.pageobjects.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTest {
    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public ConfirmationPage confirmationPage;
    @Given("I landed on Ecommere Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        landingPage=launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username,String password){
        productCatalogue=landingPage.loginApplication(username,password);
    }

    @When("^I add product (.+) to Cart$")
    public void i_add_product_to_cart(String productName){
        List<WebElement> products=productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
        public void checkout_submit_order(String productName){
        CartPage cartPage=productCatalogue.goToCartPage();
        Boolean match=cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage=cartPage.goToCheckout();
        checkoutPage.selectCountry("india");
        confirmationPage=checkoutPage.submitOrder();
        }

        @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmation_page(String string){
            String confirmMessage=confirmationPage.getConfirmationMessage();
            Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
            driver.close();
        }

        @Then("{string} message is displayed")
    public void something_message_is_displayed(String strArg1){
        Assert.assertEquals(strArg1,landingPage.getErrorMessage());
        driver.close();
        }
}


