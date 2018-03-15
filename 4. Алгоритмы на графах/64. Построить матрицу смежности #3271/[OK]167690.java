import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class task8 {
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
			PrintStream writer = new PrintStream("output.txt");
			
			String line;
			int n, m, pos, x1, x2;
			
			line = reader.readLine();
			pos = line.indexOf(" ");
			n = Integer.parseInt(line.substring(0, pos));
			m = Integer.parseInt(line.substring(pos + 1, line.length()));
			
			int[][] matr = new int[n][n];
			
			
			for (int i = 0; i < m; i++) {
				line = reader.readLine();
				pos = line.indexOf(" ");
				x1 = Integer.parseInt(line.substring(0, pos));
				x2 = Integer.parseInt(line.substring(pos + 1, line.length()));
				
				matr[x1 - 1][x2 - 1] = 1;
				matr[x2 - 1][x1 - 1] = 1;
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n - 1; j++) {
					writer.print(matr[i][j] + " ");
				}
				writer.println(matr[i][n - 1]);
			}
			
			
			reader.close();
			writer.close();
		}
		catch(Exception e) {
			
		}
	}
}
