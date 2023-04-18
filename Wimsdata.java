import java.io.*;
import java.util.*;

class Wimsdata {
    private static int bottles, racks, imp = 1000, exp = 1000;
    private static List<String> dates = new ArrayList<>();
    private static List<Bottle> listBottles = new ArrayList<>();
    private static List<Rack> listRacks = new ArrayList<>();

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

        genDates();
        genRacks();
        genImports();
        genExports();
        genBottles();
    }

    public static void genDates() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("dates.csv"));
            String date;

            for (int year = 2020; year < 2023; year++) {
                for (int month = 1; month < 13; month++) {
                    for (int day = 1; day < 32; day++) {
                        date = String.valueOf(year) + '/' + String.valueOf(month) + '/' + String.valueOf(day);
                        dates.add(date);
                        
                        writer.write(date);
                        writer.newLine();

                        if (day == 28 && month == 2 && year != 2020) {
                            day = 1;
                            break;
                        }
                        else if (day == 29 && month == 2 && year == 2020) {
                            day = 1;
                            break;
                        }
                        else if (day == 30 && (month == 4 || month == 6 || month == 9 || month == 11)) {
                            day = 1;
                            break;
                        }
                        else if (day == 31) {
                            day = 1;
                            break;
                        }
                    }
                }
            }

            writer.close();
        }
        catch (Exception ex) {
            System.err.println(ex);
            System.exit(0);
        }
    }

    private static void genBottles() {
        try{
            for (int i = 0; i < bottles; i++) {
                Bottle bottle = new Bottle();
                listBottles.add(bottle);
                System.out.println(bottle.toString());
            }
        }
        catch (Exception ex) {
            System.err.println(ex);
            System.exit(0);
        }
    }

    private static void genRacks() {
        try {
            int length = (int) Math.sqrt((bottles * 0.10) / racks);

            for (int i = 0; i < racks; i++) {
                Rack rack = new Rack(i, length);
                listRacks.add(rack);
                System.out.println(rack.toString());
            }
        }
        catch (Exception ex) {
            System.err.println(ex);
            System.exit(0);
        }
    }

    private static void genImports() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("imports.csv"));
            Random rand = new Random();

            double cost;
            int ordered, arrived, supplier;

            for (int i = 0; i < imp; i++) {
                ordered = 1000 + rand.nextInt(dates.size() - 14);
                arrived = ordered + 5 + rand.nextInt(10);
                cost = Math.round((10 + (rand.nextDouble() * 20)) * 100.0) / 100.0;
                supplier = 1000 + rand.nextInt(40);

                writer.write(String.valueOf(ordered) + ',' + String.valueOf(arrived) + ',' + String.valueOf(cost) + ',' + String.valueOf(supplier));
                writer.newLine();
            }

            writer.close();
        }
        catch (Exception ex) {
            System.err.println(ex);
            System.exit(0);
        }
    }

    private static void genExports() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("exports.csv"));
            Random rand = new Random();

            double cost;
            int ordered, arrived, location;

            for (int i = 0; i < exp; i++) {
                ordered = 1000 + rand.nextInt(dates.size() - 14);
                arrived = ordered + 5 + rand.nextInt(10);
                cost = Math.round((10 + (rand.nextDouble() * 20)) * 100.0) / 100.0;
                location = 1000 + rand.nextInt(150);

                writer.write(String.valueOf(ordered) + ',' + String.valueOf(arrived) + ',' + String.valueOf(cost) + ',' + String.valueOf(location));
                writer.newLine();
            }

            writer.close();
        }
        catch (Exception ex) {
            System.err.println(ex);
            System.exit(0);
        }
    }
}