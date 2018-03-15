	import java.io.File;
	import java.io.IOException;
	import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
	import java.util.Stack;

public class solution {

	public static class b {
		public int l,r;
		b(){
			l = r = 0;
		}
	}
	static boolean added[];
	static Queue<Integer>queue;
	static ArrayList<Integer> ListSmej[];
	static int [] ab;
	static int idx = 0;
	
		public static void main(String[] args) throws IOException {
			Scanner SC = new Scanner(new File("input.txt")); 
			
			
			if(!SC.hasNext()) { SC.close(); return; }
			
			int k = 0, d = 0, p = 0;
			int [] a = new int [SC.nextInt()];
			added = new boolean[a.length];
			Arrays.fill(added, false);
			queue = new LinkedList<Integer>();
			ListSmej = new ArrayList[a.length];
	       ab=new int[a.length];
			for (int i = 0; i < a.length; ++i) {
				ListSmej[i] = new ArrayList<>();
	        }
			for(int x:a) {
				x = 0;
			}
			b [] c = new b[a.length];
			for(int h = 0; h < c.length; h++) {
				c[h] = new b();
			}
			
			while(SC.hasNext()) {
			    while(k != a.length) {
			    	p++;
			    	if(SC.nextInt() == 1) {
			    		ListSmej[d].add(p-1); 	
			    		System.out.println(d+":"+(p-1));	    		
			    	}
			    	++k;
			    }
			    System.out.println();
			    k=0;
			    p=0;
			    d++;
			}
			
//		for(int v = 0; v < a.length; ++v) {
//			
//			if(added[v]) {
//				continue;
//			}
//			queue.add(v);
//			added[v] = true;
//	
//			int v1;
//			while(!queue.isEmpty()) {
//
//				//======
//				v1=queue.poll();
//				ab.add(v1+1);
//				for(int g = 0; g < ListSmej[v1].size(); g++) {
//					int vers = ListSmej[v1].get(g);
//					System.out.println(vers);
//					if(added[vers]) {
//						System.out.println("Was");
//						continue;
//					}
//					queue.add(vers);
//					added[vers] = true;
//					
//				}
//				//======
//				
//			}
//			
//		}
			
			
	        //---------------------------------
		    try {
		    	PrintWriter pw = new PrintWriter(new File("output.txt"));
		    	for(int v = 0; v < a.length; ++v)
		    	     dfs(v);
		    	for(int o = 0; o<ab.length;o++) {
		    		pw.print(ab[o]+" ");
		    	}
		    	pw.close();
		    }
		    catch (Exception e) {
		    	e.printStackTrace();
		    }
		    SC.close();
		}
		public static void dfs(int v) {
		
			if(added[v]) {
				return;
			}
			//queue.add(v);
			added[v] = true;

				//======
				//v=queue.poll();
			++idx;
				ab[v] = idx;
				
				for(int g = 0; g < ListSmej[v].size(); g++) {
					Collections.sort(ListSmej[v]);
					int vers = ListSmej[v].get(g);
				//	System.out.println(vers);
//					if(added[vers]) {
//						continue;
//					}
					dfs(vers);
					//added[vers] = true;
					
				}
				//======
				
			
		}

}
