package testrunner;

import config.Setup;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class DashboardTestRunner extends Setup {
    @BeforeTest
    public void doLogin(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogin("salman@test.com","1234");
    }
    @Test(priority = 1, description = "Add cost for an Item")
    public void addCost() throws InterruptedException {
        DashboardPage dashboardPage=new DashboardPage(driver);
        dashboardPage.btnAddCost.click();
        dashboardPage.addCost("Item 1","50");
        Thread.sleep(1000);
        driver.switchTo().alert().accept();

    }
}
