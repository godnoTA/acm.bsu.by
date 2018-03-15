import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class STree  {
    static int N;
    static boolean[] visit;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter pw = new PrintWriter(new File("output.txt"));
        N = in.nextInt();
        visit = new boolean[N];
        for(boolean b:visit)
        {
            b = false;
        }
        int[][] matrix = new int[N][N];
        Queue<Integer> vertices = new PriorityQueue<>();

        for(int i = 0; i < N ; i++)
        {
            for(int j = 0; j < N; j++)
            {
                matrix[i][j] = in.nextInt();
            }
        }
        ArrayList<Pair<Integer, Integer>> answer = new ArrayList<>();
        vertices.add(0);
        visit[0] = true;
        while ( !vertices.isEmpty())
        {
            int ind=vertices.peek();
            vertices.poll();
            for ( int i=0; i<N; i++ )
            {
                if ( matrix[ind][i]==1 )
                {
                    if(!visit[i]){
                        vertices.add(i);
                        answer.add(new Pair<>(ind+1, i+1));
                        visit[i] = true;
                    }
                }
            }
        }

        boolean flag = true;
        for(boolean bol:visit)
        {
            if(!bol)
            {
                flag = false;
            }
        }
        if(answer.size()!=0&&flag)
        {
            pw.println(answer.size());
            int ind = 0;
            for(Pair<Integer, Integer> temp:answer)
            {
                pw.println(answer.get(ind).getKey()+" "+answer.get(ind).getValue()+" ");
                ind++;
            }
        }
        else if(N==1) pw.println(0);
        else
            pw.println(-1);

        pw.close();
    }
}
