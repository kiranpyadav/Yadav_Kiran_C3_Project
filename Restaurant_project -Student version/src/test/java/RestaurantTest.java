import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;
//import static org.mockito.Mockito.when; 
//import static org.mockito.Mockito;
import static org.mockito.Mockito.when;

import java.time.LocalTime;

import org.junit.Test;

public class RestaurantTest {
    Restaurant restaurant;
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");
    //REFACTOR ALL THE REPEATED LINES OF CODE
  
   /*
    * This method initialize the Restaurant object instance and initialize the attributes.
    * Input Parameters: it takes boolean value. If true then mock object instance is created else normal object is created
    */
    public void createRestaurant_for_nonmock_methods(boolean isMock){
    	if(isMock == false)
    		restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
    	else
    		restaurant = spy(new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime));
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    
    
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE   	
    	LocalTime currentTime = LocalTime.parse("11:03:00");  
        createRestaurant_for_nonmock_methods(true);
        
        when(restaurant.getCurrentTime()).thenReturn(currentTime,currentTime);
                      
        boolean bValue = restaurant.isRestaurantOpen();
        assertTrue(bValue);
        assertEquals(currentTime, restaurant.getCurrentTime());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
    	LocalTime currentTime = LocalTime.parse("23:03:00");  
        createRestaurant_for_nonmock_methods(true);
        
        when(restaurant.getCurrentTime()).thenReturn(currentTime,currentTime);
                    
        boolean bValue = restaurant.isRestaurantOpen();
        assertFalse(bValue);
        assertEquals(currentTime, restaurant.getCurrentTime());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        
        createRestaurant_for_nonmock_methods(false);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

    	createRestaurant_for_nonmock_methods(false);
    	
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
    	
    	createRestaurant_for_nonmock_methods(false);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}