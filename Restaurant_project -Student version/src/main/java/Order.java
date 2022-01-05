import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String custName;
	public LocalDate orderDate;
    public LocalTime orderTime;
    private List<Item> orderLists = new ArrayList<Item>();
    
	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}
    
    public Order(String custName, LocalDate orderDate, LocalTime orderTime) {
		super();
		this.custName = custName;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
	}
    
    private Item findItemByName(String itemName){
        for(Item item: orderLists) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }
    
    public List<Item> getorderLists() {
    	List<Item> itemList = this.orderLists;
        return itemList;
    }
    
    public void addItem(String name, List<Item> menuList) throws itemNotFoundException{
    	boolean bItemExists = false;
    	for(Item item: menuList){
    		if(name.equalsIgnoreCase(item.getName())){
    			bItemExists = true;
    	        Item newItem = new Item(name,item.getPrice());
    	        orderLists.add(newItem);
    	        break;
    		}
    	}
    	if(bItemExists == false)
    		throw new itemNotFoundException(null);
    }
    
    public void removeFromOrderList(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        orderLists.remove(itemToBeRemoved);
    }
    
    private int totalAmount(){
    	int totalAmt = 0;
        for(Item item: orderLists) {
            totalAmt = totalAmt + item.getPrice();                
        }
        return totalAmt;
    }
    
    public int getTotalAmount(){
    	return totalAmount();
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
