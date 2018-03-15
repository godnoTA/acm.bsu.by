import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

public class BFS {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter pw = new PrintWriter(new File("output.txt"));
        int N = in.nextInt();
        int[][] matrix = new int[N][N];
        Vector<Integer> counter = new Vector<>();
        boolean[] visited = new boolean[N];

        for(int i = 0; i < N; i++)
        {
            for (int j = 0; j<N; j++)
            {
                matrix[i][j] = in.nextInt();
            }
        }
        Queue<Integer> vertices = new LinkedList<>();
        for(int index = 0; index<N; index++)
        {
            if(!visited[index])
            {
                counter.add(index);
                vertices.add(index);
                visited[index] = true;
                while (!vertices.isEmpty())
                {
                    int ind = vertices.poll();
                    for (int i=0; i<N; i++)
                    {
                        if(!visited[i]&&matrix[ind][i]==1){
                            vertices.add(i);
                            counter.add(i);
                            visited[i] = true;
                        }
                    }
                }
            }
        }
        for(int i = 0; i<N; i++)
            pw.print((counter.indexOf(i)+1)+" ");
        pw.close();
    }
}
