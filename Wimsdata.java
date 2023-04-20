import java.io.*;
import java.util.*;

class Wimsdata {
    private static int bottles, racks, firstImp;
    private static List<String> dates = new ArrayList<>();
    private static List<Bottle> listBottles = new ArrayList<>(), listBotTemp = new ArrayList<>();
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
        writeCSV();
    }

    public static void genDates() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("dates.csv"));
            String date;

            for (int year = 2020; year < 2023; year++) {
                for (int month = 1; month < 13; month++) {
                    for (int day = 1; day < 32; day++) {
                        date = '"' + String.valueOf(day) + '/' + String.valueOf(month) + '/' + String.valueOf(year) + '"';
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
        //try {
            int length = (int) Math.sqrt((bottles * 0.20) / racks);

            for (int i = 0; i < racks; i++) {
                Rack rack = new Rack(i, length);
                listRacks.add(rack);
            }
        /* }
        catch (Exception ex) {
            System.err.println(ex);
            System.exit(0);
        } */
    }

    private static void genBottles() {
        // try{
            for (int i = 0; i < bottles; i++) {
                Bottle bottle = new Bottle(i);
                listBottles.add(bottle);
                listBotTemp.add(bottle);
            }
        /* }
        catch (Exception ex) {
            System.err.println(ex);
            System.exit(0);
        } */
    }

    private static void genImports() {
        //try {
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
        /* }
        catch (Exception ex) {
            System.err.println(ex);
            System.exit(0);
        } */
    }

    private static void genExports() {
        //try {
            firstImp = dates.size() + 1000;
            for (Bottle b : listBotTemp) {
                if (b.getImport().getDate() < firstImp) {
                    firstImp = b.getImport().getDate();
                }
            }

            int col = 0, row = 0, seq = 0;
            Rack rack = null;
            Export exp = new Export(dates, seq, firstImp);
            listExports.add(exp);

            while (listBotTemp.size() != 0) {
                Bottle bottle = listBotTemp.get(rand.nextInt(listBotTemp.size()));
                double r = rand.nextDouble();
                //System.out.println("reached");

                if (r < 0.95) {
                    while (true) {
                        if (firstImp >= exp.getDate()) {
                            seq += 1;
                            exp = new Export(dates, seq, firstImp);
                            listExports.add(exp);
                            //System.out.println("Breaking");
                            //System.out.println(String.valueOf(exp.getDate()));
                            break;
                        }
                        else if (bottle.getImport().getDate() < exp.getDate()) {
                            bottle.setExport(exp);
                            listBotTemp.remove(bottle);
    
                            r = rand.nextDouble();
                            if (r < 0.15) {
                                seq += 1;
                                exp = new Export(dates, seq, firstImp);
                                listExports.add(exp);
                                //System.out.println(String.valueOf(exp.getDate()));
                            }

                            //System.out.println("Breaking");
                            break;
                        }
                        System.out.println(listBotTemp.size());

                        bottle = listBotTemp.get(rand.nextInt(listBotTemp.size()));
                    }
                }
                else {
                    Boolean space = false;
                    while (space == false) {
                        rack = listRacks.get(rand.nextInt(listRacks.size()));
                        col = rand.nextInt(rack.getSize());
                        row = rand.nextInt(rack.getSize());
                        space = rack.checkPos(col, row);
                    }
                    
                    bottle.setRack(rack.getID(), col + 1, row + 1);
                    listBotTemp.remove(bottle);
                }

                firstImp = dates.size() + 1000;
                for (Bottle b : listBotTemp) {
                    if (b.getImport().getDate() < firstImp) {
                        firstImp = b.getImport().getDate();
                    }
                }

                //System.out.println(firstImp);
            }
        /* }
        catch (Exception ex) {
            System.err.println(ex);
            System.exit(0);
        } */
    }

    private static void writeCSV() {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("racks.csv"));
            writer.write("rName,rRowNo,rColumnNo,rIsChilled");
            writer.newLine();

            for (Rack rack : listRacks) {
                writer.write(rack.toString());
                writer.newLine();
            }
            writer.close();

            writer = new BufferedWriter(new FileWriter("bottles.csv"));
            writer.write("bYear,bBottleVolumeID,bAlcoholPercentage,bListCost,bRetailCost,bVarietyID,bWineBrandID,bRackID,bColumnPosition,bRowPosition,bImportID,bExportID");
            writer.newLine();

            for (Bottle bottle : listBottles) {
                writer.write(bottle.toString());
                writer.newLine();
            }
            writer.close();

            writer = new BufferedWriter(new FileWriter("imports.csv"));
            writer.write("iDateOrdered,iDateArrived,iShippingCost,iSupplierID");
            writer.newLine();

            for (Import imp : listImports) {
                writer.write(imp.toString());
                writer.newLine();
            }
            writer.close();

            writer = new BufferedWriter(new FileWriter("exports.csv"));
            writer.write("eDateOrdered,eDateShipped,eShippingCost,eLocationID");
            writer.newLine();

            for (Export exp : listExports) {
                writer.write(exp.toString());
                writer.newLine();
            }
            writer.close();
        }
        catch (IOException ex) {
            System.err.println(ex);
            System.exit(0);
        }
    }
}