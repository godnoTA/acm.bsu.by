import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void addPoint(Long a, Set<Long> set ){
        set.add(a);
    }

    public static long setSum(Set<Long> set){
        long sum =0;
        for (Long item:set) {
            sum+=item;
        }
        return sum;
    }
    public static void main (String [] args) throws IOException{
        Scanner sc = new Scanner(new File("input.txt"));
        Set<Long> set = new HashSet<>();
        while(sc.hasNextLine()){
            String p = sc.nextLine();
            addPoint(Long.parseLong(p),set);
        }
        FileWriter bw = new FileWriter("output.txt" );
        bw.write(String.valueOf(setSum(set)));
        bw.close();
    }
}
