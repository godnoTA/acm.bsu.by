import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(new File("input.txt")); 
		
		int num = sc.nextInt();
		
		int x;
		
		int[] test = new int[num+1]; 
		
		for(int i = 0; i < num; i++){
			test[i] = sc.nextInt();
			if(i != (num - 1))
				x = sc.nextInt(); 
		}
		
		test[num] = sc.nextInt();
		
		int[] test1 = { 10, 100, 5, 50 };
		//System.out.println(Main.multiplyOrder(test));
		
		FileWriter writer = new FileWriter("output.txt");
		int z = Main.multiplyOrder(test);;
		String b = String.valueOf(z);
		writer.write(b);
		writer.close();
	}
	
	
	
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
    
    
    
}