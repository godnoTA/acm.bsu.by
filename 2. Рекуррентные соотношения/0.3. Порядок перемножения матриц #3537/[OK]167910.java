import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class task3 {
	public static int n;
	public static int[] mas;
	public static int[][] matr;

	
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
			PrintStream writer = new PrintStream("output.txt");
			
			String line = null;
			n = Integer.parseInt(reader.readLine());
			mas = new int[n + 1];
			int pos1, pos2 = 0;
			
			
			for (int i = 0; i < n; i++) {
				line = reader.readLine();
				pos1 = 0;
				
				pos2 = line.indexOf(" ", pos1);
				mas[i] = Integer.parseInt(line.substring(pos1, pos2));
			}
			line += " ";
			pos2++;
			pos1 = pos2;
			pos2 = line.indexOf(" ", pos1);
			mas[n] = Integer.parseInt(line.substring(pos1, pos2));			
			
			matr = new int[mas.length][mas.length];
			for (int i = 0; i < mas.length - 1; i++) {
				for (int j = i + 1; j < mas.length; j++) {
					matr[i][j] = -1;
				}
			}
			
			
			writer.println(getMinCountOper(0, mas.length - 1));
			
			
			reader.close();
			writer.close();
		}
		catch(Exception e) {
			
		}
	}
	
	public static int getMinCountOper(int left, int right) {
		if (matr[left][right] == -1) {
			if (left == right - 1) {
				matr[left][right] = 0;
			} else {
				matr[left][right] = Integer.MAX_VALUE;
				for (int i = left + 1; i <= right - 1; i++) {
					matr[left][right] = Math.min(matr[left][right], mas[left] * mas[i] * mas[right]
												+ getMinCountOper(left, i) + getMinCountOper(i, right));
				}
			}
			
			return matr[left][right];
		} else {
			return matr[left][right];
		}
	}
}


