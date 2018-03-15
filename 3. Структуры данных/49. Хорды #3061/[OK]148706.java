
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.*;

public class Main implements Runnable {

	public  class Comp implements Comparator<Chord> {

		@Override
		public int compare(Chord o1, Chord o2) {
			
			return Integer.compare(o1.x, o2.x); //
			
		}
	}
	
	public class Chord {
		int x;
		int y;
		boolean isStart=true;
		
		Chord (int x,int y,boolean isStart) {
			this.x=x;
			this.y=y;
			this.isStart=isStart;
		}
		
		public String toString (){
			return x+" "+y+" "+isStart ;
		}
		
	}

	int N;
	int [] rtree ;
	List<Chord> chords = new ArrayList<>();
	
	int sum (int v, int tl, int tr, int l, int r) {
		if (l > r)
			return 0;
		if (l == tl && r == tr)
			return rtree [v];
		int tm = (tl + tr) / 2;
		return sum (v*2, tl, tm, l, Math.min(r,tm))+sum(v*2+1, tm+1, tr, Math.max(l,tm+1), r);
	}
	
	void update (int v, int tl, int tr, int pos, int new_val) {
		if (tl == tr)
			rtree[v] += new_val;//
		else {
			int tm = (tl + tr) / 2;
			if (pos <= tm)
				update (v*2, tl, tm, pos, new_val);
			else
				update (v*2+1, tm+1, tr, pos, new_val);
			rtree[v] = rtree[v*2] + rtree[v*2+1];
		}
	}	
	
	public static void main (String [] args ) {
		new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
	}
	public static void solve ( ) {
	//	new Thread(null, new Solution(), "", 64 * 1024 * 1024).start();
//		System.out.println("start");
	//	int N=0;
		Main m = new Main();
		
		
		File f=new File(/*"input.txt"*/"chords.in");
		  try
		  {
			  FileReader r = new FileReader(f);
		      Scanner sc = new Scanner(r);
		      m.N=sc.nextInt();   
		      m.N*=2;
		      int c;
		      m.rtree= new int [4*m.N];
		      while(sc.hasNext()){
		   	    	int x = sc.nextInt();
		   	    	int y = sc.nextInt();
		   	    	if (x>y){
		   	    		c=y;
		   	    		y=x;
		   	    		x=c;
		   	    	}
		   	    	
		   	    	m.chords.add(m.new Chord(x, -1, true)) ;
		   	    	m.chords.add(m.new Chord(y, x, false));
		      }		       
		      sc.close();
	   }
		   catch(IOException ex){  
		       System.out.println(ex.getMessage());
	   }  
		  
		  m.chords.sort(m.new Comp());

//		  System.out.println(m.chords);
		  long counter=0;
//		  System.out.println(Arrays.toString(m.rtree));

		  for (int i = 0; i < m.chords.size(); ++i) {
				
				if (m.chords.get(i).isStart == true) {
					m.update(1, 0, m.N, m.chords.get(i).x, 1);
				
			}
				else if  (m.chords.get(i).isStart == false) {
					counter += m.sum(1, 0, m.N, m.chords.get(i).y + 1, m.chords.get(i).x - 1);
//					System.out.println(m.sum(1, 0, m.N, m.chords.get(i).y + 1, m.chords.get(i).x - 1));
					m.update(1,0,m.N,m.chords.get(i).y,-1);
						
			}
		  }
		
//		  	System.out.println(counter);
			try(FileWriter writer = new FileWriter(/*"output.txt"*/"chords.out", false))
	        {
				writer.write(Long.toString(counter));          
	            writer.flush();
	        }
	        catch(IOException ex){
	            System.out.println(ex.getMessage());
	        } 
	}
	
	
	 public void run() {
	        solve();
	    }	
}
