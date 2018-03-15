import java.io.*;
import java.util.*;





public class Main {
	
	static public void main(String[]args){
		Set <Integer> set = new HashSet<Integer>();
		try {
			Scanner sc = new Scanner(new File("input.txt"));
			while (sc.hasNextInt()) {
			     set.add(sc.nextInt());
			}
			sc.close();
		} catch (FileNotFoundException e) {};
		long sum = 0;
		Iterator<Integer> iterator = set.iterator();
	    while (iterator.hasNext()) {
	    	sum+=iterator.next();
	    }
		try {
			FileWriter writeFile = new FileWriter(new File("output.txt"));
			writeFile.write(""+sum);
			writeFile.close();
		} catch (IOException e) {}
		
	}

}
