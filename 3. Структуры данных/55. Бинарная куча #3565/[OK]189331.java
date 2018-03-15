import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(new File("input.txt")); 
		
		int num = sc.nextInt();

		boolean check = true;
		
		int[] test = new int[num + 1]; 
		
		for(int i = 1; i <= num; i++){
			test[i] = sc.nextInt(); 
		}
		
	    for (int i = 1; i <= num / 2; i++)
	    {	        
	        if (test[i] > test[2 * i] || ((2 * i) + 1 <= num && test[i] > test[(2 * i) + 1])){
	        	check = false;
	        }
	    }
	    
	    FileWriter writer = new FileWriter("output.txt");
	 
	    if (check){
	    	//System.out.println("Yes");
	    	writer.write("Yes");
	    }else{
	    	//System.out.println("No");
	    	writer.write("No");
	    }
		
	    
		writer.close();
	}
}