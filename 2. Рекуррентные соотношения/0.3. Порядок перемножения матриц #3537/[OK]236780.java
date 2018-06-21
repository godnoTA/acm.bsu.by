import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main implements Runnable{
	
	public static void main(String[] args)
	{
		new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }
	
	public static int multiplyOrder(int[] mas) {
		int[][] matr = new int[mas.length][mas.length];
		
		for (int l = 2; l <= mas.length-1; l++) {
			for (int i = 1; i <= mas.length - l; i++) {
				int j = i + l - 1;
				matr[i][j] = Integer.MAX_VALUE;
				for (int k = i; k <= j - 1; k++) {
					matr[i][j] = Math.min(matr[i][j], matr[i][k] + matr[k + 1][j] + mas[i - 1] * mas[k] * mas[j]);
				}
			}
		}
		return matr[1][mas.length-1]; 
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//File f = new File("input.txt");
		int n;
		int[] size;
		try {
			FastScanner sc = new FastScanner("input.txt");
			n = sc.nextInt();
			size = new int[n+1];
			for(int i=0;i<n-1;i++)
			{
				size[i]=sc.nextInt();
				sc.nextInt();
			}
			size[n-1]=sc.nextInt();
			size[n]=sc.nextInt();
			PrintWriter q = new PrintWriter(new File("output.txt"));
			q.println(multiplyOrder(size));
			q.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class FastScanner {
        BufferedReader reader;
        StringTokenizer tokenizer;
 
        public FastScanner(String fileName) throws IOException {
            reader = new BufferedReader(new FileReader(fileName));
        }
 
        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                String line = reader.readLine();
                if (line == null) {
                    throw new EOFException();
                }
                tokenizer = new StringTokenizer(line);
            }
            return tokenizer.nextToken();
        }
 
        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
	
}