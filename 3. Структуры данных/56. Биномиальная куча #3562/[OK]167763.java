import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class task4 {
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
			PrintStream writer = new PrintStream("output.txt");
			
			long n = Long.parseLong(reader.readLine());
			long copy = n;
			int counter;
			
			counter = 0;
			while (copy != 0) {
				copy /= 2;
				counter++;
			}
			
			int[] mas = new int[counter];
			mas[counter - 1] = 1;
			n -= (1l << (counter - 1));
			
			while (n > 0) {
				counter = 0;
				copy = n;
				while (copy != 0) {
					copy /= 2;
					counter++;
				}
				mas[counter - 1] = 1;
				n -= (1l << (counter - 1));
			}
			
			
			for (int i = 0; i < mas.length; i++) {
				if (mas[i] == 1) {
					writer.println(i);
				}
			}
			
			
			reader.close();
			writer.close();
		}
		catch(Exception e) {
			
		}
	}
}
