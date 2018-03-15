import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class program {

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

	public static void main(String[] args) throws IOException {
		InputStream in = new FileInputStream(new File("input.txt"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		int n = Integer.valueOf(reader.readLine());
		int[] p = new int[n + 1];
		String str;
		for (int i = 0; i < n; i++) {
			str = reader.readLine();
			int s = Integer.valueOf(str.substring(0, str.indexOf(" ")));
			p[i] = s;
			if (i == n - 1) {
				s = Integer.valueOf(str.substring(str.indexOf(" ") + 1, str.length()));
				p[n] = s;
			}
		}
		FileOutputStream fos = new FileOutputStream(new File("output.txt"));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
		writer.write(String.valueOf(multiplyOrder(p)));
		writer.newLine();
		reader.close();
		writer.flush();
		writer.close();
	}
}
