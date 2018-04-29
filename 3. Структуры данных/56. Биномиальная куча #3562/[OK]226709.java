import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Binom_Kucha {

	public static void main(String[] args) throws FileNotFoundException {
		File fin = new File("input.txt");
		Scanner sc = new Scanner(fin);
		long n = sc.nextLong();
		char t;
		System.out.println(n);
		StringBuffer dv = new StringBuffer();
		 while(n !=0 ) {  
	            t = Long.toString(n%2).charAt(0);  
	            System.out.print(t);  
	            dv.append(t);
	            n = n/2;  
	        } 
		 System.out.println("\n"+ dv);
		 StringBuffer answ = new StringBuffer();
		 int i = 0;
		 while(dv.length() != 0){
			 if(dv.substring(0, 1).toCharArray()[0] == '1'){
				 answ.append(i);
				 answ.append("\r\n");
			 }
			 dv = dv.deleteCharAt(0);
			 i++;
		 }
		 System.out.println(answ.toString());
		 writeToFile("output.txt", answ.toString());
	}
	public static void writeToFile(String fileName, String text){
		File file = new File(fileName);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			PrintWriter out = new PrintWriter(file.getAbsoluteFile());

			try {
				out.print(text);
			} finally {
				out.close();
			}
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	static String recursiveReverse(String s) {
        if ((null == s) || (s.length() <= 1)) {
            return s;
        }
        return recursiveReverse(s.substring(1)) + s.charAt(0);
    }
}
