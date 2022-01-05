import java.time.LocalTime;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;

public class RestaurantServiceTest {

	RestaurantService service = new RestaurantService();
	Restaurant restaurant;
	LocalTime openingTime = LocalTime.parse("10:30:00");
	LocalTime closingTime = LocalTime.parse("22:00:00");
	// REFACTOR ALL THE REPEATED LINES OF CODE

	public void createRestaurant_for_Test_methods() {

		restaurant = service.addRestaurant("Amelie's cafe", "Chennai", openingTime, closingTime);

		restaurant.addToMenu("Sweet corn soup", 119);
		restaurant.addToMenu("Vegetable lasagne", 269);
	}

	// >>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	@Test
	public void searching_for_existing_restaurant_should_return_expected_restaurant_object()
			throws restaurantNotFoundException {
		// WRITE UNIT TEST CASE HERE
		createRestaurant_for_Test_methods();

		Restaurant returnRestaurant = service.findRestaurantByName("Amelie's cafe");
		assertNotNull(returnRestaurant);
		assertEquals(restaurant.getName(), returnRestaurant.getName());
		assertEquals(restaurant.getMenu().size(), returnRestaurant.getMenu().size());

	}

	// You may watch the video by Muthukumaran on how to write exceptions in
	// Course 3: Testing and Version control: Optional content
	@Test
	public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
		// WRITE UNIT TEST CASE HERE
		createRestaurant_for_Test_methods();

		assertThrows(restaurantNotFoundException.class, () -> service.findRestaurantByName("ABC"));
	}
	// <<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>

	// >>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING
	// RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	@Test
	public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
		createRestaurant_for_Test_methods();

		int initialNumberOfRestaurants = service.getRestaurants().size();
		service.removeRestaurant("Amelie's cafe");
		assertEquals(initialNumberOfRestaurants - 1, service.getRestaurants().size());
	}

	@Test
	public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
		createRestaurant_for_Test_methods();

		assertThrows(restaurantNotFoundException.class, () -> service.removeRestaurant("Pantry d'or"));
	}

	@Test
	public void add_restaurant_should_increase_list_of_restaurants_size_by_1() {
		createRestaurant_for_Test_methods();

		int initialNumberOfRestaurants = service.getRestaurants().size();
		service.addRestaurant("Pumpkin Tales", "Chennai", LocalTime.parse("12:00:00"), LocalTime.parse("23:00:00"));
		assertEquals(initialNumberOfRestaurants + 1, service.getRestaurants().size());
	}
	// <<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING
	// RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}