import java.io.*;
import java.util.*;

public class BinaryST implements Runnable {
    
    public void run() {
    	//BinaryST tree = new BinaryST();
 		File fis = new File("input.txt");
 		File fos = new File("output.txt");
 		HashSet<Long> set = new HashSet<>();
 		Scanner in;
 		FileWriter out;
 		String str;
 		long tmp, sum = 0;
 		try {
 			in = new Scanner(fis);
 			while (in.hasNextLine()) {
 				str = in.nextLine();
 				tmp = Long.parseLong(str);
 				if (!set.contains(tmp)) {
 					set.add(tmp);
 					sum += tmp;
 				}
 			}
 			in.close();
 			out = new FileWriter(fos);
 			out.write(Long.toString(sum));
 			out.close();
 			
 		} catch (Exception ex) {
 			ex.printStackTrace();
 		}		
	}
   
    public static void main(String[] args) {
    	new Thread(null, new BinaryST(), "", 64 * 1024 * 1024).start();	
    	System.out.println("Запись в файл прошла успешно!");
    }
    
}