import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = null;
        try {
            scan = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileWriter fOut = null;
        try {
            fOut = new FileWriter("output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        long n;
        n = scan.nextInt();
        long m;
        m = scan.nextInt();
        ArrayList<Integer>[] list;
        list = new ArrayList[Math.toIntExact(n)];
        for (long i = 0; i < n; i++) {
                    list[(int) i] = new ArrayList<>();
                }
        long i1 = 0;
        while (i1 < m) {
            long a = scan.nextInt();
            long b = scan.nextInt();
            list[(int) (a - 1)].add((int) b);
            list[Math.toIntExact(b - 1)].add(Math.toIntExact(a));
            i1++;
        }
        ArrayList<Integer> ranks = new ArrayList<>();
        int i = 0;
        do {
            if (!(i < n)) break;
            ranks.add(list[i].size());
            i++;
        } while (true);
        ranks.sort(Comparator.reverseOrder());
                for (Integer item : ranks) {
                    try {
                        fOut.write(item + " ");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}