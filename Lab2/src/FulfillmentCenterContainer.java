
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FulfillmentCenterContainer {
    private Map<String, FulfillmentCenter> map;

    public FulfillmentCenterContainer() {
        this.map = new LinkedHashMap<>();
    }

    public void addCenter(String name, double capacity){
        if(name == null || name.isEmpty() || capacity == 0){
            throw new IllegalArgumentException();
        } else {
            FulfillmentCenter center = new FulfillmentCenter(name, capacity);
            map.put(name, center);
        }
    }

    public void removeCenter(String name){
        map.remove(name);
    }

    public List<FulfillmentCenter> findEmpty(){
        return map.values()
                .stream()
                .filter(FulfillmentCenter::isEmpty)
                .collect(Collectors.toList());
    }

    public void summary(){
        for(FulfillmentCenter center : map.values()){
            System.out.println(center.getName()+": percentage fill - "+center.getPercentageFill()+"%");
        }
    }
}
