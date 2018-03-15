import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {

    public static int multiplyOrder(int[] mas) {
        int n = mas.length - 1;
        int[][] f = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            f[i][i] = 0;
        }

        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                f[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    f[i][j] = Math.min(f[i][j],
                            f[i][k] + f[k + 1][j] + mas[i - 1] * mas[k] * mas[j]);
                }
            }
        }
        return f[1][n];
    }

    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (java.io.FileNotFoundException ex) {
            System.out.println("Файл не найден!");
            return;
        }
        int razm=0;
        int kol = scanner.nextInt();
        int[] massiv=new int [kol+1];
        for (int i = 0; i < kol; i++){
                razm=scanner.nextInt();
                massiv[i]=razm;
                razm=scanner.nextInt();
        }
        massiv[kol]= razm;

        try{
            FileWriter fileWriter = new FileWriter("output.txt");
            String text = String.valueOf(multiplyOrder(massiv));
            fileWriter.write(text);
            fileWriter.flush();
        }
        catch(Exception e){}
    }
}
