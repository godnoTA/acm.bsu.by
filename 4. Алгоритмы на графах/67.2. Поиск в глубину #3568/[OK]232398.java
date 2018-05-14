import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    static boolean[] isVisited;
    static int mas[][];
    static int count;
    static int[] result;
    static int qq = 1;

    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("input.txt");
             FileOutputStream fos = new FileOutputStream("output.txt");
             Scanner sc = new Scanner(fis);
             PrintStream ps = new PrintStream(fos)) {
            count = Integer.parseInt(sc.nextLine());
            mas = new int[count][count];
            for (int i = 0; i < count; i++) {
                String arr[] = sc.nextLine().split(" ");
                for (int j = 0; j < count; j++) {
                    mas[i][j] = Integer.parseInt(arr[j]);
                }
            }

            result = new int[count];

            isVisited = new boolean[count];
            for (int w = 0; w < count; w++) {

                int node = w + 1;
                if (isVisited[node-1]){
                    continue;
                }
                fun (node);
            }
            for (int i = 0; i < count; i++) {
                ps.print(result[i] + " ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void fun(int node) {

        isVisited[node - 1] = true;
        result[node - 1] = qq++;

        for (int i = 0; i < count; i++) {

            if (mas[node - 1][i] != 0 && !isVisited[i]) {
                fun(i+1);
            }
        }
    }
}
