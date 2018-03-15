import java.io.*;
import java.util.Scanner;
 
class ladder{
    int n;
    long[] q;
    PrintWriter out;
    ladder() throws FileNotFoundException{
        Scanner scan = new Scanner(new File("input.txt"));
        while (scan.hasNextInt()){
            n = scan.nextInt();
        }
        q = new long[n+1];
        q[0] = 1;
        try {
            out = new PrintWriter(new FileWriter("output.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void solv(){
        for (int i = 1; i <= n; ++i) {
            for (int j = n; j >= i; --j) {
                q[j] += q[j - i];
            }
        }
        out.println(q[n]);
        out.close();
    }
}
public class Main {
 
   public static void main(String[] args) throws FileNotFoundException{
       ladder A = new ladder();
       A.solv();
    }
}