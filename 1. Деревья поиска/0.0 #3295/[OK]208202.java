import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import static java.lang.System.out;

public class Algorithms {
    public static void main(String[] args) {
        TreeSet<BigInteger> Tree = new TreeSet<BigInteger>();
        try {
            BigInteger sum = new BigInteger("0");
            FileReader fr = new FileReader("input.txt");
            FileWriter fw = new FileWriter("output.txt");
            Scanner scan = new Scanner(fr);
            while(scan.hasNext()){
                Tree.add(scan.nextBigInteger());
            }
            for(BigInteger key: Tree){
                sum = sum.add(key);
            }
            fw.write("" + sum);
            fw.close();
        } catch (IOException ex) {
            out.println("NANI??");
        }

    }
}