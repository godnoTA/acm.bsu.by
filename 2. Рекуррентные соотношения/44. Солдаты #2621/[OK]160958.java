import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by maxmxmx on 20.11.2016.
 */
public class Main {
    private static int x;

    public static void main(String[] args) throws IOException {
        readFile();
        print();
    }

    public static void readFile() throws IOException{
        Scanner sc = new Scanner(new File("input.txt"));
        x = Integer.parseInt(sc.next());
    }

    public static int work(int x) {
        if (x <= 3) return 1;
        if (x % 2 != 0) return work(x / 2) + work(x / 2 + 1);
        else return 2 * work(x / 2);
    }

    public static void print() throws IOException{
        File f = new File("output.txt");
        PrintStream ps = new PrintStream(f);
        ps.print(work(x));
    }
}
