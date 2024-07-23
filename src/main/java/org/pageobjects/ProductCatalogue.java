package org.pageobjects;

import org.abstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {
    WebDriver driver;
    public ProductCatalogue(WebDriver driver){
        super(driver);
        //initialize webdriver
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".mb-3")
    private List<WebElement> products;

    @FindBy(css=".ng-animating")
    private WebElement spinner;

    private By productsBy=By.cssSelector(".mb-3");
    private By addToCart=By.cssSelector(".card-body button:last-of-type");
    private By toastMessage=By.cssSelector("#toast-container");

    public List<WebElement> getProductList() {
        waitForElementToAppear(productsBy);
        return products;
    }

        public WebElement getProductByName(String productName){
            WebElement prod = getProductList().stream().filter(product ->
                    product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
            return prod;
        }

        public void addProductToCart(String productName){
            WebElement prod=getProductByName(productName);
            prod.findElement(addToCart).click();
            waitForElementToAppear(toastMessage);
            waitForElementToDisappear(spinner);
        }


    }

