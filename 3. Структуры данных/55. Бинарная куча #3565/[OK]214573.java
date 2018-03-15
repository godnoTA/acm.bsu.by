import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        int n = in.nextInt();
        int[] list = new int[n];
        BinaryHeap l = new BinaryHeap();
        for (int i = 0; i < n; i++)
        {
            list[i] = in.nextInt();
            l.add(list[i]);
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
        if(check(list, l.list))
            bw.write("Yes");
        else
            bw.write("No");
        bw.close();
        in.close();
    }
    private static boolean check(int[]p, List<Integer>list)
    {
        for(int i = 0; i <list.size();i++)
        {
            if(p[i]!=list.get(i))
            {
                return false;
            }
        }
        return true;
    }

}
class BinaryHeap {
    List<Integer> list = new ArrayList<>();

    public int heapSize() {
        return this.list.size();

    }

    public void add(int value) {
        list.add(value);
        int i = heapSize() - 1;
        int parent = (i - 1) / 2;

        while (i > 0 && list.get(parent) > list.get(i)) {
            int temp = list.get(i);
            list.set(i, list.get(parent));
            list.set(parent,temp);

            i = parent;
            parent = (i - 1) / 2;
        }
    }

}