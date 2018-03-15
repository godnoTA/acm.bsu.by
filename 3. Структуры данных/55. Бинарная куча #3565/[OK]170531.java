import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class KuchaTest {

	public static void main(String[] args) throws IOException {
		Scanner SC = new Scanner(new File("input.txt"));
		if(!SC.hasNext()) { SC.close(); return; }
	    int N = SC.nextInt(), i = 1;
	    Long [] a = new Long[N + 1];
	    a[0] = -1L;
	    while(SC.hasNext()) {
	    	a[i] = SC.nextLong();
	    	++i;
	    }
            //---------------------------------
	    try {
	    	PrintWriter pw = new PrintWriter(new File("output.txt"));
	    	for(int g = 1; g < N + 1; ++g) {
	    		if(2 * g > N  && 2 * g + 1 > N ) { pw.print("Yes"); break; }
	    		else if(a[2 * g] >= a[g] && 2 * g + 1 > N) {
	    			       pw.print("Yes"); break; 
	    			 }
	    		
	    		if(a[g] > a[2 * g] || a[g] > a[2 * g + 1])
	    		{
	    			pw.print("No"); break;
	    		}
	    	}
	    	pw.close();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	    SC.close();
	}

}
