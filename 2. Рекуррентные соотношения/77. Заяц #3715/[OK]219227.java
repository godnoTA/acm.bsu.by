
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

public class Main {



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        String line = br.readLine();
        int x = Integer.parseInt(line);


        PrintWriter pw = new PrintWriter("output.txt");

        if (x == 1)
            pw.print(1);
        else if (x == 2)
            pw.print(2);
        else {
            long prev = 2;
            long prePrev = 1;

            for (int i = 3; i <= x; i++){
                long tmp = prev % 1000000007 + prePrev % 1000000007;
                prePrev = prev % 1000000007;
                prev = tmp % 1000000007;
            }
            pw.print(prev % 1000000007);

        }

        pw.close();
    }


}
