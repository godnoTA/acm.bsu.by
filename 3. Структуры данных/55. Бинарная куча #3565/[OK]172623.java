import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

class Heap {
    int size;
    int[] arr;

    Heap(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        size = sc.nextInt();
        arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }
    }

    public boolean check() {

        for (int i = 0, j = 1; j < size; i++, j += 2) {
            if (arr[j] < arr[i] || (j + 1 < size && arr[j + 1] < arr[i])) return false;
        }
        return true;
    }
}

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Heap h = new Heap("input.txt");
        PrintStream ps = new PrintStream("output.txt");
        if (h.check())
            ps.println("Yes");
        else
            ps.println("No");
    }
}