package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.customlisteners.CustomListeners;
import com.nopcommerce.demo.pages.*;
import com.nopcommerce.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import resources.testdata.TestData;

@Listeners(CustomListeners.class)
public class ComputerPageTest extends BaseTest {

    HomePage homePage;
    LoginPage loginPage;
    RegisterPage registerPage;
    ComputerPage computerPage;
    DesktopsPage desktopsPage;
    BuildYourOwnComputerPage buildYourOwnComputerPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        loginPage = new LoginPage();
        registerPage = new RegisterPage();
        computerPage = new ComputerPage();
        desktopsPage = new DesktopsPage();
        buildYourOwnComputerPage = new BuildYourOwnComputerPage();
    }

    @Test(groups = {"sanity", "smock", "regression"})
    public void verifyUserShouldNavigateToComputerPageSuccessfully() {
        //Click on Computer tab
        homePage.clickOnComputerTabLink();
        // Verify "Computer" text
        String expectedErrorMessage = "Computers";
        String actualErrorMessage = computerPage.verifyComputerText() ;
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage, "computer text not display");

    }

    @Test(groups = {"smock", "regression"})
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        //Click on Computer tab
        // Click on Desktops link
        computerPage.mouseOverToComputerAndClickOnDesktop();
        // Verify "Desktops" text
        String expectedErrorMessage = "Desktops";
        String actualErrorMessage = desktopsPage.verifyDesktopText() ;
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage, "computer text not display");
    }

    @Test(groups = {"regression","runners"}, dataProvider = "buildYourOwnComputerData",dataProviderClass = TestData.class)
    public void verifyThatUserShouldBuildYouOwnComputerAndAddThemToCartSuccessfully(String processor, String ram, String hdd, String os, String software) {
        //Click on Computer tab
        // Click on Desktops link
        computerPage.mouseOverToComputerAndClickOnDesktop();
        // Click on product name "Build your own computer"
        desktopsPage.clickOnBuildYourOwnComputer();
        // Select processor "processor"
        buildYourOwnComputerPage.selectProcessor(processor);
        // Select RAM "ram"
        buildYourOwnComputerPage.selectRam(ram);
        // Select HDD "hdd"
        buildYourOwnComputerPage.selectHDD(hdd);
        // Select OS "os"
        buildYourOwnComputerPage.selectOS(os);
        // Select Software "software"
        buildYourOwnComputerPage.selectCheckBox(software);
        // Click on "ADD TO CART" Button
        buildYourOwnComputerPage.clickOnAddToCArtButton();
        // Verify message "The product has been added to your shopping cart"
        String expectedErrorMessage = "The product has been added to your shopping cart";
        String actualErrorMessage = buildYourOwnComputerPage.verifyProductAddToCartSuccessfully();
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage, "product added successfully text not display");
    }

}
