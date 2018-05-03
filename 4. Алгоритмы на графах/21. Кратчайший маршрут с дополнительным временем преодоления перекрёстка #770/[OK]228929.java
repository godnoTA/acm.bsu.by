import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

class Pair implements Comparable<Pair>{
    public int top;
    public int length;
    public Pair(int top, int length){
        this.top = top;
        this.length = length;
    }
    @Override
    public int compareTo(Pair o) {
        return this.length - o.length;
    }
}

public class AAAAAA {
    private static int[] result;
    private static int [][] widthMatrix;
    private static ArrayList<Integer>[] adjList;
    private static boolean[] visit;
    private static int firstTop;
    private static int lastTop;
    private static int q;
    public static void main(String[] args) {
        input();
        PriorityQueue<Pair> queue = new PriorityQueue<>();
        queue.add(new Pair(firstTop, result[firstTop]));
        int currentIndex;
        while (!queue.isEmpty()){
            currentIndex = queue.peek().top;
            if(!visit[currentIndex]){
                for( Integer num: adjList[currentIndex]){
                    if(!visit[num]){
                        if(num!= lastTop){
                            result[num] = Math.min(result[num], result[currentIndex] + widthMatrix[currentIndex][num]
                            + adjList[num].size()*q);
                        }
                        else{
                            result[num] = Math.min(result[num], result[currentIndex] + widthMatrix[currentIndex][num]);
                        }
                        queue.add(new Pair(num, result[num]));
                    }
                }
                visit[currentIndex] = true;
            }
            queue.poll();
        }
        output(result[lastTop]);
    }
    private static void input(){
        try (FileReader fr = new FileReader("input.txt")) {
            Scanner sc = new Scanner(fr);
            result = new int [Integer.parseInt(sc.next())];
            adjList = new ArrayList[result.length];
            visit = new boolean[result.length];
            widthMatrix = new int[result.length][result.length];
            for( int i = 0; i < result.length; i++){
                adjList[i] = new ArrayList<>();
                result[i] = Integer.MAX_VALUE;
            }
            int numberOfEdges = Integer.parseInt(sc.next());
            int top1;
            int top2;
            int weight;
            for( int i = 0; i < numberOfEdges; i++){
                top1 = Integer.parseInt(sc.next())-1;
                top2 = Integer.parseInt(sc.next())-1;
                weight = Integer.parseInt(sc.next());
                adjList[top1].add(top2);
                adjList[top2].add(top1);
                widthMatrix[top1][top2] = widthMatrix[top2][top1] = weight;
            }
            firstTop = Integer.parseInt(sc.next())-1;
            lastTop = Integer.parseInt(sc.next())-1;
            q = Integer.parseInt(sc.next());
            result[firstTop] = 0;
        } catch (IOException e) { }
    }
    private static void output( int result){
        try (FileWriter fw = new FileWriter("output.txt")) {
            if( result!= Integer.MAX_VALUE){
                fw.write("Yes\n" + String.valueOf(result+adjList[firstTop].size()*q));
            }
            else{
                fw.write("No");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
