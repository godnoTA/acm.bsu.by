import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WidthSearch {
    private static int[] result;
    private static ArrayList<Integer>[] adjList;
    public static void main(String[] args) {
        input();
        int met = 1;
        boolean[] visit = new boolean[result.length];
        ArrayList<Integer> queue = new ArrayList<>();
        for( int i = 0; i < result.length; i++){
            if(!visit[i]){
                queue.add(i);
                visit[i] = true;
                result[i] = met++;
                while (!queue.isEmpty()){
                    for( Integer num: adjList[queue.get(0)]){
                        if(!visit[num-1]){
                            queue.add(num-1);
                            result[num-1] = met++;
                            visit[num-1] = true;
                        }
                    }
                    queue.remove(0);
                }
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
                        adjList[i].add(j+1);
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
}
