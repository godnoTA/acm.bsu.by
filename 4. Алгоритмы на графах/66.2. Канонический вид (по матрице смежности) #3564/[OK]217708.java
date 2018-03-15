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
        int j;
        int res [][] = new int [n][n];
        for (i = 0; i <= n - 1; i++) {
            for (j = 0; j <= n - 1; j++)
                res[i][j] = scanner.nextInt();
        }


        int P[] = new int [n];

       for (i = 0; i <= n - 1; i++) {
           for (j = 0; j <= n - 1; j++) {
               if (res[j][i] == 1) {
                   P[i] = j + 1;
               }
           }
       }
        FileWriter fout = null;
        try {
            fout = new FileWriter("output.txt");
            for (i = 0; i <= n - 1; i++) {
                fout.write(P[i] + " ");
            }
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

