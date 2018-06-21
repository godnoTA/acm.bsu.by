import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Graphy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean isGraph = true, isPsevdoGraph = true, isMultiGraph = true;
		try (Scanner sc = new Scanner(new File("input.txt"))) {
			StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int[][] matrix = new int[n][n];
			
			int x, y;
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(sc.nextLine(), " ");
				x = Integer.parseInt(st.nextToken()) - 1;
				y = Integer.parseInt(st.nextToken()) - 1;
				if (x == y) {
					isGraph = false;
					isMultiGraph = false;
					break;
				} else if (matrix[x][y] == 1 || matrix[y][x] == 1)
					isGraph = false;
				
				matrix[x][y] = 1;
				matrix[y][x] = 1;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try (FileWriter out = new FileWriter(new File("output.txt"))) {
			out.write(isGraph ? "Yes\r\n" : "No\r\n");
			out.write(isMultiGraph ? "Yes\r\n" : "No\r\n");
			out.write(isPsevdoGraph ? "Yes\r\n" : "No\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
