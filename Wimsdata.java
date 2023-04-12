import java.io.*;

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

        System.out.println("Correct args!");
    }
}