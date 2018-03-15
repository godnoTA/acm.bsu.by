import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Tree {
     public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        int tmp;
        Set <Integer> mySet = new TreeSet<>();
        while(in.hasNextInt()) {
            tmp = in.nextInt();
            mySet.add(tmp);
        }
            long res=0;
            for (int num: mySet) {
                res += num;
            }
         FileWriter out = new FileWriter("output.txt");
         String str = Long.toString(res);
         out.write(str);
         out.close();
     }
}

