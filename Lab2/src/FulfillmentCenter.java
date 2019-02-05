import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FulfillmentCenter {
    private String name;
    private List<Item> items;
    private double maxCapacity;
    private double currentItemsWeight;

    public FulfillmentCenter(String name, double maxCapacity) {
        if(name == null || name.isEmpty() || maxCapacity == 0)
            throw new IllegalArgumentException();
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.items = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public int getPercentageFill(){
        return (int)(currentItemsWeight/maxCapacity)*100;
    }

    public boolean isEmpty(){
        return items.size()==0;
    }

    public void addProduct(Item product){
        if(maxCapacity-(currentItemsWeight+(product.getWeight()*product.getQuantity()))<0)
            System.err.println("Cannot add product! FulfillmentCenter capacity is too small.");
        else {
            for(Item item : items){
                if(item.compareTo(product)==0){
                    item.increaseQuantity(product.getQuantity());
                    currentItemsWeight+=product.getQuantity()*product.getWeight();
                    return;
                }
            }
            currentItemsWeight+=product.getQuantity()*product.getWeight();
            items.add(product);
        }
    }

    public void getProduct(Item product){
        for(Item item : items){
            if(item.compareTo(product)==0){
                if(item.getQuantity()>1) {
                    item.decreaseQuantity(1);
                    currentItemsWeight-=item.getWeight();
                    return;
                } else {
                    items.remove(item);
                    currentItemsWeight-=item.getWeight();
                    return;
                }
            }
        }
    }

    public void removeProduct(Item product){
        for(Item item : items){
            if(item.compareTo(product)==0){
                items.remove(item);
                currentItemsWeight-=item.getWeight()*item.getQuantity();
                return;
            }
        }
    }

    public Item search(String name){
        items.sort(Item::compareTo);
        int index = Collections.binarySearch(items,
                new Item(name, null, 0, 0),
                Comparator.comparing(Item::getName));
        if(index!=-1)
            return items.get(index);
        else return null;
    }

    public Item searchPartial(String text){
        items.sort(Item::compareTo);
        int index = Collections.binarySearch(items,
                new Item(text, null, 0, 0),
                (o1, o2) -> {
                        if(o1.getName().contains(o2.getName()))
                            return 0;
                        return o1.compareTo(o2);
                    }
                );
        if(index!=-1)
            return items.get(index);
        else return null;
    }

    public int countByCondition(ItemCondition condition){
        return items.stream()
                .filter(o -> o.getCondition().equals(condition))
                .collect(Collectors.toList()).size();
    }

    public void summary(){
        items.forEach(Item::print);
    }

    public List<Item> sortByName(){
        items.sort(Item::compareTo);
        return items;
    }

    public List<Item> sortByAmount(){
        items.sort(((o1, o2) ->
            Integer.compare(o2.getQuantity(), o1.getQuantity())
        ));
        return items;
    }

    public Item max(){
        return Collections.max(items, Comparator.comparingInt(Item::getQuantity));
    }

}
