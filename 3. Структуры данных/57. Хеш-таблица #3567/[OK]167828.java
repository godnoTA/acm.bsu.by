import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashSet;


public class task6 {
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
			PrintStream writer = new PrintStream("output.txt");
			
			int m, c, n, pos1 = 0, pos2 = 0;;
			String line = reader.readLine() + " ";
			
			pos2 = line.indexOf(" ", pos1); 
			m = Integer.parseInt(line.substring(pos1, pos2));
			pos2++;
			pos1 = pos2;
			
			pos2 = line.indexOf(" ", pos1);
			c = Integer.parseInt(line.substring(pos1, pos2));
			pos2++;
			pos1 = pos2;
			
			pos2 = line.indexOf(" ", pos1);
			n = Integer.parseInt(line.substring(pos1, pos2));			
			
			
			int[] mas = new int[m];
			
			for (int i = 0; i < mas.length; i++) {
				mas[i] = -1;
			}
			
			HashSet<Integer> set = new HashSet<>();
			for (int i = 0; i < n; i++) {
				int x = Integer.parseInt(reader.readLine());
				if (set.contains(x)) {
					continue;
				} else {
					set.add(x);
				}
				
				int j = 0;
				while (true) {
					int position = ((x % m) + c * j) % m;
					
					if (mas[position] == -1) {
						mas[position] = x;
						break;
					} else {
						j++;
					}
				}
			}
			
			
			for (int i = 0; i < mas.length - 1; i++) {
				writer.print(mas[i] + " ");
			}
			writer.println(mas[mas.length - 1]);
				
			
			reader.close();
			writer.close();
		}
		catch(Exception e) {
			
		}
	}
}
