import java.io.*;
import java.util.*;

public class graf {
    public static void main(String[] args){
        try{
            String path = "input.txt";
            File in = new File(path);
            Scanner sc = new Scanner(in);
            int n = sc.nextInt();
            int m = sc.nextInt();
            int a, b;
            int[][] matrix = new int[n + 1][n + 1];
            FileWriter out = new FileWriter("output.txt");
            for(int i = 0; i < m; i++){
                a = sc.nextInt();
                b = sc.nextInt();
                if(a == b){
                    out.write("No");
                    out.write("\n");
                    out.write("No");
                    out.write("\n");
                    out.write("Yes");
                    sc.close();
                    out.flush();
                    out.close();
                    System.exit(0);
                }
                matrix[a][b]++;
                matrix[b][a]++;
            }
            for(int i = 1; i < n + 1; i++)
                for(int j = 0; j < n + 1; j++)
                    if(matrix[i][j] > 1){
                        out.write("No");
                        out.write("\n");
                        out.write("Yes");
                        out.write("\n");
                        out.write("Yes");
                        sc.close();
                        out.flush();
                        out.close();
                        System.exit(0);
                    }
            out.write("Yes");
            out.write("\n");
            out.write("Yes");
            out.write("\n");
            out.write("Yes");
            sc.close();
            out.flush();
            out.close();
            System.exit(0);
        }
        catch(Exception e){
            System.err.println(e);
            System.exit(1);
        }
    }
}
