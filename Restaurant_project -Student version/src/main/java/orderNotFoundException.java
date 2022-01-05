public class orderNotFoundException extends Throwable {
    public orderNotFoundException(String itemName) {
        super(itemName);
    }
}
