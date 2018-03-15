import java.io.*;
import java.util.*;





public class Main {
	

	     
	  public static int multiplyOrder(int[] p) {
			int n = p.length - 1;
			int[][] dp = new int[n + 1][n + 1];
			
			for (int i = 1; i <= n; i++) {
				dp[i][i] = 0;
			}
			
			for (int l = 2; l <= n; l++) {
				for (int i = 1; i <= n - l + 1; i++) {
					int j = i + l - 1;
					dp[i][j] = Integer.MAX_VALUE;
					for (int k = i; k <= j - 1; k++) {
						dp[i][j] = Math.min(dp[i][j],
	                                                   dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j]);
					}
				}
			}
			return dp[1][n]; 
	       }
	
	public static void main(String[]args){
		int size_of_vector = 0;
		String str_test;
		String[]temp_mas;
		int [] required_vector = null;
		int counter = 1;
		try {
			Scanner sc = new Scanner(new File("input.txt"));
			size_of_vector= sc.nextInt();
			required_vector = new int[size_of_vector+1];
			str_test=sc.nextLine();
			str_test=sc.nextLine();
			temp_mas = str_test.split(" ");
			for(int i = 0; i<2;i++){
				required_vector[i]= Integer.parseInt(temp_mas[i]);
			}
			while (sc.hasNextLine()) {
				if(!((str_test=sc.nextLine()).isEmpty()))
					temp_mas = str_test.split(" ");
				counter++;
				required_vector[counter]=Integer.parseInt(temp_mas[1]);
			}
			sc.close();
		} catch (FileNotFoundException e) {};
		
		
		FileWriter file;
		try {
			file = new FileWriter("output.txt");
			file.write(""+Main.multiplyOrder(required_vector));
			file.close();
		} catch (IOException e) {}

		
	}
}
