import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class BFS {
    static void meth(int[][] matrix, int[] path, ArrayList<Integer> v, int str, int n, int k)
    {
        for (int j = 0; j < n; j++)
        {
            if (matrix[str - 1][j] == 1 && path[j]==0)
            {
                v.add(j + 1);
                path[j] = k;
                k++;
                matrix[str - 1][j] = 0;
            }
        }
        if (v.size()!=0)
        {
            int str_new = v.get(0);
            v.remove(0);
            meth(matrix, path, v, str_new, n, k);
        }
        else {
            for (int i = 0; i < n; i++) {
                if (path[i] == 0) {
                    path[i]=k;
                    k++;
                    meth(matrix, path, v, i + 1, n, k);
                }
            }
        }
    }

        public static void main(String[] arg) throws IOException {
        try{
            Scanner in = new Scanner(new File("input.txt"));
            PrintWriter out = new PrintWriter("output.txt");

            int n = in.nextInt(), k=1;
            int[][] matrix = new int[n][n];
            int[] path = new int[n];

            ArrayList<Integer> v = new ArrayList<>();

            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                     matrix[i][j]= in.nextInt();
            }


            path[0]=k;
            k++;
            meth(matrix, path, v, 1, n, k);

            for (int i = 0; i < n; i++)
               out.print(path[i]+" ");

            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
