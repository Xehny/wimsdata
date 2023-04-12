import java.io.*;
import java.util.*;

class Wimsdata {
    private static int bottles;
    private static int racks;
    private static int imp = 1000;
    private static int exp = 1000;

    public static void main (String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: Wimsdata <bottles> <racks>");
            return;
        }

        try {
            bottles = Integer.parseInt(args[0]);
            racks = Integer.parseInt(args[1]);

            if (bottles < 1 || racks < 1) {
                System.err.println("Error: Arguments must be greater than 0");
                return;
            }
        }
        catch (Exception ex) {
            System.err.println("Error: Arguments must be integers");
            return;
        }

        genBottles();
        genRacks();
    }

    private static void genBottles() {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("bottles.csv"));
            Random rand = new Random();

            int year, volume, variety, branch;
            double alc, list, retail;

            for (int i = 0; i < bottles; i++) {
                year = 1990 + rand.nextInt(30);
                volume = randVolume(rand.nextInt(10000));
                alc = Math.round((3 + (rand.nextDouble() * 17)) * 100.0) / 100.0;
                list = Math.round((5 + (rand.nextDouble() * 120)) * 100.0) / 100.0;
                retail = Math.round((5 + (list * 1.2)) * 100.0) / 100.0;
                variety = 1000 + rand.nextInt(32);
                branch = 1000 + rand.nextInt(120);;

                writer.write(String.valueOf(year) + ',' + String.valueOf(volume) + ',' + String.valueOf(alc) + ',' + String.valueOf(list) + ',' + String.valueOf(retail) + ',' + String.valueOf(variety) + ',' + String.valueOf(branch) + ',' + String.valueOf(imp) + ',');

                if (rand.nextDouble() < 0.05) {
                    writer.write("NULL");
                }
                else if (rand.nextDouble() < 0.25) {
                    writer.write(String.valueOf(exp));
                    exp += 1;
                }
                else {
                    writer.write(String.valueOf(exp));
                }

                if (rand.nextDouble() < 0.15) {
                    imp += 1;
                }

                writer.newLine();
            }
            
            writer.close();
        }
        catch (Exception ex) {
            System.err.println(ex);
            System.exit(0);
        }
    }

    private static void genRacks() {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("racks.csv"));
            Random rand = new Random();

            String name;
            int length, chill;

            length = (int) Math.sqrt((bottles * 0.10) / racks);

            for (int i = 0; i < racks; i++) {
                name = "\'Rack " + String.valueOf(i + 1) + "\'";

                if (rand.nextDouble() < 0.15) {
                    chill = 1;
                }
                else {
                    chill = 0;
                }

                writer.write(name + ',' + String.valueOf(length) + ',' + String.valueOf(length) + ',' + String.valueOf(chill));
                writer.newLine();
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