import java.io.*;
import java.util.*;

public class Task0 {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        TreeSet<Integer> tree = new TreeSet<Integer>();
        while(in.hasNext()){
            tree.add(in.nextInt());
        }
        
        Iterator<Integer> iterator = tree.iterator();
        long i = 0;
        while(iterator.hasNext()){
            i += iterator.next();
        }
        out.print(i);
        out.close();
   }
    
}