import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class ALG0 {
	public static void main(String [] args) {
		try{
			File input = new File ("input.txt");
			Scanner sc = new Scanner(input);
			Set<Long> k = new HashSet<>();
			long sum = 0;
			while (sc.hasNextLong()){
				k.add(sc.nextLong());
			}
			sc.close();
			Iterator<Long> it = k.iterator();
			while (it.hasNext()){
				sum+=it.next();
			}
			FileWriter out = new FileWriter("output.txt");
			out.write(Long.toString(sum));
			out.flush();
			out.close();
			}
			
		catch(Exception e){
			System.err.println (e);
		}
	}
}

