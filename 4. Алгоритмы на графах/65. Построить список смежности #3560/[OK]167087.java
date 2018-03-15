import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main_65 {

	public static void main(String [] args){
		try
		{
			/*5 4
			1 2
			4 3
			2 4
			2 3*/
			FileReader file = new FileReader("input.txt");
			BufferedReader br = new BufferedReader (file);
			String str;
			str = br.readLine();
			StringTokenizer tok = new StringTokenizer(str, " ");
			int N = Integer.parseInt(tok.nextToken());//вершины
			int M = Integer.parseInt(tok.nextToken());//ребра
			ArrayList <Integer>[] P = new ArrayList [N];
			for(int i = 0; i < N;i ++)
				P[i] = new ArrayList();
			int k = 1;
			while(k <= M)
			{
				str = br.readLine();
				tok = new StringTokenizer(str, " ");
				int i = Integer.parseInt(tok.nextToken());
				int j = Integer.parseInt(tok.nextToken());
				P[i - 1].add(j);
				P[j - 1].add(i);
				k ++;
			}
			file.close();
			br.close();
			FileWriter out = new FileWriter("output.txt");
			for(int i = 0;i < N;i ++)
			{
				out.write(Integer.toString(P[i].size()));
				out.write(" ");
				for(int j = 0;j < P[i].size();j ++)
					{
						out.write(Integer.toString(P[i].get(j)));
						if(j != P[i].size())
							out.write(" ");
					}
				//if(i != P[i].size())
				out.write("\n");
			}
				
			out.close();
		}
		 catch(IOException ex)
		{
			 System.out.println(ex.getMessage());
		}
		catch(IllegalArgumentException ex)
		{
			 System.out.println(ex.getMessage());
		}
		catch(Exception ex)
		{
			 System.out.println(ex.getMessage());
		}
	}
}
