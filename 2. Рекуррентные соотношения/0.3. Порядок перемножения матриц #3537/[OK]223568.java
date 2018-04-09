import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class matrix{
	
static int scet(int[] mas) {
		int n = mas.length - 1;
		int[][] a = new int[n + 1][n + 1];
		
		for (int i = 1; i <= n; i++) {
			a[i][i] = 0;
		}
		
		for (int l = 2; l <= n; l++) {
			for (int i = 1; i <= n - l + 1; i++) {
				int j = i + l - 1;
				a[i][j] = Integer.MAX_VALUE;
				for (int k = i; k <= j - 1; k++) {
					a[i][j] = Math.min(a[i][j],
                                                   a[i][k] + a[k + 1][j] + mas[i - 1] * mas[k] * mas[j]);
				}
			}
		}
		return a[1][n]; 
	}

	public static void main(String[] args) throws IOException {
		try {
			Scanner fileScanner = new Scanner(new FileReader("input.txt"));
			int udal = 0;
			String line;
			if (fileScanner.hasNextLine())
				udal = Integer.parseInt(fileScanner.nextLine());
			int mas[]=new int[udal+1];
			int chislo = 0;
			int k=0;
			for(int j=0;j<udal-1;j++) {
				{line = fileScanner.nextLine();
				Scanner s=new Scanner(line);
				chislo=s.nextInt();
				mas[k]=chislo;
				k++;}
			}
			
				line = fileScanner.nextLine();
				Scanner s=new Scanner(line);
				for(int e=0;e<2;e++)
				{
				chislo=s.nextInt();
				mas[k]=chislo;
				k++;
				}
			 String text = Integer.toString(matrix.scet(mas));
			FileWriter writer = new FileWriter("output.txt");
			writer.write(text);
			writer.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	}
