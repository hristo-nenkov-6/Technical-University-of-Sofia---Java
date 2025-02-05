import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    public List<Item> items;

    public ShoppingCart() {
        items = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(){
        items.removeLast();
    }

    public double getTotal() {
        return items
                .stream()
                .map(x -> x.price)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public static class Item{
        private String itemInfo;
        private double price;

        public Item(String itemInfo, double price) {
            this.itemInfo = itemInfo;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new Item("Milk", 2.99));
        shoppingCart.addItem(new Item("Potatoes", 3.55));

        shoppingCart.removeItem();

    }
}
