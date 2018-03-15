import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Heap {
    private long N;
    private List<Integer> list;

    public Heap(String path) throws IOException {
        Scanner sc = new Scanner(new File(path));
        N = sc.nextLong();
        sc.close();
        list = new ArrayList<Integer>();
    }

    public void decompose() {
        char[] arr = Long.toBinaryString(N).toCharArray();
        int k = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == '1')
                list.add(k);
            k++;
        }
    }

    public void printList(String path) throws IOException {
        PrintStream ps = new PrintStream(path);
        for (Integer item : list)
            ps.println(item);
        ps.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Heap h = new Heap("input.txt");
        h.decompose();
        h.printList("output.txt");
    }
}