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
			int [] a = new int [SC.nextInt()];
			for(int x:a) {
				x = 0;
			}
			b [] c = new b[a.length-1];
			for(int h = 0; h < c.length; h++) {
				c[h] = new b();
			}
			int i = 0;
		           while(SC.hasNext()) {
		              c[i].l = SC.nextInt();
		              c[i].r = SC.nextInt();
		              ++i;
		           }
		        
		        	   for(b x:c) {
		        		   //S//ystem.out.println();
		        		   a[x.r-1] = x.l;
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
