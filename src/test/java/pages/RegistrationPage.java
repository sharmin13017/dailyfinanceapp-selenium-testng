package pages;

import config.UserModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegistrationPage {
    @FindBy  (tagName ="a")
  public List<WebElement> btnRegister;
    @FindBy (id = "firstName")
    WebElement txtFirstName;
    @FindBy (id = "lastName")
    WebElement txtLastName;
    @FindBy (id = "email")
    WebElement txtEmail;
    @FindBy (id="password")
    WebElement txtPassword;
    @FindBy(id ="phoneNumber")
    WebElement txtPhoneNumber;
    @FindBy(id = "address")
    WebElement txtAddress;
    @FindBy (css ="[type=radio]")
    List<WebElement>  rdGender;
    @FindBy(css="[type=checkbox]")
    WebElement chkAcceptTerm;
    @FindBy (id= "register")
    WebElement btnSubmitRegister;

    public RegistrationPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void doRegistration(UserModel userModel){

        txtFirstName.sendKeys(userModel.getFirstname());
        txtLastName.sendKeys(userModel.getLastname()!=null?userModel.getLastname():" ");
        txtEmail.sendKeys(userModel.getEmail());
        txtPassword.sendKeys(userModel.getPassword());
        txtPhoneNumber.sendKeys(userModel.getPhonenumber());
        txtAddress.sendKeys(userModel.getAddress()!=null?userModel.getAddress():" ");
        rdGender.get(0).click();
        chkAcceptTerm.click();
        btnSubmitRegister.click();

    }


}
