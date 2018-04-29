import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DepthSearch {
    private static int[] result;
    private static ArrayList<Integer>[] adjList;
    private static int met;
    private static boolean[] visit;
    public static void main(String[] args) {
        input();
        met = 0;
        visit = new boolean[result.length];
        for( int i = 0; i < result.length; i++){
            if(!visit[i]){
                dfs(i);
            }
        }
        output(result);
    }
    private static void input(){
        try (FileReader fr = new FileReader("input.txt")) {
            Scanner sc = new Scanner(fr);
            result = new int [Integer.parseInt(sc.next())];
            adjList = new ArrayList[result.length];
            for( int i = 0; i < result.length; i++){
                adjList[i] = new ArrayList<>();
                for( int j = 0; j < result.length; j++){
                    if( Integer.parseInt(sc.next()) == 1){
                        adjList[i].add(j);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void output( int[] result){
        try (FileWriter fw = new FileWriter("output.txt")) {
            for (int aResult : result) {
                fw.write(aResult + " ");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void dfs( Integer top){
        visit[top] = true;
        result[top] = ++met;
        for( Integer adjTop: adjList[top]){
            if(!visit[adjTop]){
                dfs(adjTop);
            }
        }
    }
}
