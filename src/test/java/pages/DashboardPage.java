package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
    @FindBy (className = "add-cost-button")
    public WebElement btnAddCost;
    @FindBy (id = "itemName")
    WebElement txtItemName;
    @FindBy (id = "amount")
    WebElement txtAmount;
    @FindBy (css="[type=submit]")
    WebElement btnSubmit;

    public DashboardPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void addCost(String itemName,String amount){
        txtItemName.sendKeys(itemName);
        txtAmount.sendKeys(amount);
        btnSubmit.click();
    }
}
