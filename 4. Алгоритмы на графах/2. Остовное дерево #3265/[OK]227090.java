import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SpanningTree {
    private static int numberOfVertices;
    private static int[][] result;
    private static ArrayList<Integer>[] adjList;
    public static void main(String[] args) {
        input();
        boolean[] visit = new boolean[numberOfVertices];
        ArrayList<Integer> queue = new ArrayList<>();
        int counter = 0;
        queue.add(0);
        visit[0] = true;
        while (!queue.isEmpty()){
            for( Integer num: adjList[queue.get(0)]){
                if(!visit[num-1]){
                    queue.add(num-1);
                    result[counter][0] = queue.get(0)+1;
                    result[counter][1] = num;
                    counter++;
                    visit[num-1] = true;
                }
            }
            queue.remove(0);
        }
        for( boolean check: visit){
            if( !check){
                numberOfVertices = -1;
                break;
            }
        }
        output(result);
    }
    private static void input(){
        try (FileReader fr = new FileReader("input.txt")) {
            Scanner sc = new Scanner(fr);
            numberOfVertices = Integer.parseInt(sc.next());
            result = new int[numberOfVertices-1][2];
            adjList = new ArrayList[numberOfVertices];
            for( int i = 0; i < numberOfVertices; i++){
                adjList[i] = new ArrayList<>();
                for( int j = 0; j < numberOfVertices; j++){
                    if( Integer.parseInt(sc.next()) == 1){
                        adjList[i].add(j+1);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void output( int[][] result){
        try (FileWriter fw = new FileWriter("output.txt")) {
            if( numberOfVertices != -1){
                fw.write(String.valueOf(numberOfVertices-1));
                for (int[] aResult : result) {
                    fw.write("\n" + aResult[0] + " " + aResult[1]);
                }
            }
            else{
                fw.write(String.valueOf(numberOfVertices));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
