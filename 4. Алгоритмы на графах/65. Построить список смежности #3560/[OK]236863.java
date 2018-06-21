

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
		int n,m;
		try
		{
			FastScanner sc = new FastScanner("input.txt");
			n = sc.nextInt();
			m = sc.nextInt();
			ArrayList<Integer>[] mas = new ArrayList[n];
			for (int i = 0; i < n; i++) {
	            mas[i] = new ArrayList<>();
	        }
			int i,j;
			for(int str=0;str<m;str++)
			{
				i = sc.nextInt();
				j = sc.nextInt();
				mas[i-1].add(j);
				mas[j-1].add(i);
			}
			PrintWriter q = new PrintWriter(new File("output.txt"));
			for(int k=0;k<n;k++)
			{
				q.print(mas[k].size());
				if(mas[k].size()!=0)
				{
					q.print(" ");
					for(int c =0;c<mas[k].size()-1;c++)
					{
						q.print(mas[k].get(c) + " ");
					}
					q.println(mas[k].get(mas[k].size()-1) + " ");
				}
				else
				{
				q.println();
				}
			}
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