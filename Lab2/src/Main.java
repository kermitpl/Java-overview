import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        FulfillmentCenterContainer container = new FulfillmentCenterContainer();
        container.addCenter("Magazyn", 30);
        container.addCenter("Magazyn", 30);

        FulfillmentCenter center = container.findEmpty().get(0);
        Item item = new Item("test", ItemCondition.NEW,
                5, 6);
        Item item2 = new Item("dupa", ItemCondition.USED,
                5, 3);
        Item item3 = new Item("zaqwsx", ItemCondition.USED,
                5, 5);
        center.addProduct(item);
        center.addProduct(item2);
        center.addProduct(item3);
        //center.summary();
        container.summary();
        container.removeCenter("Magazyn");
        container.summary();
    }
}
