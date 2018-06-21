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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//File f = new File("input.txt");
		int n;
		int[] graph;
		try
		{
			FastScanner sc = new FastScanner("input.txt");
			n = sc.nextInt();
			graph = new int[n];
			int i,j;
			for(int str=0;str<n-1;str++)
			{
				i = sc.nextInt();
				j = sc.nextInt();
				graph[j-1]=i;
			}
			PrintWriter q = new PrintWriter(new File("output.txt"));
			for(int k=0;k<n-1;k++)
				q.print(graph[k] + " ");
			q.print(graph[n-1]+"\n");
			q.flush();
			}
		catch (IOException e) {
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