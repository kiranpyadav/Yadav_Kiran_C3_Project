import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;

import org.junit.Test;

public class OrderServiceTest {

	//TDD
	//Creating new customer order should increase order by 1
	//Searching for existing order by Customer name  should return order object
	//Searching for non exiting order by Customer name  should throw exception
	//Removing existing order should decrease order size by 1
	//Removing non existing order should throw exception
	
    @Test
    public void searching_for_existing_order_should_return_expected_order_object() throws orderNotFoundException {
        
	
    }

    @Test
    public void searching_for_non_existing_order_should_throw_exception() throws orderNotFoundException {

    	
    }

    @Test
    public void remove_order_should_reduce_list_of_orders_size_by_1() throws orderNotFoundException {

    	
    }

    @Test
    public void removing_order_that_does_not_exist_should_throw_exception() throws orderNotFoundException {

    	
    }

    @Test
    public void add_order_should_increase_list_of_orders_size_by_1(){

    	
    	
    }

}
