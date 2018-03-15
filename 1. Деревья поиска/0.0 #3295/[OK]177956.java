import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));

        Set<Long> mySet = new HashSet<>();
        while (in.hasNext()) {
            mySet.add(in.nextLong());
        }
        long sum = 0;
        for(long i : mySet) {
            sum += i;
        }
        out.println(sum);
        out.flush();
    }
}