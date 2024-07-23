package org.pageobjects;

import org.abstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {
    WebDriver driver;
    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "tr td:nth-child(3)")
    private List<WebElement> productNames;

    public boolean verifyOrderDisplay(String productName){
        Boolean match= productNames.stream().anyMatch(product->
                product.getText().equalsIgnoreCase(productName));
        return match;
    }
}
