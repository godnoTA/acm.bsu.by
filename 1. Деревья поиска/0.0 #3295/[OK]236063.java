import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeSet;

public class BinarySearchTree {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new FileReader("input.txt"));
            TreeSet tree = new TreeSet();
            long res = 0;

            while(in.hasNextLine()) {
                int t;
                t = Integer.parseInt(in.nextLine());
                if (!tree.contains(t)) {
                    tree.add(t);
                    res += t;
                }
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
            bw.write(Long.toString(res));
            bw.close();
        }catch(Exception e){

        }
    }
}