import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Kanon {
	
	static int c;
    static int idxx = 0;
    static int massLength;
			
		public static void main(String[] args) throws IOException {
			Scanner SC = new Scanner(new File("input.txt")); 
			
			if(!SC.hasNext()) { SC.close(); return; }
			
            massLength = SC.nextInt();
            int a[] = new int[massLength];
            for(int i = 0; i < massLength; ++i)  a[i] = -1;
            c = SC.nextInt();
            int keyNumber = SC.nextInt();
            
            while(SC.hasNext()) {
            	int key = SC.nextInt();
            	if(check(key, a)) {
            	while(true) {
            	     int idx = hashFunction(key, idxx);
            	     if(a[idx]==-1) {
            	    	 a[idx] = key;
            	    	 break;
            	     } else {
            	    	 ++idxx;
            	     }
            	}
            	idxx = 0;
                }
            }
            
		    try {
		    	PrintWriter pw = new PrintWriter(new File("output.txt"));
		    	for(int i = 0; i < massLength; ++i) pw.print(a[i]+" ");
		    	pw.close();
		    }
		    catch (Exception e) {
		    	e.printStackTrace();
		    }
		    SC.close();
		}
		
		public static int hashFunction(int key, int tryNumber) {
			return ((key % massLength) + c * tryNumber) % massLength;  
		}
		
		public static boolean check(int key, int a[]) {
			boolean is = true;
			for(int i = 0; i < a.length; ++i) {
				if(a[i] == key) {is = false; break;}
			}
			return is;
		}
	
		
}