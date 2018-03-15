import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try(FileInputStream fis=new FileInputStream("input.txt");
            FileOutputStream fos=new FileOutputStream("output.txt");
            PrintStream ps=new PrintStream(fos)) {
            Scanner sc = new Scanner(fis);
            int k=Integer.parseInt(sc.nextLine());
            int mas[]=new int[k+1];
            for (int i = 0; i < k - 1; i++) {
                String s=sc.nextLine();
                mas[i]=Integer.parseInt(s.split(" ")[0]);
            }
            String s=sc.nextLine();
            mas[k-1]=Integer.parseInt(s.split(" ")[0]);
            mas[k]=Integer.parseInt(s.split(" ")[1]);

            ps.print(multiplyOrder(mas));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static int multiplyOrder(int[] arg) {
        int n = arg.length - 1;
        int[][] mass = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            mass[i][i] = 0;
        }

        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                mass[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    mass[i][j] = Math.min(mass[i][j],
                            mass[i][k] + mass[k + 1][j] + arg[i - 1] * arg[k] * arg[j]);
                }
            }
        }
        return mass[1][n];
    }

}
