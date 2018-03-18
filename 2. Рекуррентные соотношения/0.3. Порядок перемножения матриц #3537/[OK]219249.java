import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class MatrixComposition {
	
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
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j]);
				}
			}
		}
		return dp[1][n]; 
	}

	public static void main(String[] args) {
//		int[] test = { 10, 100, 5, 50 };
//		System.out.println(multiplyOrder(test));
		File fis = new File("input.txt");
		File fos = new File("output.txt");
		Scanner in;
		FileWriter out;
		try {
			in = new Scanner(fis);
			out = new FileWriter(fos);
			int count = Integer.parseInt(in.nextLine());
			int[] data = new int[count + 1];
			int j = 1, k = 0;			
			while(in.hasNext()) {
				if(j%2 == 0 || j == 1) {
					data[k] =  Integer.parseInt(in.next());
					k++;
				} else {
					in.next();
				}
				j++;
			}
			long result = multiplyOrder(data);
//			System.out.println(result);
			out.write(Long.toString(result));
			out.close();
			in.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
