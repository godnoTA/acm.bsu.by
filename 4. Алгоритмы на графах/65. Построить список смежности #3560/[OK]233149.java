import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new FileReader("input.txt"));
        FileWriter fw = new FileWriter("output.txt");


        String[] line;
        line = bf.readLine().split(" ");
        int ver = Integer.parseInt(line[0]);
        int reb = Integer.parseInt(line[1]);

        ArrayList[] arr = new ArrayList[ver];
        for (int i = 0; i < ver; i++) {
            arr[i] = new ArrayList<Integer>();
        }

        int a, b;
        for (int i = 0; i < reb; i++) {
            line = bf.readLine().split(" ");
            a = Integer.parseInt(line[0]);
            b = Integer.parseInt(line[1]);
            arr[a - 1].add(b);
            arr[b - 1].add(a);
        }

        for (int i =0; i<ver; i++) {
            fw.append(String.valueOf(arr[i].size() + " "));
            for (Object tmp: arr[i]){
                fw.append(String.valueOf(tmp) + " ");
            }
            fw.append("\n");
        }


        fw.close();
        bf.close();
    }
}
