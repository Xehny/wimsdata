import java.util.List;
import java.util.Random;

public class Export {
    private int id, orderedDate, shippedDate, location;
    private String orderedDateStr, shippedDateStr;
    private double cost;
    private Random rand = new Random();

    public Export(List<String> dates, int seq, int firstImp) {
        id = 1000 + seq;
        orderedDate = firstImp + rand.nextInt(dates.size() - (firstImp - 986));
        orderedDateStr = dates.get(orderedDate - 1000);
        shippedDate = orderedDate + 5 + rand.nextInt(10);
        shippedDateStr = dates.get(shippedDate - 1000);
        cost = Math.round((10 + (rand.nextDouble() * 20)) * 100.0) / 100.0;
        location = 1000 + rand.nextInt(10);
    }

    public String toString() {
        return String.valueOf(orderedDateStr) + ',' + String.valueOf(shippedDateStr) + ',' + String.valueOf(cost) + ',' + String.valueOf(location);
    }

    public int getID() {
        return id;
    }

    public int getDate() {
        return orderedDate;
    }
}