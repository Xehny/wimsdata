import java.util.Random;

public class Rack {
    private int id, size, chill;
    private int[][] grid;
    private String name;
    private Random rand = new Random();

    public Rack(int seq, int s) {
        id = seq + 1000;
        name = "Rack " + String.valueOf(seq + 1);
        size = s;
        grid = new int[s][s];

        /* for (int i = 0; i < s; i++) {
            grid[i] = new int[s];
        } */

        if (rand.nextDouble() < 0.15) {
            chill = 1;
        }
        else {
            chill = 0;
        }
    }

    public String toString() {
        return name + ',' + String.valueOf(size) + ',' + String.valueOf(size) + ',' + String.valueOf(chill);
    }

    public int getID() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public Boolean checkPos(int x, int y) {
        if (grid[x][y] == 1) {
            return false;
        }
        else {
            grid[x][y] = 1;
            return true;
        }
    }
}