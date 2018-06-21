import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class matrsm {
    public static void main(String[] args)  {
        try {
            File in = new File("input.txt");
            FileWriter out = new FileWriter("output.txt");
            Scanner sc = new Scanner(in);
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] matr = new int[n][n];
            for(int i = 0; i < m; i++){
                    int k = sc.nextInt() - 1;
                    int g = sc.nextInt() - 1;
                    matr[k][g] = 1;
                    matr[g][k] = 1;
                }
            for(int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++)
                    out.write(Integer.toString(matr[i][j]) + " ");
                out.write("\n");
            }
            out.flush();
            out.close();
            System.exit(0);
        }catch (Exception e){
            System.out.println(e);
            System.exit(1);
        }
    }
}
