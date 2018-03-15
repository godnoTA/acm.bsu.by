import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * Created by Никита on 03.06.2016.
 */
public class Algorithms9 {
    public static void main(String[] args) {
        Algorithms9 algorithms9 = new Algorithms9();
        algorithms9.Reader();
    }

    public void Reader() {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            StringTokenizer stringTokenizer;
            int n, m, k;

            stringTokenizer = new StringTokenizer(br.readLine(), " ");
            n = Integer.valueOf(stringTokenizer.nextToken());
            k = Integer.valueOf(stringTokenizer.nextToken());

            this.work(k, n);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public void Writer(String count) {
        File file = new File("output.txt");
        file.delete();
        try (FileWriter fw = new FileWriter("output.txt")) {
            fw.write(count);

        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public void work(int k, int n) {
        long stairs1 = 0;
        long stairs2 = 1;
        long vrem;
        for (int i = 0; i < k; i++) {
            vrem = stairs2;
            stairs2 = (stairs1 + stairs2)%1000000009;
            stairs1 = vrem;
        }
        BigInteger bigDecimal=null;
        if(k>=1) {
            bigDecimal = new BigInteger(String.valueOf(stairs2));
        }else{
            bigDecimal = new BigInteger(String.valueOf(stairs1));
        }
        bigDecimal = bigDecimal.multiply(bigDecimal);
        BigInteger bigDecimal1 = new BigInteger("1");
        bigDecimal = bigDecimal.add(bigDecimal1);
        BigInteger s = new BigInteger("1000000009");
        bigDecimal=bigDecimal.mod(s);
        bigDecimal1 = bigDecimal.pow(n-1);
        bigDecimal = new BigInteger("1000000009");
        bigDecimal1 = bigDecimal1.mod(bigDecimal);
        this.Writer(bigDecimal1.toString());
    }
}
