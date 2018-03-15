import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Roads {
    static int N;
    static int M;
    static boolean[] visit;


    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter pw = new PrintWriter(new File("output.txt"));
        N = in.nextInt();
        M = in.nextInt();
        visit = new boolean[N];
        Vector<Vector<Integer>> list = new Vector<>();
        for(int i = 0; i<N; i++)
            list.add(new Vector<>());
        Queue<Integer> vertices = new LinkedList<>();

        for(int i = 0; i < M ; i++)
        {
            int n = in.nextInt()-1;
            int m = in.nextInt()-1;
            list.get(n).add(m);
            list.get(m).add(n);
        }
        int numberOfComponents = 0;
        for(int index = 0; index<N; index++)
        {
            if(!visit[index])
            {
                vertices.add(index);
                visit[index] = true;
                while ( !vertices.isEmpty())
                {
                    int ind = vertices.poll();
                    for (int i=0; i<list.get(ind).size(); i++)
                    {
                        if(!visit[list.get(ind).get(i)]){
                            vertices.add(list.get(ind).get(i));
                            visit[list.get(ind).get(i)] = true;
                        }
                    }
                }
                numberOfComponents++;
            }
        }
        pw.print(numberOfComponents-1);
        pw.close();
    }
}
