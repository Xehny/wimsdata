import java.io.*;
import java.util.*;

class Wimsdata {
    private static int bottles, racks;
    private static List<String> dates = new ArrayList<>();
    private static List<Bottle> listBottles = new ArrayList<>();
    private static List<Rack> listRacks = new ArrayList<>();
    private static List<Import> listImports = new ArrayList<>();
    private static List<Export> listExports = new ArrayList<>();
    private static Random rand = new Random();

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
        genBottles();
        genImports();
        genExports();
        
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

    private static void genBottles() {
        try{
            for (int i = 0; i < bottles; i++) {
                Bottle bottle = new Bottle(i);
                listBottles.add(bottle);
                System.out.println(bottle.toString());
            }
        }
        catch (Exception ex) {
            System.err.println(ex);
            System.exit(0);
        }
    }

    private static void genImports() {
        try {
            int seq = 0;
            Import imp = new Import(dates, seq);
            listImports.add(imp);

            for (int i = 0; i < bottles; i++) {
                listBottles.get(i).setImport(imp);
                double r = rand.nextDouble();

                if (r < 0.10) {
                    seq += 1;
                    imp = new Import(dates, seq);
                    listImports.add(imp);
                }
            }
        }
        catch (Exception ex) {
            System.err.println(ex);
            System.exit(0);
        }
    }

    private static void genExports() {
        try {
            int col = 0, row = 0, seq = 0;
            Rack rack = null;
            Export exp = new Export(dates, seq);
            listExports.add(exp);

            while (listBottles.size() != 0) {
                Bottle bottle = listBottles.get(rand.nextInt(listBottles.size()));
                double r = rand.nextDouble();

                if (r < 0.95) {
                    if (bottle.getImport().getDate() < exp.getDate()) {
                        bottle.setExport(exp);
                        listBottles.remove(bottle);

                        r = rand.nextDouble();
                        if (r < 0.15) {
                            seq += 1;
                            exp = new Export(dates, seq);
                            listExports.add(exp);
                        }
                    }
                }
                else {
                    Boolean space = false;
                    while (space == false) {
                        rack = listRacks.get(rand.nextInt(listRacks.size()));
                        col = rand.nextInt(rack.getSize() + 1);
                        row = rand.nextInt(rack.getSize() + 1);
                        space = rack.checkPos(col, row);
                    }
                    
                    bottle.setRack(rack.getID(), col, row);
                    listBottles.remove(bottle);
                }
            }
        }
        catch (Exception ex) {
            System.err.println(ex);
            System.exit(0);
        }
    }
}