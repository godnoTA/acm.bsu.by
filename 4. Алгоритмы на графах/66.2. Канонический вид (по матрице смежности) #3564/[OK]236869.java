import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
    	File f = new File("input.txt");
        Scanner sc = new Scanner(f);
    
        int n = sc.nextInt();
        int[] p = new int[n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (sc.nextInt() == 1) {
                        p[j] = i+1;
                    }
                }
            }
        PrintWriter q = new PrintWriter(new File("output.txt"));
        for (int j = 0; j < n-1; j++) {
            q.print(p[j] + " ");
        }
        q.print(p[n-1]+"\n");
        sc.close();
        q.flush();
    }
}