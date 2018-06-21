import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class Main {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new File("input.txt"));

        int n = sc.nextInt();

        int[][] mat = new int[n][n];


        Map<Integer, Integer> map = new TreeMap<>();

        for (int i = 1; i <= n; i++)
            map.put(i, 0);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = (sc.nextInt());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1){
                    map.put(j + 1,i + 1);
                }
            }
        }

        FileWriter fw = new FileWriter("output.txt");

        for (Integer i : map.values()) {
            fw.append(String.valueOf(i)).append(" ");
        }

        sc.close();
        fw.close();
    }

}