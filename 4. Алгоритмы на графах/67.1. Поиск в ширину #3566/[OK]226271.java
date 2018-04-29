import java.io.*;
import java.util.ArrayDeque;
import java.util.Scanner;


public class Main {

    static int metka = 1;

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new File("input.txt"));
        int n = sc.nextInt();

        int [][]smej_mat = new int[n][n];
        int []metki = new int[n];
        for (int i = 0; i < n; i++){
            for (int j=0; j<n; j++){
                smej_mat[i][j] = sc.nextInt();
            }

        }

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (metki[i] == 0) {
                metki[i] = metka++;
                queue.add(i);
            }
            while (!queue.isEmpty()) {
                int temp = queue.pop();
                for (int j = 0; j < smej_mat[i].length; j++) {
                    if (smej_mat[temp][j] == 1 && metki[j] == 0) {
                        queue.add(j);
                        metki[j] = metka++;
                    }
                }
            }
        }

        FileWriter fw = new FileWriter("output.txt");
        for (int i : metki)
            fw.append(String.valueOf(i)).append(" ");
        sc.close();
        fw.close();
    }

}