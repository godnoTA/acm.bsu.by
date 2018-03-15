import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by Drys on 30.03.2016.
 */
public class Run {
    Run(){
        Map<Integer,Cell> tree = new TreeMap<>();

        try(Scanner sc = new Scanner(new File("input.txt"))) {
            int i = 0;
            //sc.nextInt();
            while(sc.hasNextInt()) {

                tree.put(sc.nextInt(), new Cell(i,0));
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cell arr[][] = new Cell[tree.size()][tree.size()];
        int o = 0;
        for(Cell cell:tree.values()) {
            arr[o][o] = cell;
            o++;
        }
        int n = tree.size() - 1;
        for(int i = 0; i < n;i++) {
            for(int g = 0; g < n - i;g++) {
                redactCell(arr,g + 1 + i,g);
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(new File("output.txt"));
            fileWriter.write(arr[n][0].toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        };
    }
    private void redactCell(Cell arr[][], int x,int y) {
        int min = Integer.MAX_VALUE;
        for(int i = y, j = 1; i < x;i++,j++) {
            int e = Math.abs(arr[i][y].getColumn() - arr[x][y + j].getColumn()) + arr[i][y].getSteps() + arr[x][y + j].getSteps();
            min = (min <= e) ? min : e;
        }
        arr[x][y] = new Cell(arr[x][x].getColumn(),min);
    }
    private class Cell implements Comparable<Cell>{
        int column;
        int steps;

        public Cell(int column, int steps) {
            this.column = column;
            this.steps = steps;
        }

        public int getColumn() {
            return column;
        }

        public int getSteps() {
            return steps;
        }
        public void setSteps(int x) {
            this.steps = x;
        }

        @Override
        public int compareTo(Cell o) {
            return this.steps - o.getSteps();
        }

        @Override
        public String toString() {
            return Integer.valueOf(steps).toString();
        }
    }

    public static void main(String[] args) {
        Run run = new Run();
    }
}
