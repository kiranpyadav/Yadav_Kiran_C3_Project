import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
	private static List<Order> Orders = new ArrayList<>();
	
	public Order findOrderByCustomerName(String customerName) throws orderNotFoundException{
        for(Order order: Orders) {
            if(order.getCustName().equalsIgnoreCase(customerName))
                return order;
        }
        throw new orderNotFoundException(null);
        //return null;
    }
	
    public Order addOrder(String custName, LocalDate orderDate, LocalTime orderTime) {
		Order order = new Order(custName, orderDate, orderTime);
		Orders.add(order);
		return order;
	}
    
    public Order removeOrder(String customerName) throws orderNotFoundException {
        Order orderToBeRemoved = findOrderByCustomerName(customerName);
        Orders.remove(orderToBeRemoved);
        return orderToBeRemoved;
    }

    public List<Order> getOrders() {
        return Orders;
    }
	
}
