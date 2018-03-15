import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class task5 {
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
			PrintStream writer = new PrintStream("output.txt");
			
			String line;
			int n = Integer.parseInt(reader.readLine());
			long[] mas = new long[n + 1];
			int pos1 = 0, pos2 = 0;
			
			line = reader.readLine() + " ";
			for (int i = 1; i <= n; i++) {
				pos2 = line.indexOf(" ", pos1);
				mas[i] = Long.parseLong(line.substring(pos1, pos2));
				pos1 = pos2 + 1;
			}
			
			for (int i = 1; i < (n / 2) + 1; i++) {
				if ((mas[i] > mas[i * 2]) || ((i * 2 + 1 <= n) && (mas[i] > mas[i * 2 + 1]))) {
					writer.println("No");
					reader.close();
					writer.close();
					return;
				}
			}
			
			writer.println("Yes");
			
		
			reader.close();
			writer.close();
		}
		catch(Exception e) {
			
		}
	}
}
