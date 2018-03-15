import java.io.*;
import java.util.*;

public class Var_1{
	
	public static void main(String[]args){
		long sum = 0;
		try {
			Scanner sc = new Scanner(new File("input.txt"));
			sum= sc.nextLong();
			sc.close();
		} catch (FileNotFoundException e) {};
		
		String result = Long.toBinaryString(sum);
		
		try {
			FileWriter file = new FileWriter("output.txt");
			
				for (int i = result.length()-1; i>=0;i--){
					if(Integer.parseInt(result.substring(i, i+1))==1){
						file.write("" +(result.length()-i-1)+"\r\n");
					}
				}
			
			file.close();
		} catch (IOException e) {}
	}

}
