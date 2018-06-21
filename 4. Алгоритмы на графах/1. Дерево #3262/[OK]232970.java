import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class tree{
    static int matr[][], u[], c = 0;
    static void check(int v, int n){
        u[v] = 1;
        c++;
        for(int i = 0; i < n; i++)
            if(matr[v][i] == 1 && u[i] == 0)
                check(i, n);
    }
    public static void main(String[] args) {
        try{
            File in = new File("input.txt");
            Scanner sc = new Scanner(in);
            int n = sc.nextInt();
            matr = new int[n][n];
            u = new int[n];
            int e = 0;
            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++) {
                    matr[i][j] = sc.nextInt();
                    e += matr[i][j];
                }
            e /= 2;
            check(0, n);
            FileWriter out = new FileWriter("output.txt");
            if(c == n && e == n - 1)
                out.write("Yes");
            else
                out.write("No");
            out.flush();
            out.close();
        }catch(Exception e){
            System.out.println(e);
            System.exit(1);
        }
    }
}