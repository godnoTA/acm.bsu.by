import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class test 
{
	public static void main(String[] args)throws FileNotFoundException
	{
		Set<Long> numberSet = new HashSet<>();
		long sum=0;
		try (Scanner in=new Scanner(new File("input.txt")))
		{
			while(in.hasNextInt())
			{
				numberSet.add(in.nextLong());
			}
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		if(numberSet.isEmpty())
		{
			System.out.println("Empty file!");
			return;
		}
		else
		{
			for(long i : numberSet)
				sum+=i;
		}
		System.out.println(sum);
		try
		{
			PrintWriter out = new PrintWriter(new File("output.txt"));
			out.write(Long.toString(sum));
			out.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
