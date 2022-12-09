package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.CartPage;
import pages.CheckOutStepOnePage;
import pages.CheckOutStepTwoPage;
import pages.CheckoutCompletePage;
import pages.InventoryPage;
import pages.LoginPage;

public class EndToEndTest extends TestBase {
	/*
	 * This test covers complete End to End test From login till CheckOut
	 */

	public LoginPage loginPage;
	public InventoryPage inventory;
	public CartPage cart;
	public CheckOutStepOnePage checkOutStepOne;
	public CheckOutStepTwoPage checkOutStepTwo;
	public CheckoutCompletePage checkOutComplete;

	@BeforeClass
	public void classSetUp() {
		loginPage = new LoginPage(getDriver());
		inventory = new InventoryPage(getDriver());
		cart = new CartPage(getDriver());
		checkOutStepOne = new CheckOutStepOnePage(getDriver());
		checkOutStepTwo = new CheckOutStepTwoPage(getDriver());
		checkOutComplete = new CheckoutCompletePage(getDriver());

	}

	@Test(priority = 0)
	public void loginTest() throws InterruptedException {
		loginPage.getUserName().sendKeys("standard_user");
		loginPage.getPassword().sendKeys("secret_sauce");
		loginPage.getLoginButton().click();
		Thread.sleep(3000);

		String expectedText = "PRODUCTS";
		String actualText = getDriver().findElement(By.cssSelector("span.title")).getText();
		Assert.assertEquals(actualText, expectedText);
		System.out.println("Login Test Passed");

	}

	@Test(priority = 1)
	public void selectProductAndCheckOutTest() {
		inventory.getAddTshirtButton().click();
		inventory.getGoToShoppingCartButton().click();
		cart.getCheckButton().click();
		checkOutStepOne.enterCustomerDetails("user47778", "Smith", "+1-12399230");
		checkOutStepOne.getContinueBtn().click();
		checkOutStepTwo.getFinishButton().click();
		checkOutComplete.getBackToHomeButton();

		System.out.println("End to End Test Execution completed...Test Passed");
	}

}
