import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class Main implements Runnable {
	static int[][][] data;
	static int[] byteLand;
	static int n;
	static int m;

	public static void main(String[] args) {
		new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
	}

	@Override
	public void run() {
		File in = new File("input.txt");
		workWithFile(in);
		data = new int[n][n][m + 1];
		int answer = dp(0, n - 1, m);
		writeToFile("output.txt", Integer.toString(answer));
	}

	public static int dp(int i,int j, int k) {
		int res = 0;
		if(data[i][j][k] != 0)
			return data[i][j][k];
		if(k == 0){
			for(int a = i; a < j; a++){
				for(int b = a+1; b <= j; b++){
					res += byteLand[a]*byteLand[b];
				}
			}
		} else {
			res = Integer.MAX_VALUE;
			for(int a = j; a >= k; a--){
				int tmp = dp(a, j, 0) + dp(i, a - 1, k-1);
				if(tmp < res)
					res = tmp;
			}
		}
		data[i][j][k] = res;
		return res;
	}

	public static void workWithFile(File file) {
		try {
			Scanner in = new Scanner(file.getAbsoluteFile());
			try {
				n = in.nextInt();
				m = in.nextInt();
				byteLand = new int[n];
				int i = 0;
				while (in.hasNextInt()) {
					byteLand[i] = in.nextInt();
					i++;
				}
			} finally {
				in.close();
			}
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	public static void writeToFile(String fileName, String text) {
		File file = new File(fileName);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			PrintWriter out = new PrintWriter(file.getAbsoluteFile());

			try {
				out.print(text);
			} finally {
				out.close();
			}
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

}