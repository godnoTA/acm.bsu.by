	import java.io.File;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.util.Scanner;
	import java.util.Stack;

public class Kanon {

	public static class b {
		public int l,r;
		b(){
			l = r = 0;
		}
	}
		public static void main(String[] args) throws IOException {
			Scanner SC = new Scanner(new File("input.txt")); 
			if(!SC.hasNext()) { SC.close(); return; }
			
			int k = 0, d = 0, p = 0;
			int [] a = new int [SC.nextInt()];
			for(int x:a) {
				x = 0;
			}
			b [] c = new b[a.length-1];
			for(int h = 0; h < c.length; h++) {
				c[h] = new b();
			}
			while(SC.hasNext()) {
			    while(k != a.length) {
			    	p++;
			    	if(SC.nextInt() == 1) {
			    		System.out.println(d+1 +" "+p);
			    	    a[p-1] = d+1;
			    	}
			    	++k;
			    }
			    k=0;
			    p=0;
			    d++;
			}	           
	        //---------------------------------
		    try {
		    	PrintWriter pw = new PrintWriter(new File("output.txt"));
		    	for(int x:a) {
		    		pw.print(x+" ");
		    	}
		    	pw.close();
		    }
		    catch (Exception e) {
		    	e.printStackTrace();
		    }
		    SC.close();
		}
}