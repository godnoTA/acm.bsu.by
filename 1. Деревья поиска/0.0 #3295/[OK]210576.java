import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));
        Set<Integer> set = new HashSet<Integer>();
        long sum = 0;

        while(sc.hasNext()){
            set.add(Integer.parseInt(sc.next()));
        }

        for(Integer i:set){sum+=i;}

        PrintWriter bw =new PrintWriter( new BufferedWriter(new FileWriter("output.txt")));
        bw.print(sum);
        bw.close();
    }
}