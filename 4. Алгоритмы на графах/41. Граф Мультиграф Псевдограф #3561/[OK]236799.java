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
	
	static boolean Multi = false;
	static boolean Psevdo = false;
	
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//File f = new File("input.txt");
		int n, m;
		int[][] matrGraph;
		try {
			FastScanner sc = new FastScanner("input.txt");
			n = sc.nextInt();
			m = sc.nextInt();
			matrGraph = new int[n][n];
			int i,j;
			for(int k=0;k<m;k++)
			{
				i = sc.nextInt();
				j = sc.nextInt();
				if(matrGraph[i-1][j-1] != 1 && matrGraph[j-1][i-1]!=1)
				{
					matrGraph[i-1][j-1] = 1;
					matrGraph[j-1][i-1] = 1;
				}
				else
				{
					matrGraph[i-1][j-1] = 2;
					matrGraph[j-1][i-1] = 2;
				}
			}
			for(int k=0;k<n;k++)
			{
				for(int d=0; d<n && d!=k; d++)
					if(matrGraph[k][d]>1)
						Multi = true;
				if(matrGraph[k][k] != 0)
				{
					Psevdo = true;
				}
			}
			PrintWriter q = new PrintWriter(new File("output.txt"));
			if(Multi==false && Psevdo==false)
			{
				q.println("Yes");
				q.println("Yes");
				q.println("Yes");
			}
			else
			{
				q.println("No");
				if(Psevdo==false)
				{
					q.println("Yes");
				    q.println("Yes");
				}
				else
				{
					q.println("No");
				    q.println("Yes");
				}
			}
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