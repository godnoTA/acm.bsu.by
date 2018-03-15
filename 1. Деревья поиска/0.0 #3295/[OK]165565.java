import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws IOException {
		String s1 = "input.txt";
		String s2 = "output.txt";
		BufferedReader buf1 = new BufferedReader(new FileReader(s1));
		BufferedWriter buf2 = new BufferedWriter(new FileWriter(s2));
		long sum = 0;
		Set <Integer> set = new HashSet();
		String str;
		while((str = buf1.readLine()) != null){
			set.add(Integer.parseInt(str));
		}
		Iterator it = set.iterator();
		for(Integer i: set){
			sum += i;
		}
		buf2.write(String.valueOf(sum));
		buf1.close();
		buf2.close();
	}
}
