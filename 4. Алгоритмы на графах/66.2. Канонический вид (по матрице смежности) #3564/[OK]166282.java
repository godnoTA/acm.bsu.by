import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;


public class Main_66_2 {
	public static void main(String [] args){
		try
		{
			FileReader file = new FileReader("input.txt");
			BufferedReader br = new BufferedReader (file);
			String str;
			str = br.readLine();
			int del = Integer.parseInt(str);
			int P[]=new int[del];
			int i = 1;
			while((str = br.readLine()) != null)
			{
				int j = 1;
				StringTokenizer tok = new StringTokenizer(str, " ");
				while(j <= del )
				{
					String token = tok.nextToken();
					if(token.compareTo("1") == 0)
						P[j - 1] = i;
					j++;
				}
				i++;
			}
			file.close();
			br.close();
			FileWriter out = new FileWriter("output.txt");
			for(int k = 0;k < P.length;k ++)
				out.write(Integer.toString(P[k]) + " ");
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
