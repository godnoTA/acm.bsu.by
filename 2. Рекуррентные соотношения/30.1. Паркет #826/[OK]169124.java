import java.util.*;
import java.io.*;

public class Baget {

    public static void main(String args[]) throws Exception {
        Scanner s = new Scanner(new File("in.txt"));
        long n = s.nextLong();
        s.close();
        long nn = n / 2;
        long otvet = 0;
        long tt = 1;
        for (long i = 0; i < nn - 1; i++) {
            otvet += tt;
            otvet %= 1000000007;
            tt *= 6;
            tt %= 1000000007;
        }
        otvet *= -1;
        tt *= 5;
        tt %= 1000000007;
        otvet += tt + 1000000007;
        otvet %= 1000000007;
        if (n % 2 == 1) {
            otvet = 0;
        }
        PrintWriter out = new PrintWriter(new File("out.txt"));
        out.print(otvet);
        out.close();
    }
}
