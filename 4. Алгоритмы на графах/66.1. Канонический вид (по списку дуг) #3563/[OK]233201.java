import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class Main {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new File("input.txt"));

        int n = sc.nextInt();


        Map<Integer, Integer> map = new TreeMap<>();

        for (int i=1; i<=n; i++)
            map.put(i, 0);

        while (sc.hasNext()) {
            int parent = sc.nextInt();
            int child = sc.nextInt();
            map.put(child, parent);
        }


        FileWriter fw = new FileWriter("output.txt");

        for (Integer i : map.values()) {
            fw.append(String.valueOf(i)).append(" ");
        }

        sc.close();
        fw.close();
    }

}