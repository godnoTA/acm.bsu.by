import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        int[] heap = new int[123456];
        int n = in.nextInt();
        for(int i = 1; i < n + 1; ++i) {
            heap[i] = in.nextInt();
        }
        Boolean isHeap = true;
        for(int i = 1; i < n / 2 + 1; ++i) {
            if(heap[i] > heap[2 * i]) {
                isHeap = false;
                break;
            }
            if(2 * i + 1 > n) {
                break;
            }
            else {
                if(heap[i] > heap[2 * i + 1]) {
                    isHeap = false;
                    break;
                }
            }
        }
        if(isHeap) {
            out.println("Yes");
        }
        else {
            out.println("No");
        }
        out.flush();
    }
}