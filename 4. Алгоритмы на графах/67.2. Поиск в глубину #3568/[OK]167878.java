import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashSet;


public class task12 {
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
			PrintStream writer = new PrintStream("output.txt");
			
			String line;
			int n = Integer.parseInt(reader.readLine());
			int pos1 = 0, pos2 = 0;
			int[][] matr = new int[n][n]; 
			
			int[] metki = new int[n];
			int[] stek = new int[n + 1];
			HashSet<Integer> set = new HashSet<>();
			
			for (int i = 0; i < n; i++) {
				line = reader.readLine() + " ";
				
				pos1 = 0;
				pos2 = 0;
				for (int j = 0; j < n; j++) {
					pos2 = line.indexOf(" ", pos1);
					matr[i][j] = Integer.parseInt(line.substring(pos1, pos2));
					pos2++;
					pos1 = pos2;
				}
			}

			int cour = 0, counterMet = 1;
			int top;
			while (set.size() != n) {
				top = 0;
				
				for (int i = 0; i < n; i++) {
					if (!set.contains(i)) {
						set.add(i);
						metki[i] = counterMet;
						counterMet++;
						stek[top] = i;
						cour = i;
						break;
					}
				}
				
				
				boolean flag;
				while (top >= 0 && set.size() != n) {
					flag = false;
					for (int j = 0; j < n; j++) {
						if (matr[cour][j] == 1 && !set.contains(j)) {
							set.add(j);
							metki[j] = counterMet;
							counterMet++;
							top++;
							stek[top] = j;
							cour = j;
							flag = true;
							break;
						}
					}
					
					if (flag == false) {
						top--;
						if (top >= 0) {
							cour = stek[top];
						}
					}
				}
			}			
			
			
			for (int i = 0; i < metki.length - 1; i++) {
				writer.print(metki[i] + " ");
			}
			writer.println(metki[metki.length - 1]);
			
			
			reader.close();
			writer.close();
		}
		catch(Exception e) {
			
		}
	}
}


