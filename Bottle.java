import java.util.Random;

public class Bottle {
    private int year, volume, variety, brand, imp, exp;
    private double alc, list, retail;
    private Random rand = new Random();

    public Bottle() {
        year = 1990 + rand.nextInt(30);
        volume = randVolume();
        alc = Math.round((3 + (rand.nextDouble() * 17)) * 100.0) / 100.0;
        list = Math.round((5 + (rand.nextDouble() * 120)) * 100.0) / 100.0;
        retail = Math.round((5 + (list * 1.2)) * 100.0) / 100.0;
        variety = 1000 + rand.nextInt(32);
        brand = 1000 + rand.nextInt(100);
    }

    public String toString() {
        return String.valueOf(year) + ',' + String.valueOf(volume) + ',' + String.valueOf(alc) + ',' + String.valueOf(list) + ',' + String.valueOf(retail) + ',' + String.valueOf(variety) + ',' + String.valueOf(brand) + ',' + String.valueOf(imp) + ',' + String.valueOf(exp);
    }

    private int randVolume() {
        int vol, r = rand.nextInt(10000);

        if (r < 20) {
            vol = 1000;
        }
        else if (r < 100) {
            vol = 1001;
        }
        else if (r < 400) {
            vol = 1002;
        }
        else if (r < 9600) {
            vol = 1003;
        }
        else if (r < 9900) {
            vol = 1004;
        }
        else if (r < 9960) {
            vol = 1005;
        }
        else if (r < 9980) {
            vol = 1006;
        }
        else if (r < 9990) {
            vol = 1007;
        }
        else if (r < 9995) {
            vol = 1008;
        }
        else if (r < 9998) {
            vol = 1009;
        }
        else {
            vol = 1010;
        }
        
        return vol;
    }
}
