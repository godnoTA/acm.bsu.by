
import java.io.*;
import java.util.StringTokenizer;

public class Test {
    static int[] mas;
    static int[][] ar;
    public static void main(String[] args) {
        try {
            BufferedReader in=new BufferedReader(new FileReader("input.txt"));
            BufferedWriter out=new BufferedWriter(new FileWriter("output.txt"));
            String str;
            int n=Integer.parseInt(in.readLine());
            n++;
            mas=new int[n];
            int k=0;
            ar=new int[n][n];
            for(int i=0;i<n;i++)
                for(int j=0;j<n;j++)
                    ar[i][j]=-1;
            StringTokenizer s=null;
            while((str=in.readLine())!=null) {
                s=new StringTokenizer(str);
                mas[k++] = Integer.parseInt(s.nextToken());
            }
            mas[k]=Integer.parseInt(s.nextToken());
            in.close();
            out.write(MatrixMultiplication(0,n-1)+"\n");
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int MatrixMultiplication(int l, int r) {
        if (ar[l][r] == -1) {
            if (l == r - 1)
                ar[l][r] = 0;
            else {
                ar[l][r] = Integer.MAX_VALUE;
                for (int i = l + 1; i <= r - 1; i++)
                    ar[l][r] = Integer.min(ar[l][r], mas[l] * mas[i] * mas[r] + MatrixMultiplication(l, i) + MatrixMultiplication(i, r));
            }
        }
        return ar[l][r];
    }
}
