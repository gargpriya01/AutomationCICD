package org.pageobjects;

import org.abstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    WebDriver driver;
    public LandingPage(WebDriver driver){
        super(driver);
        //initialize webdriver
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    //PageFactory design pattern
    @FindBy(id="userEmail")
    private WebElement userEmail;

    @FindBy(id="userPassword")
    private WebElement passwordEle;

    @FindBy(id="login")
    private WebElement submit;

    @FindBy(css="[class*='flyInOut']")
    private WebElement errorMessage;
    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getErrorMessage(){
       waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }
    public ProductCatalogue loginApplication(String email, String password){
     userEmail.sendKeys(email);
     passwordEle.sendKeys(password);
     submit.click();
        ProductCatalogue productCatalogue=new ProductCatalogue(driver);
        return productCatalogue;

    }
}
