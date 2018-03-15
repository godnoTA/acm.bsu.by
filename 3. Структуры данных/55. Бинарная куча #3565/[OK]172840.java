import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Heap {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));
        int amount = sc.nextInt();
        int N = 0;
        boolean flag = true;
        long[] values = new long[amount+1];
        while (sc.hasNext()) {
            N++;
            values[N] =  sc.nextInt();
            int i = N;
            while(i>1 && values[i]<values[i/2])
            {
                long temp = values[i];
                values[i] = values[(i)/2];
                values[(i)/2] = temp;
                i = i/2;
                flag = false;
            }
        }

        File f = new File("output.txt");
        PrintWriter pw = new PrintWriter(f);
        String answer = (flag) ? "Yes" : "No";
        pw.println(answer);
        pw.close();
    }
}
