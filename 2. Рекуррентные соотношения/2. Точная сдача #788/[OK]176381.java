import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Change {
    private List<Integer> n;
    private List<Integer> m;
    private int S;
    private boolean[] A;

    public Change(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        int nSize = sc.nextInt();
        int mSize = sc.nextInt();
        n = new ArrayList<Integer>();
        m = new ArrayList<Integer>();
        S = sc.nextInt();
        fillList(n, nSize, sc);
        fillList(m, mSize, sc);
        sc.close();
    }

    private void fillList(List<Integer> list, int size, Scanner sc) {
        for (int i = 0; i < size; i++) {
            list.add(sc.nextInt());
        }
    }

    private void fillA(int size) {
        A = new boolean[size + 1];
        A[0] = true;
        for (int i = 0; i < m.size(); i++) {
            for (int j = size - m.get(i); j >= 0; j--)
                if (A[j]) A[j + m.get(i)] = true;
        }
    }

    private int sum(List<Integer> list) {
        int sum = 0;
        for (Integer item : list) {
            sum += item;
        }
        return sum;
    }

    public boolean check() {
        int c = sum(n) - S;
        if (c == 0) return true;
        if (c < 0) return false;
        m.addAll(n);
        Collections.sort(m);
        fillA(c);
        return A[c];
    }
}
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Change obj = new Change("input.txt");
        PrintStream ps = new PrintStream("output.txt");
        if (obj.check())
            ps.print("Yes");
        else
            ps.print("No");
    }
}