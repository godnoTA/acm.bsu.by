import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Heap {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));
        File f = new File("output.txt");
        PrintWriter pw = new PrintWriter(f);
        long amount = sc.nextLong();
        Vector<Integer> degrees = new Vector<>();
        int degree = 0;
        while(amount!=0)
        {
            if(amount%2==1)
            {
                amount--;
                degrees.add(degree);
            }
            degree++;
            amount/=2;
        }
        for(int in: degrees)
        {
            pw.println(in);
        }
        pw.close();
    }
}
