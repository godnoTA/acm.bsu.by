import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;


public class Main implements Runnable {
    public static final int MAX_ARR_VAL = 64;
    static long[][] arr = new long[MAX_ARR_VAL][MAX_ARR_VAL];

    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }
    private static class MyComparator implements Comparator<Long> {
        @Override
        public int compare(Long o1, Long o2) {
            return o2.compareTo(o1);
        }
    }

    public void run() {
        int n = MAX_ARR_VAL - 1;
        for(int i=0;i<=n;++i)
        {
            arr[i][0]=1;
            arr[i][i]=1;
            for(int j=1;j<i;++j)
            {
                arr[i][j]=arr[i-1][j-1]+arr[i-1][j];
            }
        }
        Scanner sc = null;
        try {
            sc = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileWriter writer = null;
        try {
            writer = new FileWriter("output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        long a = sc.nextLong();
        long b = sc.nextLong() + 1;
        long k = sc.nextLong();
        long count = 0;

        ArrayList<Long> lA = new ArrayList<>();
        ArrayList<Long> lB = new ArrayList<>();
        long firstSum = 0;
        long secondSum = 0;

        while (a > 0) {
            long q = a & 1;
            a = a >> 1;
            if (q == 1) {
                lA.add(count);
            }
            count++;
        }

        count = 0;

        while (b > 0) {
            long q = b & 1;
            b = b >> 1;
            if (q == 1) {
                lB.add(count);
            }
            count++;
        }

        lA.sort(new MyComparator());
        lB.sort(new MyComparator());

        for (int i = 0; i < lB.size(); i++) {
            firstSum += sochetan(k - i, lB.get(i));
        }

        for (int i = 0; i < lA.size(); i++) {
            secondSum += sochetan(k - i, lA.get(i));
        }

        try {
            writer.write(Long.toString(firstSum - secondSum));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long sochetan(long t1, long t2) {
        if(t1 < 0) return 0;
        if(t2 < t1) return 0;

        return arr[(int)t2][(int)t1];
    }
}
