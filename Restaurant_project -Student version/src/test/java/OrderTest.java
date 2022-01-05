import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

public class OrderTest {
    Order order;
    LocalDate orderDate = LocalDate.now();
    LocalTime orderTime = LocalTime.now();
    //REFACTOR ALL THE REPEATED LINES OF CODE
    
	RestaurantService service = new RestaurantService();
	Restaurant restaurant;
	LocalTime openingTime = LocalTime.parse("10:30:00");
	LocalTime closingTime = LocalTime.parse("22:00:00");
    //REFACTOR ALL THE REPEATED LINES OF CODE
  
   /*
    * This method initialize the Restaurant object instance and initialize the attributes.
    * Input Parameters: it takes boolean value. If true then mock object instance is created else normal object is created
    */
    public void createRestaurant_for_nonmock_methods(){
    	restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",100);
        restaurant.addToMenu("Vegetable lasagne", 250);
    }
  
   /*
    * This method initialize the Order object instance and initialize the attributes.
    * Input Parameters: it takes boolean value. If true then mock object instance is created else normal object is created
    */
    public void createOrder_for_nonmock_methods() throws itemNotFoundException{
    	//Initilize the Restaurant object
    	createRestaurant_for_nonmock_methods();
    	order =new Order("Kiran Yadav",orderDate,orderTime);
    	order.addItem("Sweet corn soup",restaurant.getMenu());
    		
    }

	//TDD
	//adding item to order list should increase size of orderList by 1
	//removing item to order list should decrease size of orderList by 1
	//adding false item to order list should throw exception and order size should be same as original
	//removing false item should throw exception
	
    @Test
    public void adding_item_to_orderlist_should_increase_orderlist_size_by_1_and_Item_return_should_be_not_null() throws itemNotFoundException{
        
    	createOrder_for_nonmock_methods();

        int initialMenuSize = order.getorderLists().size();
        order.addItem("Vegetable lasagne",restaurant.getMenu());
        assertEquals(initialMenuSize+1,order.getorderLists().size());

    }
    
    @Test
    public void adding_false_item_to_orderlist_should_have_same_orderlist_size_and_throw_exception() throws itemNotFoundException {
        
    	createOrder_for_nonmock_methods();
    	
        assertThrows(itemNotFoundException.class,
                ()->order.addItem("Item Not Exists",restaurant.getMenu()));

    }
    
    @Test
    public void removing_item_from_orderlist_should_decrease_orderlist_size_by_1() throws itemNotFoundException {

    	createOrder_for_nonmock_methods();
    	
        int initialMenuSize = order.getorderLists().size();
        order.removeFromOrderList("Sweet corn soup");
        assertEquals(initialMenuSize-1,order.getorderLists().size());
    }
    
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() throws itemNotFoundException{
    	
    	createOrder_for_nonmock_methods();

        assertThrows(itemNotFoundException.class,
                ()->order.removeFromOrderList("French fries"));
    }
    
    @Test
    public void calculated_total_amount_should_be_350() throws itemNotFoundException{
    	createOrder_for_nonmock_methods();
    	order.addItem("Vegetable lasagne",restaurant.getMenu());
    	int actualtotal = 350;
    	int calculatedTotal = order.getTotalAmount();
    	assertEquals(calculatedTotal,actualtotal);
    }
}
