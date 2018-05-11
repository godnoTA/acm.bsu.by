

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static boolean bool = true;
    public static int p = 1;

    private void solve() throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter(new File("output.txt"));
        int n = in.nextInt();

        int mtr[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mtr[i][j] = in.nextInt();
            }
        }

        int metk[] = new int[n];

        for (int i = 0; i < n; i++) {
            metk[i] = 0;
        }



        for (int i = 0; i < n; i++) {

            if (metk[i] == 0) {
                dfs(i, mtr, metk);
                p++;
            }
        }


            for (int i = 0; i < n; i++)
            {
                out.print(metk[i]);
                out.print(" ");
                out.flush();
            }




    }


    public int invert(int p){
        if(p == 1)
            return 2;
        else
            return 1;
    }

    private void dfs (int i,int mtr[][], int metk[]){
        metk[i] = p;
        for (int j = 0; j < mtr[i].length; j++){
            if (mtr[i][j] == 1 && metk[j] == 0){
                p++;
                dfs(j, mtr, metk);
            }

        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        new Main().solve();

    }
}
