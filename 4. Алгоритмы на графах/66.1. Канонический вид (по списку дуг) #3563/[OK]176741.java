import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("input.txt"));
        PrintWriter pr = new PrintWriter(new File("output.txt"));
        int top = sc.nextInt();
        List[] massive = new List[top];
        for (int i = 0; i < top; i++) {
            massive[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < top; i++) {
            while (sc.hasNextInt()) {
                int j = sc.nextInt();
                int k = sc.nextInt();
                massive[k - 1].add(j);
            }
            if(massive[i].isEmpty()){
                massive[i].add(0);
            }
        }

        for (int i = 0; i < top; i++) {
            for (Object item : massive[i]) {
                pr.print(item + " ");
            }
        }
        pr.close();
    }
}