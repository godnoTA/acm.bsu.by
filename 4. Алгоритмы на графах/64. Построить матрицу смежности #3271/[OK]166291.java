import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;


public class Main_64 {
	public static void main(String [] args){
		try
		{
			FileReader file = new FileReader("input.txt");
			BufferedReader br = new BufferedReader (file);
			String str;
			str = br.readLine();
			StringTokenizer tok = new StringTokenizer(str, " ");
			int N = Integer.parseInt(tok.nextToken());
			int M = Integer.parseInt(tok.nextToken());
			int P[][] = new int [N][N];
			int k = 1;
			while(k <= M)
			{
				str = br.readLine();
				tok = new StringTokenizer(str, " ");
				int i = Integer.parseInt(tok.nextToken());
				int j = Integer.parseInt(tok.nextToken());
				P[i - 1][j - 1] = 1;
				P[j - 1][i - 1] = 1;
				k ++;
			}
			file.close();
			br.close();
			FileWriter out = new FileWriter("output.txt");
			for(int i = 0;i < N;i ++)
			{
				for(int j = 0;j < N;j ++)
					{
						out.write(Integer.toString(P[i][j]));
						if(j != N -1)
							out.write(" ");
					}
				if(i != N -1)
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
