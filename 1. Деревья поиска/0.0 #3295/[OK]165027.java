import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class JavaApplication1 {
    public static void main(String[] args) throws FileNotFoundException {
      Scanner sc = new Scanner(new File("input.txt"));
      Set<Integer> set = new HashSet<Integer>();
      while (sc.hasNext()){
          set.add(sc.nextInt());
      }
      sc.close();
      long sum = 0;
      for(Integer item: set){
          sum+=item;
      }
      PrintStream ps = new PrintStream("output.txt");
      ps.print(sum);
      ps.close();
    }
}