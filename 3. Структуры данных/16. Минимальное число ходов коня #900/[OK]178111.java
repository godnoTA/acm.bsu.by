import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Scanner;

public class solution {

	static int [][] d;
	static ArrayDeque <mPair> qu = new ArrayDeque<>();
	static int h2, l2, h,l;
    static public class mPair{
    	int a;
    	int b;
    	mPair(int a, int b) {
    		this.a = a;
    		this.b = b;
    	}
    }
	
    public static void func() {
    	while(!qu.isEmpty()) {
    	mPair crPos = qu.poll();
    	//-------
    	if(!(crPos.a-1 > h-1 || crPos.b+2 > l-1 || crPos.a-1 < 0 || crPos.b+2 < 0))
    		if(!(d[crPos.a-1][crPos.b+2] == -1 || d[crPos.a-1][crPos.b+2] != 0)) { 
    			qu.addLast(new mPair(crPos.a-1, crPos.b+2)); 
    			d[crPos.a-1][crPos.b+2] = d[crPos.a][crPos.b]+1;
    	    }
    	
    	
    	if(!(crPos.a-2 > h-1 || crPos.b+1 > l-1 || crPos.a-2 < 0 || crPos.b+1 < 0))
    		if(d[crPos.a-2][crPos.b+1] != -1 && d[crPos.a-2][crPos.b+1] == 0)
    	          {qu.addLast(new mPair(crPos.a-2, crPos.b+1)); d[crPos.a-2][crPos.b+1] = d[crPos.a][crPos.b]+1;}
    	
    	
    	if(!(crPos.a+1 > h-1 || crPos.b+2 > l-1 || crPos.a+1 < 0 || crPos.b+2 < 0))
    		if(d[crPos.a+1][crPos.b+2] != -1 &&  d[crPos.a+1][crPos.b+2] == 0)
                 {qu.addLast(new mPair(crPos.a+1, crPos.b+2)); d[crPos.a+1][crPos.b+2] = d[crPos.a][crPos.b]+1;}
        
    	
    	if(!(crPos.a+2 > h-1 || crPos.b+1 > l-1 || crPos.a+2 < 0 || crPos.b+1 < 0))
        	if((d[crPos.a+2][crPos.b+1] != -1 && d[crPos.a+2][crPos.b+1] == 0))
                {qu.addLast(new mPair(crPos.a+2, crPos.b+1)); d[crPos.a+2][crPos.b+1] = d[crPos.a][crPos.b]+1;}
        
    	
    	if(!(crPos.a+1 > h-1 || crPos.b-2 > l-1 || crPos.a+1 < 0 || crPos.b-2 < 0))
        	if((d[crPos.a+1][crPos.b-2] != -1 && d[crPos.a+1][crPos.b-2] == 0))
    	        {qu.addLast(new mPair(crPos.a+1, crPos.b-2)); d[crPos.a+1][crPos.b-2] = d[crPos.a][crPos.b]+1;}
    	
    	
    	if(!(crPos.a+2 > h-1 || crPos.b-1 > l-1 || crPos.a+2 < 0 || crPos.b-1 < 0))
    		if((d[crPos.a+2][crPos.b-1] != -1 && d[crPos.a+2][crPos.b-1] == 0 ))
    	       {qu.addLast(new mPair(crPos.a+2, crPos.b-1)); d[crPos.a+2][crPos.b-1] = d[crPos.a][crPos.b]+1;}
    	
    	
    	if(!(crPos.a-1 > h-1 || crPos.b-2 > l-1 || crPos.a-1 < 0 || crPos.b-2 < 0))
    		if((d[crPos.a-1][crPos.b-2] != -1 && d[crPos.a-1][crPos.b-2] == 0))
    	       {qu.addLast(new mPair(crPos.a-1, crPos.b-2)); d[crPos.a-1][crPos.b-2] = d[crPos.a][crPos.b]+1;}
    	
    	
    	if(!(crPos.a-2 > h-1 || crPos.b-1 > l-1 || crPos.a-2 < 0 || crPos.b-1 < 0))
    		if((d[crPos.a-2][crPos.b-1] != -1 && d[crPos.a-2][crPos.b-1] == 0))
    	       {qu.addLast(new mPair(crPos.a-2, crPos.b-1)); d[crPos.a-2][crPos.b-1] = d[crPos.a][crPos.b]+1;}
    	}
    }
   
	public static void main(String[] args) throws FileNotFoundException {
		Scanner SC = new Scanner(new File("in.txt")); 
		h = SC.nextInt();
		l = SC.nextInt();
		d = new int[h][l];
		for(int i = 0; i < h; ++i) {
			for(int s = 0; s < l; ++s) {
			      d[i][s] = SC.nextInt();
			}
		}
		int h1 = SC.nextInt();
		int l1 = SC.nextInt();
		qu.push(new mPair(h1-1,l1-1));
		h2 = SC.nextInt();
		l2 = SC.nextInt();
	
		if(d[h1-1][l1-1] != -1) {
			d[h1-1][l1-1] = 1;
		   func();}
		try {
	    	PrintWriter pw = new PrintWriter(new File("out.txt"));
	    	if(d[h2-1][l2-1] == 0 || d[h2-1][l2-1] == -1) {
	    		pw.print("No");
	    	} else {
	    		pw.print(d[h2-1][l2-1]-1);
	    	}
	    	pw.close();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	    SC.close();
	} 

}
