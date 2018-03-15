import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Matrix {
    private int n;
    private int m;
    private List<Integer>[] list;

    public Matrix(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        n = sc.nextInt();
        m = sc.nextInt();
        list = new ArrayList[n];
        for (int i = 0; i < n; i++)
            list[i] = new ArrayList<Integer>();
        int a;
        int b;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            list[a - 1].add(b);
            list[b - 1].add(a);
        }
    }

    public void show(String path) throws IOException {
        FileWriter fw = new FileWriter(path);
        for (List<Integer> item : list) {
            fw.write(item.size()+" ");
            for(Integer elem: item){
                fw.write(elem+" ");
            }
            fw.write("\n");
        }
        fw.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Matrix obj = new Matrix("input.txt");
        obj.show("output.txt");
    }
}