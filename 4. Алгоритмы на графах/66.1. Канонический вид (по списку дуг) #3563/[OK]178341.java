import java.io.*;
import java.util.Scanner;

class Tree {
    private int n;
    private int[] array;

    public Tree(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        n = sc.nextInt();
        array = new int[n];
        int a;
        int b;
        for (int i = 0; i < n-1; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            array[b - 1] = a;
        }
        sc.close();
    }

    public void show(String path) throws IOException {
        FileWriter fw = new FileWriter(path);
        for (Integer item : array)
            fw.write(item + " ");
        fw.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Tree obj = new Tree("input.txt");
        obj.show("output.txt");
    }
}