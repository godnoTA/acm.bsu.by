import java.io.*;
import java.util.Scanner;

class Matrix {
    private int n;
    private int[] array;

    public Matrix(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        n = sc.nextInt();
        array = new int[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (sc.nextInt() == 1) array[j] = i + 1;
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
        Matrix obj = new Matrix("input.txt");
        obj.show("output.txt");
    }
}