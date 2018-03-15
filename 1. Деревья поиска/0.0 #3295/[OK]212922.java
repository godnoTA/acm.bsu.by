import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class Main {
	public static void main(String [] args) {
		try 
		{
			File input = new File ("input.txt");
			Scanner sc = new Scanner(input);
			boolean flag = false;
			
			ArrayList<Integer> list = new ArrayList<Integer>();
			int temp = 0;
			
			while (sc.hasNextInt())
			{
				temp = sc.nextInt();
				flag = false;
				
				for(int i : list)
				{      
		            if (i == temp)
		            		flag = true;
		        }
				
				if (flag == false)
				{
					list.add(temp);
				}
			}

			sc.close();
			long sum = 0;
			
			for(int i : list){      
	            System.out.println(i);
	            sum = sum + i;
	        }
			
			System.out.println(sum);
			
			FileWriter out = new FileWriter("output.txt");
			out.write(Long.toString(sum));
			out.flush();
			out.close();
			System.exit(0);
		}
			
		catch(Exception e)
		{
			System.err.println (e);
			System.exit(-1);
		}
	}
}