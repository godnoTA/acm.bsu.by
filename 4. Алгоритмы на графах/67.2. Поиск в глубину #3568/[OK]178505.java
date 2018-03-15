import java.io.*;
import java.util.*;


public class Test {

    static int N;
    int arr[][];
    static int mark = 1;
    static int label[];
    static boolean visited[];

    public void Depth_First_Search(int Node){
        visited[Node] = true;
        label[Node] = mark;
        mark++;
        for (int i = 0 ; i < N ; i++) {
            if (!visited[i] && arr[Node][i] == 1) {
                Depth_First_Search(i);
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

            visited = new boolean[N];
            label = new int[N];
            for(int i=0; i<N; i++) {
                visited[i] = false;
            }
            for(int i=0; i<N; i++) {
                if (!visited[i]) {
                    Depth_First_Search(i);
                }
            }

            PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
            if(N==1)
            {
                out.print(1);
                out.close();
                return;
            }
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