import java.util.List;
import java.util.Random;

public class Import {
    private int id, orderedDate, arrivedDate, supplier;
    private String orderedDateStr, arrivedDateStr;
    private double cost;
    private Random rand = new Random();

    public Import(List<String> dates, int seq) {
        id = 1000 + seq;
        orderedDate = 1000 + rand.nextInt(dates.size() - 42);
        orderedDateStr = dates.get(orderedDate - 1000);
        arrivedDate = orderedDate + 5 + rand.nextInt(10);
        arrivedDateStr = dates.get(arrivedDate - 1000);
        cost = Math.round((10 + (rand.nextDouble() * 20)) * 100.0) / 100.0;
        supplier = 1000 + rand.nextInt(40);
    }
    
    public String toString() {
        return String.valueOf(orderedDateStr) + ',' + String.valueOf(arrivedDateStr) + ',' + String.valueOf(cost) + ',' + String.valueOf(supplier);
    }

    public int getID() {
        return id;
    }

    public int getDate() {
        return arrivedDate;
    }
}