import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedHashSet;
import java.util.Scanner;


public class Table 
{
    private static int m;
    private static int c;
    private static int n;
    private static Integer[] x;
    private static int[] sol;
    private static LinkedHashSet<Integer> hs = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        read();
        rab();
        File fout = new File("output.txt");
        PrintStream ps = new PrintStream(fout);
        for (int k = 0; k < m; k++)
        {
            ps.print(sol[k] + " ");
        }
    }

    public static void read() throws IOException
    {
        Scanner sc = new Scanner(new File("input.txt"));
        m = sc.nextInt();
        c = sc.nextInt();
        n = sc.nextInt();
        for (int i = 0; i < n; i++)
        {
            hs.add(sc.nextInt());
        }
        x = hs.toArray(new Integer[hs.size()]);
        for (int i = 0; i < x.length; i++)
        {
            System.out.println(x[i]);
        }
    }

    public static void rab() {
        sol = new int[m];
        for (int i = 0; i < m; i++)
        {
            sol[i] = -1;
        }
        int mm;
        for (int k = 0; k < x.length; k++)
        {
            for (int i = 0; i < m; i++)
            {
                mm = ((x[k] % m) + c * i) % m;
                if (sol[mm] == -1)
                {
                    sol[mm] = x[k];
                    break;
                }
            }
        }
    }
}