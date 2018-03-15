import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class Item implements Comparable<Item>{
    int wait;
    int time;
    int transporting;

    public Item(int a,int b,int c){
        wait = a;
        time = b;
        transporting = c;
    }

    public int compareTo(Item i){
        if(wait-i.wait!=0)return wait-i.wait;
        if(time-i.time!=0)return time-i.time;
        return transporting-i.transporting;
    }
}

public class Solution {
    static PriorityQueue<Integer>pq = new PriorityQueue<>();

    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(new File("input.txt"));
            PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));

            int N = sc.nextInt(),M = sc.nextInt();
            Item[]items = new Item[N];
            for(int i=0;i<N;i++)items[i] = new Item(sc.nextInt(),sc.nextInt(),sc.nextInt());
            for(int i=0;i<M;i++)pq.add(0);

            Arrays.sort(items);
            int answer = 0;
            for(int i=0;i<N;i++){
                int cur = pq.poll();
                if(cur<items[i].wait)cur = items[i].wait;
                cur+=items[i].time;
                pq.add(cur);
                answer = Math.max(answer,cur+items[i].transporting);
            }

            pw.println(answer);

            pw.close();
        }catch(Exception e){
            System.out.println(e);
        }

    }
}
