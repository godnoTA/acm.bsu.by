
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;


public class Sum {

	public static void main(String[] args) {

		try(FileInputStream fin = new FileInputStream("input.txt");
                FileWriter out = new FileWriter("output.txt",false))
        {
			Scanner scanner = new Scanner(new File("input.txt"));
			//int [] mas = new int [100];
			//int i = 0;
			TreeSet<Integer> mas = new TreeSet<Integer>();
			while(scanner.hasNextInt()){
			   mas.add(scanner.nextInt());
			}
            
            long sum = 0;
            for(Integer j: mas){
            	sum += j;
            }
            
            out.write((long)sum+" ");
        
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        }   
    } 
	         
	         
	     
	
	
}
