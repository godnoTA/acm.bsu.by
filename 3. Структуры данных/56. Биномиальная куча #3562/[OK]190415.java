import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(new File("input.txt")); 
		
		long num = sc.nextLong();
		
		long absolutNUM = num;
		
		FileWriter writer = new FileWriter("output.txt");
		
		if(num > 0){	
			int bits = 0;
			
		    while (num > 0) {
		        num >>= 1;
		        bits++;
		    }
		    
			num = absolutNUM;
			String a =Long.toBinaryString(num);
			
			int i = 0;
			for(int tr = a.length() - 1; tr >= 0; tr-- ){
				if(a.charAt(tr) == '1'){
					writer.write(i + "\n");
				}
				i++;
			}
		}else{
			writer.write(-1 + "\n");
		}
		
		writer.close();
	}

}