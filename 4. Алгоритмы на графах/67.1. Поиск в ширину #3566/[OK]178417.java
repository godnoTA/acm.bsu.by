import java.io.*;
import java.util.*;


public class Test {

    static int N;
    static int arr[][];
    static int mark = 1;
    static int label[];
    static boolean visited[];
    static Queue<Integer> queue;

    private static void BFS(int v) {
        if (visited[v]) {
            return;
        }
        queue.add(v);
        visited[v] = true;
        label[v]=mark;
        mark++;
        while (!queue.isEmpty()) {
            v = queue.poll();
            for (int i = 0; i < N; i++) {
                if (!visited[i] && arr[v][i]!=0){
                    queue.add(i);
                    visited[i] = true;
                    label[i]=mark;
                    mark++;
                }
            }
        }
    }

    public void run(){
        try{
            Scanner in = new Scanner(new File("input.txt"));
            N = in.nextInt();
            arr = new int[N][N];

            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    arr[i][j] = in.nextInt();
                }
            }

            queue = new LinkedList<Integer>();
            visited = new boolean[N];
            label = new int[N];

            for(int i=0; i<N; i++){
                BFS(i);
            }

            PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
            for(int i=0; i<label.length; i++){
                out.print(label[i] + " ");
            }
            out.flush();
        }
        catch(Exception e){}
    }

    public static void main(String[] args) throws IOException {
        new Test().run();
    }
}