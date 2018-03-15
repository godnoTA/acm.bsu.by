import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CanonD {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter pw = new PrintWriter(new File("output.txt"));
        int N = in.nextInt();
        int[] parent = new int[N];

        for(int i = 0; i < N-1 ; i++)
        {
            int n = in.nextInt();
            int m = in.nextInt()-1;
            parent[m] = n;
        }
        for(int val: parent)
            pw.print(val+" ");
        pw.close();
    }
}
