import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Integer> arrayA;
    private static ArrayList<Integer> arrayB;

    public static long func(int n, int k) {
        if (k < 0)
            return 0;
        if (n == k)
            return 1;
        if (n < k)
            return 0;
        double sum = 1;
        for (int i = 1; i <= k; ++i) {
            sum *= (n - k + i);
            sum /= i;
        }
        long a = (long) sum;
        return a;
    }

    public static long sum(ArrayList<Integer> arrayList, int k) {
        long sum = 0;
        if (k == 0)
            return 0;
        for (int i = 0; i < arrayList.size(); i++) {
            sum += func(arrayList.get(i), k - i);
        }
        return sum;
    }

    public static ArrayList getArr(String s) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < s.toCharArray().length; ++i) {
            if (s.toCharArray()[i] == '1')
                arrayList.add(s.toCharArray().length - i - 1);
        }
        return arrayList;
    }


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));
        FileWriter fw = new FileWriter("output.txt");

        long first = sc.nextLong();
        long last = sc.nextLong();

        String a = Long.toBinaryString(first);
        String b = Long.toBinaryString(last + 1);
        arrayA = getArr(a);
        arrayB = getArr(b);
        int k = sc.nextInt();
        long sum1 = sum(arrayA, k);
        long sum2 = sum(arrayB, k);
        fw.append(String.valueOf(sum2 - sum1));
        sc.close();
        fw.close();


    }
}