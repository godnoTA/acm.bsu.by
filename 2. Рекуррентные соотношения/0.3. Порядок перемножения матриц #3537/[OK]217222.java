import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int count = 0;
        int[] N = {};
        int end = 0;
        try (Scanner sc = new Scanner(new FileReader("input.txt"))) {
            count = sc.nextInt();
            N = new int[count + 1];
            int i = 0;
            while (sc.hasNext()) {
                N[i++] = sc.nextInt();
                end = sc.nextInt();
            }
            N[i] = end;
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        int[][] mas = new int[count][count];
        for (int i = 0; i < count; i++)
            mas[i][i] = 0;
        for (int g = 1; g < count; g++) {
            for (int i = 0; i < count - g; i++) {
                int j = i + g;
                mas[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < g; k++) {
                    mas[i][j] = Math.min(mas[i][j], mas[i][i + k] + mas[i + k + 1][j] + N[i] * N[i + k + 1] * N[j + 1]);
                }
            }
        }
        int result = mas[0][mas.length - 1];
        try {
            PrintWriter out = new PrintWriter(new File("output.txt"));
            out.print(result);
            out.close();
        }catch (FileNotFoundException ex){}
    }
}