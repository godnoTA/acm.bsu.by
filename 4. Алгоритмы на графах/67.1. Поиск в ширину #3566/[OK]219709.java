import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
        int n = Integer.parseInt(scanner.next());
        int mas[] = new int[n + 1];
        int matr[][] = new int[n + 1][n + 1];
        int i, j;
        for (i = 1; i < n + 1; i++) {
            for (j = 1; j < n + 1; j++) {
                matr[i][j] = scanner.nextInt();
            }
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        int hlp;
        queue.add(1);
        hlp = 0;

        for (i = 1; i < n + 1; i++) {
            if (mas[i] == 0) {
                queue.add(i);
                hlp++;
                mas[i] = hlp;
                while (!queue.isEmpty()) {
                    int znach = queue.poll();
                    for (j = 1; j < n + 1; j++) {
                        if (mas[j] == 0 && matr[znach][j] == 1) {
                            queue.add(j);
                            hlp++;
                            mas[j] = hlp;
                        }
                    }
                }


            }
        }
        //int y = mas[i];

        FileWriter fout = null;
        try {

            fout = new FileWriter("output.txt");
            for (i = 1; i < n + 1; i++) {
                fout.write(mas[i] + " ");
            }
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
