import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("input.txt"));
        PrintWriter pr = new PrintWriter(new File("output.txt"));
        long tops = sc.nextLong();
        long temp;
        int none = -1;
        List<Long> list = new ArrayList<Long>();
        if (tops <= 0) {
            pr.print(none);
        }
        while (tops != 0) {
            temp = tops % 2;
            list.add(temp);
            tops = tops / 2;
        }
        Long[] array = new Long[list.size()];
        array = list.toArray(array);
        int size = array.length;
        for (int i = 0; i < size; i++) {
            if (array[i] == 1) {
                pr.println(i);
            }
        }
        pr.close();
    }
}