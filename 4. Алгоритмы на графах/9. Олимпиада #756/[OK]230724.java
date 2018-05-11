import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class Main {
    static int n=1001;
    static int [][]graph;
    static boolean visited[];

    static void DepthFirstSearch(int k) {
        visited[k] = true;
        for (int i = 0; i < n; i++) {
            if (graph[k][i] == 1)
            {
                if (!visited[i])
                {
                    DepthFirstSearch(i);
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("input.in"));
        visited = new boolean[n];
        graph = new int[n][n];
        n = sc.nextInt();
        for (int j=0;j<n;j++){
            for (int i=0;i<n;i++){
                graph[i][j] = sc.nextInt();
            }
        }
        for(int i=0;i<n;i++) {
            visited[i] = false;
        }
        DepthFirstSearch(0);
        FileWriter fw = new FileWriter("output.out");
        //System.out.println(Arrays.toString(visited));
        boolean b = true;
        for(int i=0;i<n;i++){
            if(b){
                b = visited[i];
            }
        }
       if(b){
           fw.write("YES");
       } else {
         fw.write("NO");
       }
       fw.close();
    }
}