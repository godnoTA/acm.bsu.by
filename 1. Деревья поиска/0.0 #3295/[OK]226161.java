import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) throws IOException {
        HashSet<Integer> set = new HashSet<>();
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        while (in.hasNext()) {
            int x = in.nextInt();
            set.add(x);
        }
        long ans = 0;
        for(int i : set) {
            ans += i;
        }
        out.print(ans);
        out.flush();
    }
}
