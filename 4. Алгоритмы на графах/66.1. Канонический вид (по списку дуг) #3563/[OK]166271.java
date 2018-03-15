import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;


public class Main_66_1 {
	public static void main(String [] args){
		try
		{
			FileReader file = new FileReader("input.txt");
			BufferedReader br = new BufferedReader (file);
			String str;
			str = br.readLine();
			int del = Integer.parseInt(str);
			int P[]=new int[del];
			while((str = br.readLine()) != null)
			{
				StringTokenizer tok = new StringTokenizer(str, " ");
				String token = tok.nextToken();
				int pather = Integer.parseInt(token);
				token = tok.nextToken();
				int i = Integer.parseInt(token);
				P[i - 1] = pather;
			}
			file.close();
			br.close();
			FileWriter out = new FileWriter("output.txt");
			for(int i = 0;i < P.length;i ++)
				out.write(Integer.toString(P[i]) + " ");
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
