import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Main m = new Main();
        m.readFile();
        m.count();
        m.writeFile();
    }
        private Set<Integer> list;

    public Main() {
            this.list = new HashSet<>();
        }

    public void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        while (sc.hasNext()) {
            Integer now = Integer.parseInt(sc.next());
            list.add(now);
        }
        sc.close();
    }

    public long count() {
        long s = 0;
        for (int item : list) {
            s = s + item;
        }
        return s;
    }

    public void writeFile() throws FileNotFoundException {
        PrintStream ps = new PrintStream("output.txt");
        ps.println(count());
    }
}

