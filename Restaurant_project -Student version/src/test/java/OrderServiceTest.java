import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

public class OrderServiceTest {
	OrderService oService = new OrderService();
	Order order;
	LocalDate orderDate = LocalDate.now();
	LocalTime orderTime = LocalTime.now();
	RestaurantService service = new RestaurantService();
	Restaurant restaurant;
	LocalTime openingTime = LocalTime.parse("10:30:00");
	LocalTime closingTime = LocalTime.parse("22:00:00");

	/*
	 * This method initialize the Restaurant object instance and initialize the
	 * attributes. Input Parameters: it takes boolean value. If true then mock
	 * object instance is created else normal object is created
	 */
	public void createRestaurant_for_nonmock_methods() {
		restaurant = service.addRestaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
		restaurant.addToMenu("Sweet corn soup", 100);
		restaurant.addToMenu("Vegetable lasagne", 250);
	}

	/*
	 * This method initialize the Order object instance and initialize the
	 * attributes. Input Parameters: it takes boolean value. If true then mock
	 * object instance is created else normal object is created
	 */
	public void createOrder_for_nonmock_methods() throws itemNotFoundException {
		createRestaurant_for_nonmock_methods();
		// Initilize the Order object
		order = oService.addOrder("Kiran Yadav",LocalDate.now(),LocalTime.now());
		order.addItem("Sweet corn soup", restaurant.getMenu());
		order.addItem("Vegetable lasagne", restaurant.getMenu());
	}

	// TDD
	// Creating new customer order should increase order by 1
	// Searching for existing order by Customer name should return order object
	// Searching for non exiting order by Customer name should throw exception
	// Removing existing order should decrease order size by 1
	// Removing non existing order should throw exception

	@Test
	public void searching_for_existing_order_should_return_expected_order_object()
			throws orderNotFoundException, itemNotFoundException {
		createOrder_for_nonmock_methods();

		Order returnOrder = oService.findOrderByCustomerName("Kiran Yadav");
		assertNotNull(returnOrder);
		assertEquals(order.getCustName(), order.getCustName());
		assertEquals(order.getorderLists().size(), 2);

	}

	@Test
	public void searching_for_non_existing_order_should_throw_exception()
			throws orderNotFoundException, itemNotFoundException {

		createOrder_for_nonmock_methods();

		assertThrows(orderNotFoundException.class, () -> oService.findOrderByCustomerName("ABC"));
	}

	@Test
	public void remove_order_should_reduce_list_of_orders_size_by_1()
			throws orderNotFoundException, itemNotFoundException {
		createOrder_for_nonmock_methods();

		int initialNumberOfOrders = oService.getOrders().size();
		oService.removeOrder("Kiran Yadav");
		assertEquals(initialNumberOfOrders - 1, oService.getOrders().size());

	}

	@Test
	public void removing_order_that_does_not_exist_should_throw_exception()
			throws orderNotFoundException, itemNotFoundException {
		createOrder_for_nonmock_methods();

		assertThrows(orderNotFoundException.class, () -> oService.removeOrder("Order does not exists"));

	}

	@Test
	public void add_order_should_increase_list_of_orders_size_by_1()
			throws orderNotFoundException, itemNotFoundException {

		createOrder_for_nonmock_methods();

		int initialNumberOfRestaurants = service.getRestaurants().size();

		Order newOrder = oService.addOrder("Siddhant Yadav", LocalDate.now(), LocalTime.now());
		newOrder.addItem("Sweet corn soup", restaurant.getMenu());

		assertEquals(initialNumberOfRestaurants + 1, oService.getOrders().size());

	}

}
