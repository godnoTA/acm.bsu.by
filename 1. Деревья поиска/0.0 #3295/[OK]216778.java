import java.io.*;
import java.util.*;

public class main
 {

	public static void main (String args[]) {
		
		List<Integer> list = new ArrayList<Integer>();
		long sum = 0;
		int line ;//слишком просто
		try{
			Scanner in = new Scanner(new File("input.txt"));
			while (in.hasNextInt())
			{
				line = in.nextInt();
				if (Collections.frequency(list, line)==0)
				{
					list.add(line);
					sum += line;
				}
			}
			in.close();
		
			PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
			out.println(sum);
			out.close();
		}
		
		catch(IOException exc){
            System.out.println(exc.getMessage());
        } 
		
	}
}