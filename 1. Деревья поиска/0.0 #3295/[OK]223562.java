import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class tree {

	public static void main(String[] args) throws IOException {

		HashMap<Long, Integer> mas = new HashMap<Long, Integer>(); 
		try {
			Scanner fileScanner = new Scanner(new FileReader("input.txt"));
			long i=0;
			int n=0;
			while(fileScanner.hasNextLong())
			{
				i=fileScanner.nextLong();
				if(mas.put(i, n)!=null)
					n++;
			}
			long summa=0;
			for (Long key : mas.keySet()) {
			  summa+=key;
			}
			 FileWriter writer = new FileWriter("output.txt");
			  String text = Long.toString(summa);
			  writer.write(text);	
			  writer.flush();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}

}
