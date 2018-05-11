import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    static ArrayList<Pair> ar = new ArrayList<>();
    static Integer works[] = new Integer[300000];

    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
        int n = scanner.nextInt();
        int wrkr[] = new int[n]; //здесь индексом массива будет сам работник
        int amount[] = new int[n];
        int i = 0;
        while (scanner.hasNext()){
            ar.add(new Pair(scanner.nextInt(), i));
            i++;
        }
        int m = i;

//=========================================================================================

        Collections.sort(ar, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.work - o2.work;
            }
        });
        Collections.reverse(ar);
        ArrayList<Integer> minind = new ArrayList<>();
        int minam = 10000000; //мин колво работ
        for (Pair p:ar){
            for (i = 0; i < n; i++) {
                if (amount[i] < minam) {
                    minam = amount[i];
                }
            }
            for (i = 0; i < n; i++){
                if (amount[i] == minam) {
                    minind.add(i);
                }
            }
            int minzagr = 1000000000;
            int minindz = 0;
            for (i = 0; i < minind.size(); i++){
                if (wrkr[minind.get(i)] < minzagr){
                    minzagr = wrkr[minind.get(i)];
                    minindz = i;
                }
            }
            wrkr[minindz] += p.work;
            works[p.index] = minindz + 1;
            amount[minindz] ++;
        }

//=========================================================================================

        FileWriter fout = null;
        try {
            fout = new FileWriter("output.txt");
            FileOutputStream fos = new FileOutputStream(new File("output.txt"));
            PrintStream ps = new PrintStream(fos);
            int maxwrk = 0;
            for (i = 0; i < n; i++) {
                if (wrkr[i] > maxwrk)
                    maxwrk = wrkr[i];
            }
            ps.print(maxwrk + "\n");
            for (i = 0; i < m; i++) {
                ps.print(works[i] + " ");
            }
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class Pair{
    int work;
    int index;
    Pair(int w, int i) {
        work = w;
        index = i;
    }
}