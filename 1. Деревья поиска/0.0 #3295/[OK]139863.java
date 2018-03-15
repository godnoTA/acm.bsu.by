import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Scanner;


public class JavaApplication1 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        Scanner sc = new Scanner(new File("input.txt"));
        HashSet<BigInteger> a = new HashSet<BigInteger>();
        
        while(sc.hasNext())
            a.add(sc.nextBigInteger());
        
        
        BigInteger sum;
        sum = BigInteger.valueOf(0);
        for(BigInteger i : a)
            sum = sum.add(i);
        
        FileWriter fw = new FileWriter("output.txt");
        fw.write(sum + "");
        fw.close();
        
    }
    
}
