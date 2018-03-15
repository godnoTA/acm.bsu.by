import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class kanonVid2 {
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
			PrintStream writer = new PrintStream("output.txt");
			 
			String line;
			int count;
			
			count = Integer.parseInt(reader.readLine());
			int[] a = new int[count];
			int[][] mas = new int[count][count];
			
			for (int i = 0; i < count; i++) {
				line = reader.readLine();
				for (int j = 0; j < count; j++) {
					mas[i][j] = Integer.parseInt(line.substring(2 * j, 2 * j + 1));
				}
			}			
			
			boolean flag;
			for (int j = 0; j < count; j++) {
				flag = false;
				for (int i = 0; i < count; i++) {
					if (mas[i][j] == 1) {
						a[j] = i + 1;
						flag = true;
						break;
					}
				}
				if (flag == false) {
					a[j] = 0;
				}
			}
			
			for (int i = 0; i < count - 1; i++) {
				writer.print(a[i] + " ");
			}
			writer.println(a[count - 1]);
			
			reader.close();
			writer.close();
		}
		catch (Exception e) {
			
		}
	}
}
