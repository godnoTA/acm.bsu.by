import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;


public class task9 {
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
			PrintStream writer = new PrintStream("output.txt");
			
			String line;
			int n, m, pos, x1, x2;
			ArrayList<ArrayList<Integer>> list = new ArrayList<>();
			
			line = reader.readLine();
			pos = line.indexOf(" ");
			n = Integer.parseInt(line.substring(0, pos));
			m = Integer.parseInt(line.substring(pos + 1, line.length()));
			
			for (int i = 0; i < n; i++) {
				list.add(new ArrayList<>());
			}
			
			for (int i = 0; i < m; i++) {
				line = reader.readLine();
				pos = line.indexOf(" ");
				x1 = Integer.parseInt(line.substring(0, pos));
				x2 = Integer.parseInt(line.substring(pos + 1, line.length()));
				
				list.get(x1 - 1).add(x2);
				list.get(x2 - 1).add(x1);
			}
			
			for (int i = 0; i < n; i++) {
				writer.print(list.get(i).size() + " ");
				
				if (list.get(i).size() == 0) {
					writer.println();
				} else {
					for (int j = 0; j < list.get(i).size() - 1; j++) {
						writer.print(list.get(i).get(j) + " ");
					}
					writer.println(list.get(i).get(list.get(i).size() - 1));
				}
			}
			
			
			reader.close();
			writer.close();
		}
		catch(Exception e) {
			
		}
	}
}
