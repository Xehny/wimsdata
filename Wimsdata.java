import java.io.*;
import java.util.*;

class Wimsdata {
    public static void main (String[] args) {
        if (args.length != 4) {
            System.out.println("Usage: Wimsdata <bottles> <racks> <imports> <exports>");
            return;
        }
        
        int bottles, racks, imports, exports;

        try {
            bottles = Integer.parseInt(args[0]);
            racks = Integer.parseInt(args[1]);
            imports = Integer.parseInt(args[2]);
            exports = Integer.parseInt(args[3]);

            if (bottles < 1 || racks < 1 || imports < 1 || exports < 1) {
                System.err.println("Error: Arguments must be greater than 0");
                return;
            }
        }
        catch (Exception ex) {
            System.err.println("Error: Arguments must be integers");
            return;
        }

        genBottles(bottles, racks, imports, exports);
    }

    private static void genBottles(int bottles, int racks, int imports, int exports) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("bottles.csv"));
            Random rand = new Random();

            int year, volume, variety, branch, rack, imp, exp;
            double alc, list, retail;

            for (int i = 0; i < bottles; i++) {
                year = 1990 + rand.nextInt(30);
                volume = randVolume(rand.nextInt(10000));
                alc = Math.round((3 + (rand.nextDouble() * 19)) * 100) / 100;
                list = Math.round((5 + (rand.nextDouble() * 120)) * 100) / 100;
                retail = Math.round((5 + (list * 1.2)) * 100) / 100;
                variety = 1000 + rand.nextInt(32);
                branch = 1000 + rand.nextInt(120);
                rack = 1000 + rand.nextInt(racks);
                imp = 1000 + rand.nextInt(imports);
                exp = 1000 + rand.nextInt(exports);
                writer.write(String.valueOf(year) + ',' + String.valueOf(volume) + ',' + String.valueOf(alc) + ',' + String.valueOf(list) + ',' + String.valueOf(retail) + ',' + String.valueOf(variety) + ',' + String.valueOf(branch) + ',' + String.valueOf(rack) + ',' + String.valueOf(imp) + ',' + String.valueOf(exp));
            }
            
            writer.close();
        }
        catch (Exception ex) {
            System.err.println(ex);
            System.exit(0);
        }
    }

    private static int randVolume(int r) {
        int vol;

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