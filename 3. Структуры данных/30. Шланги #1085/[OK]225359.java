import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    private void go() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt")).useLocale(Locale.US);
        PrintWriter pw = new PrintWriter(new File("output.txt"));
        int n = sc.nextInt();
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            sc.nextFloat();
            sc.nextFloat();
            list.add(sc.nextInt());
        }
        ListIterator<Integer> iter = list.listIterator();
        if (iter.hasNext()) {
            int el1 = iter.next();
            while (iter.hasNext()) {
                int el2 = iter.next();
                if (el1 != el2) {
                    el1 = el2;
                    continue;
                }
                iter.remove();
                iter.previous();
                iter.remove();
                if (iter.hasPrevious()) {
                    el1 = iter.previous();
                    iter.next();
                } else if (iter.hasNext())
                    el1 = iter.next();
            }
        }
        if (list.size() < 2)
            pw.print("Yes");
        else
            pw.print("No");
        pw.flush();
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Main().go();
    }
}