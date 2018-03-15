import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

public class ListSm {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter pw = new PrintWriter(new File("output.txt"));
        int N = in.nextInt();
        int M = in.nextInt();
        Vector<Vector<Integer>> list = new Vector<>();
        for(int i = 0; i<N; i++)
            list.add(new Vector<>());

        for(int i = 0; i < M ; i++)
        {
            int n = in.nextInt()-1;
            int m = in.nextInt()-1;
            list.get(n).add(m);
            list.get(m).add(n);
        }
        for(Vector<Integer> s:list)
        {
            pw.print(s.size()+" ");
            for(int val:s)
                pw.print((val+1)+" ");
            pw.println();
        }
        pw.close();
    }
}
