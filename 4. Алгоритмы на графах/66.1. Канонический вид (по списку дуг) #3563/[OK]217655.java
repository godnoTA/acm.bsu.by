import java.util.Scanner;
import java.io.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
        int n = scanner.nextInt();
        int i;
        if (n >= 1 && n <= 100000) {
            int P[] = new int[n];
            int u;
            int v;
            while (scanner.hasNextInt()) {
                u = scanner.nextInt();
                v = scanner.nextInt();
                P [v - 1] = u;
            }

            FileWriter fout = null;
            try {
                fout = new FileWriter("output.txt");
                for (i = 0; i < n; i++) {
                    fout.write(P[i] + " ");
                }
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }



        }
    }
}