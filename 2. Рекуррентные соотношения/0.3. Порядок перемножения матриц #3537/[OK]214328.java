import java.io.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(new File("input.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt",false));
        int n = input.nextInt();
        Matrix[]l = new Matrix[n];
        for(int i = 0; i <n;i++)
        {
            l[i] = read(input);
        }
        bw.write(Integer.toString(MinOrd(GetMas(l))));
        input.close();
        bw.close();

    }
    private static Matrix read(Scanner in) throws IOException {
        Matrix m = new Matrix(in.nextInt(), in.nextInt());
        return m;
    }
    private static int[] GetMas(Matrix[] a)
    {
        int[]p = new int[a.length+1];
        for(int i = 0; i <a.length;i++)
        {
            p[i] = a[i].n;
        }
        p[p.length-1] = a[a.length-1].m;
        return p;
    }
    public static int MinOrd(int[]razm)
    {
        int tmp = razm.length;
        int[][]d = new int[tmp][tmp];
        for (int i = 1; i < tmp; i++) {d[i][i] = 0;}
        for (int l = 2; l < tmp; l++) {
            for (int i = 1; i < tmp - l + 1; i++) {
                int j = i + l - 1;
                d[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    d[i][j] = Math.min(d[i][j],
                            d[i][k] + d[k + 1][j] + razm[i - 1] * razm[k] * razm[j]);
                }
            }
        }
        return d[1][tmp-1];

    }

}
class Matrix
{
    int n;
    int m;
    Matrix(int nt, int mt){n = nt;m = mt;}
}