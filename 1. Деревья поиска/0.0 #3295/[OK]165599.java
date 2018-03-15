import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;


public class Tree {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in=new Scanner(new File("input.txt"));
		TreeSet<Integer> my_tree = new TreeSet<>();
		long sum=0;
		
		while(in.hasNextLine())
				my_tree.add(Integer.parseInt(in.nextLine()));
				
		 for(Integer a : my_tree)
			 sum+=a;
		
		 try(FileWriter writer = new FileWriter("output.txt", false))
	        {
	            writer.write(Long.toString(sum));
	            writer.flush();
	        }
	        catch(IOException ex){
	             
	            System.out.println(ex.getMessage());
	        } 
		 
	}

}
