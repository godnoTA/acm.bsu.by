import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashSet;

public class task1 {

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
			PrintStream writer = new PrintStream("output.txt");
			 
			String line;
			long sum = 0;
			HashSet<Integer> set = new HashSet<>();
			
			while ((line = reader.readLine()) != null) {
				set.add(Integer.parseInt(line));
			}			
			
			for (Integer i : set) {
				sum += i;
			}
			
			writer.println(sum);
			
			reader.close();
			writer.close();
		}
		catch (Exception e) {
			
		}
	}
}