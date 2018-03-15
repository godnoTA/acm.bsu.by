import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class kanonVid {
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
			PrintStream writer = new PrintStream("output.txt");
			 
			String line;
			int count, x1, x2, pos;
			
			count = Integer.parseInt(reader.readLine());
			int[] mas = new int[count];
			
			for (int i = 0; i < count - 1; i++) {
				line = reader.readLine();
				pos = line.indexOf(" ");
				x1 = Integer.parseInt(line.substring(0, pos));
				x2 = Integer.parseInt(line.substring(pos + 1, line.length()));
				mas[x2 - 1] = x1;
			}			
			
			for (int i = 0; i < count - 1; i++)
				writer.print(mas[i] + " ");
			writer.println(mas[count - 1]);
			
			reader.close();
			writer.close();
		}
		catch (Exception e) {
			
		}
	}
}




