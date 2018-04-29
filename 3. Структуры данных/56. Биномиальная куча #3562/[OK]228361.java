import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new File("input.txt"));
        long count = sc.nextLong();
        sc.close();

        ArrayList<Integer> answer = new ArrayList<>();
        BigInteger two = BigInteger.valueOf(2);

        while (count > 0) {
            long temp = two.pow(logs(count)).longValue();
            answer.add(logs(temp));
            count -= temp;
        }
        ListIterator<Integer> itr = answer.listIterator(answer.size());
        FileWriter fw = new FileWriter("output.txt");
        while (itr.hasPrevious())
            fw.append(String.valueOf(itr.previous())).append("\n");
        fw.close();
    }

    static int logs(long a) {
        long base = 1;
        int res = 0;
        while (base <= a) {
            base *= 2;
            res++;
        }
        return res - 1;
    }

}