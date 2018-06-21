import java.io.*;
import java.util.StringTokenizer;

public class Test {
    public static int algotythmMine(int[][] mas, int input, int output) {
        for (int i = 0;;) {
            int temp = calculating(mas, new boolean[mas.length], input, output, Integer.MAX_VALUE);
            if (temp == 0)
                return i;
            i += temp;
        }
    }

    static int n,countIn,countOut;
    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader("input.in"));
            StringTokenizer str=new StringTokenizer(in.readLine());
            n=Integer.parseInt(str.nextToken());
            countIn=Integer.parseInt(str.nextToken());
            countOut=Integer.parseInt(str.nextToken());
            int[][] matrix=new int[n+2][n+2];
            for(int i=0;i<n+2;i++)
                for(int j=0;j<n+2;j++)
                    matrix[i][j]=0;

            for(int i=1;i<n+1;i++) {
                str=new StringTokenizer(in.readLine());
                for (int j = 1; j < n + 1; j++)
                    matrix[i][j] = Integer.parseInt(str.nextToken());
            }

            str=new StringTokenizer(in.readLine());
            for(int i=0;i<countIn;i++)
            {
                int k=Integer.parseInt(str.nextToken());
                matrix[0][k]=1;
                matrix[k][0]=1;
            }
            showMatrix(matrix,n+2);
            str=new StringTokenizer(in.readLine());
            for(int i=0;i<countOut;i++)
            {
                int k=Integer.parseInt(str.nextToken());
                matrix[n+1][k]=1;
                matrix[k][n+1]=1;
            }
            in.close();
            for(int i=1;i<n+1;i++)
                matrix[i][i]=1;
            BufferedWriter out=new BufferedWriter(new FileWriter("output.out"));
            out.write(algotythmMine(matrix, 0, n+1)+"\n");
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    static void showMatrix(int[][] matrix,int raz){
        for(int i=0;i<raz;i++) {
            for (int j = 0; j < raz; j++)
                System.out.print(matrix[i][j]+" ");
            System.out.println("");
        }
    }
    static int calculating(int[][] mas, boolean[] flag, int i, int j, int z) {
        if (i == j)
            return z;
        flag[i] = true;
        for (int v = 0; v < flag.length; v++)
            if (!flag[v] && mas[i][v] > 0) {
                int result = calculating(mas, flag, v, j, Math.min(z, mas[i][v]));
                if (result > 0) {
                    mas[i][v] -= result;
                    mas[v][i] += result;
                    return result;
                }
            }
        return 0;
    }
}
