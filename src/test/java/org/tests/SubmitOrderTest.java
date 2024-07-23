package org.tests;

import org.TestComponents.BaseTest;
import org.TestComponents.Retry;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.pageobjects.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
   @Test(dataProvider = "getData",groups={"Purchase"})
   public void submitOrder(HashMap<String,String> input) throws IOException {
       System.out.println(input.get("email"));
       System.out.println(input.get("password"));

        ProductCatalogue productCatalogue= landingPage.loginApplication(input.get("email"),input.get("password"));

        List<WebElement> products=productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("productName"));
        CartPage cartPage=productCatalogue.goToCartPage();
        Boolean match=cartPage.verifyProductDisplay(input.get("productName"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage=cartPage.goToCheckout();
        checkoutPage.selectCountry("india");
        ConfirmationPage confirmationPage=checkoutPage.submitOrder();
        String confirmMessage=confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        }

        @Test(dataProvider = "getData",dependsOnMethods = {"submitOrder"},retryAnalyzer = Retry.class)
     public void OrderHistoryTest(String email,String password,String productName){
             ProductCatalogue productCatalogue= landingPage.loginApplication(email,password);
             OrderPage ordersPage=productCatalogue.goToOrdersPage();
             Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));

        }

        // Method 1 ---- to run tests with multiple test data
       /* @DataProvider
        public Object[][] getData() {
            //return new Object[][]{{"test2@yahoo.com","Test@123","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
        }*/

    //method 2: run tests via multiple test data using HashMap object
   /* @DataProvider
    public Object[][] getData() {
            HashMap<String,String> map=new HashMap<String,String>();
            map.put("email","test2@yahoo.com");
            map.put("password","Test@123");
            map.put("productName","ZARA COAT 3");

            HashMap<String,String> map1=new HashMap<String,String>();
            map1.put("email","shetty@gmail.com");
            map1.put("password","Iamking@000");
            map1.put("productName","ADIDAS ORIGINAL");

            return new Object[][] {{map},{map1}};
   }*/

    //method 3- via json file
    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//data//PurchaseOrder//PurchaseOrder.json");
    return new Object[][] {{data.get(0)},{data.get(1)}};
    }


}
