package features.step_definitions;

import java.util.List;
import java.util.Map;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;

public class BookingSteps {

	private String toDate = "";
	private String fromDate = "";

	HomePage homePage = new HomePage();

	@Given("User access the hotel booking website in his browser")
	public void user_access_the_hotel_booking_website_in_his_browser() {
		homePage.navigateToHomePage();
	}

	@Given("User clicks on the Book this room button")
	public void user_clicks_on_the_book_this_room_button() {
		homePage.bookThisRoom();
	}

	@Given("User enter valid details")
	public void user_enter_valid_details(DataTable table) {
		List<Map<String, String>> rows = table.asMaps(String.class, String.class);

		for (Map<String, String> columns : rows) {
			homePage.enterDetails(columns.get("firstname"), columns.get("lastname"), columns.get("phone"),
					columns.get("email"), columns.get("checkin_date"), columns.get("checkout_date"));
			toDate = columns.get("checkout_date");
			fromDate = columns.get("checkin_date");
		}
	}

	@When("user clicks on the Book button")
	public void user_clicks_on_the_book_button() {
		homePage.submitBookingDetails();
	}

	@Then("user verifies the booking dates in the popup")
	public void user_verifies_the_booking_dates_in_the_popup() {

		System.out.println(fromDate + " " + homePage.printMonthYear() + " " + toDate + " " + homePage.printMonthYear());
		try {
		System.out.println(homePage.printDateRange());
		}
		catch (Exception e) {
		      System.out.println("Something went wrong while booking the room . Please try again later.");
			} finally {
				homePage.closeBroswer();
			}
		
		

	}

}
