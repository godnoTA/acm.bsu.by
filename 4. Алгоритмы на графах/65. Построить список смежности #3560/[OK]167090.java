import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class matrs
{
	public static void main(String[] args)throws FileNotFoundException  
	{
		try (Scanner in=new Scanner(new File("input.txt")))
		{
			int n=in.nextInt();
			int m=in.nextInt();
			Map myMap=new HashMap<Integer,ArrayList<Integer>>();
			for(int i=0;i<m;i++)
			{
				Integer first=in.nextInt();
				Integer second=in.nextInt();
				if (!myMap.containsKey(first))
				{
                    myMap.put(first, new ArrayList<Integer>());
                }
                if (!myMap.containsKey(second))
                {
                    myMap.put(second, new ArrayList<Integer>());
                }
                ((ArrayList<Integer>)myMap.get(first)).add(second);
                ((ArrayList<Integer>)myMap.get(second)).add(first);
			}
			try
			{
				PrintWriter out = new PrintWriter(new File("output.txt"));
				for(int i=1;i<=n;i++)
				{
					ArrayList<Integer> result=(ArrayList<Integer>)myMap.get(i);
					if(result==null)
						out.print(0);
					else
					{
						out.print(result.size()+" ");
						for(int j=0;j<result.size();j++)
							out.print(result.get(j)+" ");
					}
					out.println();
				}
					
				out.close();
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		} 
	}
}