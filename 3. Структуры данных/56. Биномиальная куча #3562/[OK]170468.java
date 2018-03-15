import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;

public class KuchaTest {

	public static void main(String[] args) throws IOException {
		Scanner SC = new Scanner(new File("input.txt"));
		if(!SC.hasNext()) { SC.close(); return; }
	    Long N = SC.nextLong(), buf1 = 1L, i = 0L;
	    Stack <Long> st = new Stack<>();
            //---------------------------------
	    try {
	    	PrintWriter pw = new PrintWriter(new File("output.txt"));
	    	if(N == 0) { pw.println(-1); pw.close(); return; }
	    	while(N != 0) {
                while(N >= buf1*2) {
	               buf1 *= 2;
	               ++i;
                }
                N -= buf1;
                st.push(i);
                buf1 = 1L;
                i = 0L;
		    }
	    	while(st.size() != 0) {
	    		pw.println(st.pop());
	    	}
	    	pw.close();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	    SC.close();
	}

}
