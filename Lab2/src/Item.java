public class Item implements Comparable<Item>{
    private String name;
    private ItemCondition condition;
    private double weight;
    private int quantity;

    public Item(String name, ItemCondition condition, double weight, int quantity) {
        this.name = name;
        this.condition = condition;
        this.weight = weight;
        this.quantity = quantity;
    }

    public void print(){
        System.out.println("Product: "+name+"\nCondition: "+condition+"\nWeight: "+ weight +"\nQuantity: "+quantity);
    }

    public String getName() {
        return name;
    }

    public void increaseQuantity(int value){
        this.quantity+=value;
    }

    public void decreaseQuantity(int value){
        if(this.quantity-value<=0)
            throw new IllegalArgumentException("Value too low!");
        else this.quantity-=value;
    }

    public ItemCondition getCondition() {
        return condition;
    }

    public double getWeight() {
        return weight;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int compareTo(Item o) {
        return this.name.compareTo(o.name);
    }
}
