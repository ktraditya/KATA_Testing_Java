package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.CommonUtils.*;
import static utils.DriversUtils.*;

public class HomePage {

	public HomePage() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(className = "room-firstname")
	private WebElement firstName;

	@FindBy(className = "room-lastname")
	private WebElement lastName;

	@FindBy(className = "room-phone")
	private WebElement phone;

	@FindBy(className = "room-email")
	private WebElement email;

	@FindBy(xpath = "//button[contains(text(),'Book')]")
	private WebElement book;

	@FindBy(className = "rbc-toolbar-label")
	private WebElement monthYear;

	@FindBy(xpath = "/html/body/div[4]/div/div/div[1]/div[2]/p[2]")
	private WebElement dateRange;

	@FindBy(tagName = "h2")
	private WebElement roomCategoryIdentifier;

	@FindBy(xpath = "//button[contains(@class,'openBooking')]")
	private WebElement bookButton;

	public void goToRoomsCategory() {
		try {
			scrollToElement(roomCategoryIdentifier);
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.out.println("Error in the rooms category method");
		}
	}

	public void assertBookButtonDisplayed() {
		Assert.assertEquals(true, bookButton.isDisplayed());
	}

	public void navigateToHomePage() {
		getDriver().get("https://automationintesting.online/#/");
	}

	public void bookThisRoom() {
		bookButton.click();
	}

	public void enterDetails(String _firstname, String _surename, String _phone, String _email, String _checkinDate,
			String _checkoutDate) {
		firstName.sendKeys(_firstname);
		lastName.sendKeys(_surename);
		phone.sendKeys(_phone);
		email.sendKeys(_email);
		selectDateRange(_checkinDate, _checkoutDate);

	}

	// drag from date on to to date to select number of nights
	public void selectDateRange(String fromDate, String toDate) {
		WebElement from_Date = getDriver().findElement(By.xpath("//button[text()= '" + fromDate + "']"));
		WebElement to_Date = getDriver().findElement(By.xpath("//button[text()='" + toDate + "']"));
		Actions act = new Actions(getDriver());
		act.clickAndHold(to_Date).dragAndDrop(from_Date, to_Date).release().build().perform();
	}

	public void submitBookingDetails() {
		try {
		WebDriverWait wait = new WebDriverWait(getDriver(),30);
		wait.until(ExpectedConditions.elementToBeClickable(book));
		book.click();
		}
		catch (Exception e) {
		      System.out.println("Something went wrong while booking the room . Please try again later.");
			}
	}

	public String printMonthYear() {
		return monthYear.getText();

	}

	public String printDateRange() {
		return dateRange.getText();
	}
	
	public void closeBroswer() {
		getDriver().quit();
	}
}