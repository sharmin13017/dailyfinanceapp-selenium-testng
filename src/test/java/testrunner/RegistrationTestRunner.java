package testrunner;

import com.github.javafaker.Faker;
import config.Setup;
import config.UserModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;

public class RegistrationTestRunner extends Setup {
    @Test(priority = 1, description = "User can register by providing all info")
    public void userRegByAllFields() throws InterruptedException, IOException, ParseException {

        RegistrationPage userReg = new RegistrationPage(driver);
        Faker faker = new Faker();
        userReg.btnRegister.get(1).click();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = "1234";
        String phoneNumber = "01505" + Utils.generateRandomNumber(100000, 999999);
        String address = faker.address().fullAddress();
        UserModel userModel=new UserModel();
        userModel.setFirstname(firstName);
        userModel.setLastname(lastName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhonenumber(phoneNumber);
        userModel.setAddress(address);
        userReg.doRegistration(userModel);

        //assertion
        doRegAssertion();

       
        JSONObject userObj = new JSONObject();
        userObj.put("firstName", firstName);
        userObj.put("lastName", lastName);
        userObj.put("email", email);
        userObj.put("password", password);
        userObj.put("phoneNumber", phoneNumber);
        userObj.put("address", address);
        Utils.saveUserInfo("./src/test/resources/users.json", userObj);
        Thread.sleep(5000);
    }

    @Test (priority = 2, description = "User can register by providing only mandatory info")
    public void userRegByMandatoryFields() throws IOException, ParseException, InterruptedException {
        //WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(50));
        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("h1"))));
        RegistrationPage userReg = new RegistrationPage(driver);
        Faker faker = new Faker();
        userReg.btnRegister.get(1).click();
        String firstName = faker.name().firstName();
        String email = faker.internet().emailAddress();
        String password = "1234";
        String phoneNumber = "01505" + Utils.generateRandomNumber(100000, 999999);
        UserModel userModel=new UserModel();
        userModel.setFirstname(firstName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhonenumber(phoneNumber);
        userReg.doRegistration(userModel);


         doRegAssertion();
         JSONObject userObj = new JSONObject();
         userObj.put("firstName", firstName);
         userObj.put("email", email);
         userObj.put("phoneNumber", phoneNumber);
         userObj.put("password",password);

          Utils.saveUserInfo("./src/test/resources/users.json", userObj); 
    }

    public void doRegAssertion()throws InterruptedException {
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Toastify__toast")));
        String successMessageActual = driver.findElement(By.className("Toastify__toast")).getText();
        String successMessageExpected = "successfully";
        System.out.println(successMessageActual);
        Assert.assertTrue(successMessageExpected.contains(successMessageActual));
        
    }
}