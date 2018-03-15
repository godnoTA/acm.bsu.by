import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
public class test {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        int i = 0, sum = 0;
        int N=0;
        int n=0;
        int c = 0;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("in.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("out.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            N = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (N > 200 || N < 1) {
            try {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        try {
            n = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (n < 0 || n > (N + 1) / 2) {
            try {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        String s;
        try {
            while ((s = reader.readLine()) != null) {
                if (!s.isEmpty()) {
                    c = Integer.parseInt(s);
                    list.add(c);
                    sum += c;
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (sum > N){
            try {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        int k = N - sum - n + 1;
        if (k == 0) {
            try {
                writer.write("1");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        int nn = n + 1;
        BigInteger res = new BigInteger(1 + "");
        for (i = 1; i <= k; i++) {
            res = res.multiply(new BigInteger(nn - 1 + i + ""));
            res = res.divide(new BigInteger(i + ""));
        }
        try {
            writer.write(res.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}